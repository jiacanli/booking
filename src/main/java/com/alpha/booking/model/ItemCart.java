package com.alpha.booking.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class ItemCart {
	
	public ItemCart() {
		
	}

	public ItemCart(String order_num,Long restaurant_id,String table_num) {
		// TODO Auto-generated constructor stub	
		this.order_num = order_num;
		this.last_op_time = new Date();
		this.table_num = table_num;
		this.restaurant_id = restaurant_id;
	}
	
	
	private String order_num;
	private Date last_op_time;
	private Long restaurant_id;
	private Map<Long, Integer> items = new HashMap<Long, Integer>();
	private String table_num;
	// 是否已经提交
	private boolean finished = false;
	// 是否已经结算
	private boolean paied = false;
	
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public boolean isPaied() {
		return paied;
	}
	public void setPaied(boolean paied) {
		this.paied = paied;
	}
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
