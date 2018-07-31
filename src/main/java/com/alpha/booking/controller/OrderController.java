package com.alpha.booking.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alpha.booking.model.OrderItem;
import com.alpha.booking.model.OrderUpdateStub;
import com.alpha.booking.model.Orders;
import com.alpha.booking.result.model.OrderStaticByHour;
import com.alpha.booking.service.OrderItemService;
import com.alpha.booking.service.OrderService;
import com.alpha.booking.service.OrderUpdateStubService;
import com.alpha.booking.util.AuthenticateUtil;
import com.alpha.booking.util.ParamPreCheck;
import com.alpha.booking.util.test;
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
			@RequestParam(value = "new_item",required= true) String items,
			@RequestParam(value = "old_item",required = true) String olds,
			@RequestParam(value = "order_num",required = true) String order_num
			){
		try {

		List<OrderItem.OrderItemDetail> new_item = JSONObject.parseArray(items, OrderItem.OrderItemDetail.class);
		List<OrderUpdateStub> update_stub = JSONObject.parseArray(olds, OrderUpdateStub.class);
		for(OrderItem.OrderItemDetail item : new_item) {		
			Example example = new Example(OrderItem.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("id", item.getId());
			int result = orderItemService.updateByExampleSelective(item.transfer(), example);
		}
		for(OrderUpdateStub stub:update_stub) {
			stub.setOpttime(new Date());
			stub.setOrderNum(order_num);
		}
		orderUpdateStubService.insertList(update_stub);
		return ResultMapUtils.getResultMap();}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResultMapUtils.failUpdating();
		}
	}
	
	@RequestMapping("/ordersbyhour")
	public DataModel<Object> orderByhour(
			@RequestParam(value = "date",required = true) String date,
			@RequestParam(value = "token",required = true)String token,
			@RequestParam(value = "r_id",required = true)Long id
			){
		if(AuthenticateUtil.checkPermission(token)) {
			List<OrderStaticByHour> result = service.OrderStatisticsByHour(date,id);
			return ResultMapUtils.getResultMap(result);
		}
		
		return ResultMapUtils.getFailResultMap("400", "授权失败");
		
	}
	
	
	
	

}
