package com.alpha.booking.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alpha.booking.dao.OrdersMapper;
import com.alpha.booking.model.Orders;
import com.alpha.booking.service.OrderService;
import com.alpha.booking.util.Redis;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Orders> implements OrderService {
	
	

	public OrderServiceImpl() {
		// TODO Auto-generated constructor stub
	}




	public DataModel<Object> additem(String restaurant_id, String table_num, String item_detail) {
		// TODO Auto-generated method stub
		JSONObject cart = new JSONObject();
		cart.put("restaurant_id", restaurant_id);
		cart.put("last_op_time", new Date());
		cart.put("table_num", table_num);
		cart.put("item_detail", item_detail);
		Jedis redis = Redis.getInstance();
		redis.set(restaurant_id+table_num, cart.toJSONString());
		//返回最新表单
		String last_updated = redis.get(restaurant_id+table_num);
		redis.close();
		return ResultMapUtils.getResultMap(last_updated);
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
