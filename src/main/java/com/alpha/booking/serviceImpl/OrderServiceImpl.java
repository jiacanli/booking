package com.alpha.booking.serviceImpl;

import java.io.IOException;
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
		//插入之前首先获取锁(防止并发插入)
		Jedis redis = Redis.getInstance();
		String lock_key = order.getRestaurantId()+Redis.prefix.PAY_LOCK+order.getTableNum();
		String key = order.getRestaurantId()+Redis.prefix.ORDER+order.getTableNum();
		ItemCart cart = JSONObject.parseObject(redis.get(key), ItemCart.class);
		if(!Redis.tryLock(redis, lock_key, Redis.prefix.PAY_LOCK, 10)) {
			//加锁失败
			return ResultMapUtils.getFailResultMap("400", "其他小伙伴正在提交");
		}
		if(cart.isFinished()) {
			return ResultMapUtils.getFailResultMap("400", "该订单已经完成");
		}
		order.setCreateTime(new Date());
		OrdersMapper mapper = (OrdersMapper) super.getMapper();
		int result =mapper.insert0(order);
		if(result!=0) {
			cart.setFinished(true);
			redis.set(key, cart.toString());
			Redis.releaseLock(redis, lock_key, Redis.prefix.PAY_LOCK);
			redis.close();
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
			if(time_diff(now.getTime(), last_op_date.getTime())<1800) {
				redis.close();
				return ResultMapUtils.getResultMap("已存在订单", cart.getOrder_num());
			}
		}
		String order_num = OrderUtil.newOrder();
		ItemCart cart = new ItemCart(order_num
				,Long.parseLong(restaurant_id),table_num);
		//防止并发覆盖
		redis.setnx(key, cart.toString());
		redis.close();
		return ResultMapUtils.getResultMap("订单生成成功", order_num);
	}

	
	
	private Long time_diff(Long time1,Long time2) {
		return (time1-time2)/1000;
	}



}
