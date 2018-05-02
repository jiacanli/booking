package com.alpha.booking.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alpha.booking.dao.OrdersMapper;
import com.alpha.booking.model.ItemCart;
import com.alpha.booking.model.Orders;
import com.alpha.booking.service.OrderService;
import com.alpha.booking.util.Redis;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Orders> implements OrderService {
	
	

	public OrderServiceImpl() {
		// TODO Auto-generated constructor stub
	}




	public DataModel<Object> additem(String restaurant_id, String table_num, String item_detail) {
		// TODO Auto-generated method stub
		Jedis redis = Redis.getInstance();
		String key = restaurant_id+"order"+table_num;
		redis.watch(key);
		JSONObject items = JSONObject.parseObject(item_detail);
		JSONArray actions = items.getJSONArray("item_detail");
		ItemCart cart = JSONObject.parseObject(redis.get(key), ItemCart.class);
		for(int i=0;i<actions.size();i++) {
			JSONObject object = actions.getJSONObject(i);
			update(cart.getItems(), object);
		}
		Transaction tx = redis.multi();
		tx.set(key, cart.toString());
		List<Object> result = tx.exec();
		if(result==null) {
			
		}
		
		return null;
	}
	
	private void update(Map<Long, Integer> map,JSONObject object) {
		Long id = object.getLong("id");
		String action = object.getString("action");
		int num = object.getIntValue("amount");
		if(map.containsKey(id)) {
			int item_amounts = map.get(id);
			if(action.equals("plus")) {
				map.put(id, item_amounts+num);
			}
			else if(action.equals("minus")) {
				map.put(id, item_amounts-num<0?0:item_amounts-num);
			}
		}
		
		
		else {
			if(action.equals("add")) {
				map.put(id, 1);
			}
		}
	}




	public DataModel<Object> insertOrder(Orders order) {
		// TODO Auto-generated method stub
		order.setCreateTime(new Date());
		OrdersMapper mapper = (OrdersMapper) super.getMapper();
		int result =mapper.insert0(order);
		
		return result==0?ResultMapUtils.getFailResultMap("400", "插入失败")
				:ResultMapUtils.getResultMap("插入成功", "");
	}




	public DataModel<Object> findByRestaurantId(String id) {
		// TODO Auto-generated method stub
		Example example = new Example(Orders.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("restaurantId", id);
		List<Orders> results = mapper.selectByExample(example);
		return ResultMapUtils.getResultMap("", results);
	}





}
