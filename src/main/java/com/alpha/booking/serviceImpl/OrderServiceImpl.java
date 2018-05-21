package com.alpha.booking.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alpha.booking.dao.OrderItemMapper;
import com.alpha.booking.dao.OrdersMapper;
import com.alpha.booking.model.ItemCart;
import com.alpha.booking.model.OrderItem;
import com.alpha.booking.model.Orders;
import com.alpha.booking.service.OrderService;
import com.alpha.booking.util.OrderUtil;
import com.alpha.booking.util.Redis;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Orders> implements OrderService {
	
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	public OrderServiceImpl() {
		// TODO Auto-generated constructor stub
	}



	/*
	 * 
	 * @see com.alpha.booking.service.OrderService#additem(java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * item_detail
	 *     { 
	 *       id,action,amount
	 *        }
	 */
	public DataModel<Object> additem(String restaurant_id, String table_num, String item_detail) {
		// TODO Auto-generated method stub
		Jedis redis = Redis.getInstance();
		// 防止重复
		String prefix = Redis.prefix.ORDER;
		String key = restaurant_id+prefix+table_num;
		String key_lock = restaurant_id+Redis.prefix.PAY_LOCK+table_num;
		//上锁key
		redis.watch(key);
		//redis中的订单，理论上之前已经生成了最新的订单
		String cart_str = redis.get(key);
		// 
		if(cart_str==null) {
			return createOrder(restaurant_id, table_num);
		}
		ItemCart cart = JSONObject.parseObject(cart_str, ItemCart.class);
		//需要判断该订单是不是已经提交了
		if(cart.isFinished()||redis.exists(key_lock)) {
			redis.unwatch();
			redis.close();
			return ResultMapUtils.getFailResultMap("400", "该订单已经提交");
		}
		JSONObject items = (JSONObject) JSONObject.parse(item_detail);
		//商品的操作（增加 减少）
		JSONArray actions = items.getJSONArray("data");
		for(int i=0;i<actions.size();i++) {
			JSONObject object = actions.getJSONObject(i);
			//逐条更新cart中的商品和数量
			update(cart.getItems(), object);
		}
		//开启事务
		Transaction tx = redis.multi();
		cart.setLast_op_time(new Date());
		tx.set(key, cart.toString());
		List<Object> result = tx.exec();
		//结果为null说明事务执行失败
		if(result==null) {
			try {
				tx.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ItemCart last_update_cart = JSONObject.parseObject(redis.get(key), ItemCart.class);
			redis.close();
			return ResultMapUtils.getResultMap("操作冲突", last_update_cart);
		}
		//事务执行成功
		ItemCart last_update_cart = JSONObject.parseObject(redis.get(key), ItemCart.class);
		redis.close();
		//返回最新结果
		return ResultMapUtils.getResultMap("操作成功", last_update_cart);
	}
	
	private void update(Map<Long, Integer> map,JSONObject object) {
		Long id = object.getLong("id");
		String action = object.getString("action");
		int num = object.getIntValue("amount");
		// redis订单中已存在该商品
		if(map.containsKey(id)) {
			//原始商品的数量
			int item_amounts = map.get(id);
			//判断执行的增减操作
			if(action.equals("plus")) {
				map.put(id, item_amounts+num);
			}
			else if(action.equals("minus")) {
				map.put(id, item_amounts-num<0?0:item_amounts-num);
			}
		}
		
		//订单中不存在该商品
		else {
			if(action.equals("plus")) {
				map.put(id, num);
			}
		}
	}




	public DataModel<Object> insertOrder(Orders order) {
		// TODO Auto-generated method stub
		
		Jedis redis = Redis.getInstance();
		String lock_key = order.getRestaurantId()+Redis.prefix.PAY_LOCK+order.getTableNum();
		String key = order.getRestaurantId()+Redis.prefix.ORDER+order.getTableNum();
		ItemCart cart = JSONObject.parseObject(redis.get(key), ItemCart.class);
		//插入之前首先获取锁(防止并发插入)
		if(!Redis.tryLock(redis, lock_key, Redis.prefix.PAY_LOCK, 10)) {
			//加锁失败
			return ResultMapUtils.getFailResultMap("400", "其他小伙伴正在提交订单");
		}
		if(cart.isFinished()) {
			return ResultMapUtils.getFailResultMap("400", "该订单已经完成");
		}
		order.setCreateTime(new Date());
		OrdersMapper mapper = (OrdersMapper) super.getMapper();
		int result =mapper.insert0(order);
		//插入订单成功，下面插入订单详情到order_item
		if(result!=0) {
			cart.setFinished(true);
			redis.set(key, cart.toString());
			Redis.releaseLock(redis, lock_key, Redis.prefix.PAY_LOCK);
			redis.close();
			//开始插入订单明细item
			List<OrderItem> items = parseItems(order.getItems(), order.getOrderNum());
			int order_item_result = orderItemMapper.insertList(items);						
			return ResultMapUtils.getResultMap("插入操作成功", "");

			
		}		
		return ResultMapUtils.getFailResultMap("400", "插入失败");
				
	}




	public DataModel<Object> findByRestaurantId(String id) {
		// TODO Auto-generated method stub
		Example example = new Example(Orders.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("restaurantId", Long.parseLong(id));
		List<Orders> results = mapper.selectByExample(example);
		return ResultMapUtils.getResultMap("查询成功", results);
		
	}


	//后台生成订单号 并插入redis
	/*
	 * (non-Javadoc)
	 * @see com.alpha.booking.service.OrderService#createOrder(java.lang.String, java.lang.String)
	 * 
	 * 
	 * 
	 */
	public DataModel<Object> createOrder(String restaurant_id, String table_num) {
		// TODO Auto-generated method stub
		Jedis redis = Redis.getInstance();
		String prefix = Redis.prefix.ORDER;
		String key = restaurant_id+prefix+table_num;
		if(redis.exists(key)) {
			Date now = new Date();
			ItemCart cart = JSONObject.parseObject(redis.get(key), ItemCart.class);
			Date last_op_date = cart.getLast_op_time();
			if(time_diff(now.getTime(), last_op_date.getTime())<1800&&!cart.isFinished()) {
				redis.close();
				return ResultMapUtils.getResultMap("已存在订单", cart);
			}
		}
		String key_lock = restaurant_id+Redis.prefix.NEW_ORDER_LOCK+table_num;
		//生成新订单前上锁
		if(Redis.tryLock(redis, key_lock, Redis.prefix.NEW_ORDER_LOCK, 10)) {
			String order_num = OrderUtil.newOrder();
			ItemCart new_cart = new ItemCart(order_num
					,Long.parseLong(restaurant_id),table_num);
			//防止并发覆盖
			redis.set(key, new_cart.toString());
			Redis.releaseLock(redis, key_lock, Redis.prefix.NEW_ORDER_LOCK);
			redis.close();
			ItemCart result = new ItemCart();
			result.setOrder_num(order_num);
			result.setRestaurant_id(Long.parseLong(restaurant_id));
			result.setTable_num(table_num);			
			return ResultMapUtils.getResultMap("订单生成成功", result);
		}
		
		return ResultMapUtils.getResultMap("400", "订单正在生成");

	}

	
	
	private Long time_diff(Long time1,Long time2) {
		return (time1-time2)/1000;
	}



	public DataModel<Object> findByRestaurantIdAndTable(String id, String table_num) {
		// TODO Auto-generated method stub
		Jedis redis =Redis.getInstance();
		ItemCart result = JSONObject.parseObject(redis.get(id+Redis.prefix.ORDER+table_num), ItemCart.class);
		redis.close();
		return ResultMapUtils.getResultMap("获取成功", result);
	}
	
	/*
	 * 组装OrderItem实例
	 */
	private List<OrderItem> parseItems(String items,String order_num){
		JSONArray items_json = JSONArray.parseArray(items);
		List<OrderItem> result = new ArrayList<OrderItem>();
		for(int i=0;i<items_json.size();i++) {
			JSONObject item = items_json.getJSONObject(i);
			Long item_id = item.getLongValue("id");
			int amount = item.getIntValue("amount");
			double prince = item.getDoubleValue("price");
			OrderItem orderItem = new OrderItem(order_num,item_id,amount,prince);
			result.add(orderItem);
		}
		return result;
	}



}
