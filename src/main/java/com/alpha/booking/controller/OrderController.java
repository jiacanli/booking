package com.alpha.booking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.booking.model.OrderItem;
import com.alpha.booking.model.OrderUpdateStub;
import com.alpha.booking.model.Orders;
import com.alpha.booking.service.OrderItemService;
import com.alpha.booking.service.OrderService;
import com.alpha.booking.service.OrderUpdateStubService;
import com.alpha.booking.util.ParamPreCheck;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.PageModel;
import com.alpha.common.web.ResultMapUtils;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderUpdateStubService orderUpdateStubService;
	
	public OrderController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	@RequestMapping("/additem")
	public DataModel<Object> addItem(HttpServletRequest request){
		if(!ParamPreCheck.checkNull(request,"restaurant_id","table_num"
				,"item_detail")) {
			return ResultMapUtils.getFailResultMap("400", "参数错误");
		}
		String restaurant_id = request.getParameter("restaurant_id");
		String table_num = request.getParameter("table_num");
		String json_item_detail = request.getParameter("item_detail");
		
		return service.additem(restaurant_id, table_num, json_item_detail);
		
	}
	
	@RequestMapping("/insert")
	public DataModel<Object> insert(Orders order){		
		return service.insertOrder(order);
	}
	
	@RequestMapping("/syncorder")
	public DataModel<Object> neworder(
			@RequestParam(value="restaurant_id",required=true)String restaurant_id
			,@RequestParam(value="table_num",required=true)String table_num ){
		return service.createOrder(restaurant_id, table_num);
	}
	
	@RequestMapping("/find")
	public DataModel<Object> find(@RequestParam(value="restaurant_id",required=true)String restaurant_id){
		return service.findByRestaurantId(restaurant_id);
		
	}
	
	@RequestMapping("/tableorder")
	public DataModel<Object> tableorder(
			@RequestParam(value="restaurant_id",required=true)String restaurant_id,
			@RequestParam(value="table_num",required=true)String table_num){
		return service.findByRestaurantIdAndTable(restaurant_id, table_num);
		
	}
	
	@RequestMapping("/update_status")
	public DataModel<Object> confirm(Orders order){
		Example example = new Example(Orders.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("orderNum", order.getOrderNum());
		int result = service.updateByExampleSelective(order, example);
		return result == 0?ResultMapUtils.failUpdating():ResultMapUtils.getResultMap();
	}
	
	@RequestMapping("/update_items")
	public DataModel<Object> update(
			@RequestParam(value = "new_item",required= true) List<OrderItem> items,
			@RequestParam(value = "old_item",required = true) List<OrderUpdateStub> olds
			){
		
		for(OrderItem item :items) {
			int update_result = orderItemService.updateByprimaryKeySelective(item);
			if(update_result==0) {
				orderItemService.deleteByPrimaryKey(item.getId());
				return ResultMapUtils.failUpdating();
			}
		}
		orderUpdateStubService.insertList(olds);
		return ResultMapUtils.getResultMap();
	}
	
	
	

}
