package com.alpha.booking.serviceImpl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alpha.booking.dao.OrderMapper;
import com.alpha.booking.model.Order;
import com.alpha.booking.service.OrderService;
import com.alpha.booking.util.Redis;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

import redis.clients.jedis.Jedis;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
	
	

	public OrderServiceImpl() {
		// TODO Auto-generated constructor stub
	}




	public DataModel<Object> additem(String restaurant_id, String table_num, String item_detail) {
		// TODO Auto-generated method stub
		JSONObject cart = new JSONObject();
		cart.put("restaurant_id", restaurant_id);
		cart.put("last_ip_time", new Date());
		cart.put("table_num", table_num);
		cart.put("item_detail", item_detail);
		Jedis redis = Redis.getInstance();
		redis.set(restaurant_id+table_num, cart.toJSONString());
		
		String last_updated = redis.get(restaurant_id+table_num);
		redis.close();
		return ResultMapUtils.getResultMap(last_updated);
	}




	public DataModel<Object> insertOrder(Order order) {
		// TODO Auto-generated method stub
		order.setCreateTime(new Date());
		OrderMapper mapper = (OrderMapper) super.getMapper();
		int result =mapper.insert0(order);
		
		return result==0?ResultMapUtils.getFailResultMap("400", "插入失败")
				:ResultMapUtils.getResultMap("插入成功", "");
	}





}
