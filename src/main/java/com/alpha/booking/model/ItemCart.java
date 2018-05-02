package com.alpha.booking.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.StreamingHttpOutputMessage;

import com.alibaba.fastjson.JSONObject;

public class ItemCart {

	public ItemCart() {
		// TODO Auto-generated constructor stub
	}
	
	
	private String order_num;
	private Date last_op_time;
	private Long restaurant_id;
	private Map<Long, Integer> items = new HashMap<Long, Integer>();
	private String table_num;
	
	
	
	
	
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public Date getLast_op_time() {
		return last_op_time;
	}
	public void setLast_op_time(Date last_op_time) {
		this.last_op_time = last_op_time;
	}
	public Long getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public Map<Long, Integer> getItems() {
		return items;
	}
	public void setItems(Map<Long, Integer> items) {
		this.items = items;
	}
	public String getTable_num() {
		return table_num;
	}
	public void setTable_num(String table_num) {
		this.table_num = table_num;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
		
	}
	
	

}
