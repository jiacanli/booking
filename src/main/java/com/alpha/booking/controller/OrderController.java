package com.alpha.booking.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.booking.model.Orders;
import com.alpha.booking.service.OrderService;
import com.alpha.booking.util.ParamPreCheck;
import com.alpha.common.connect.test;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	public OrderController() {
		// TODO Auto-generated constructor stub
	}
	
	
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
	
	@RequestMapping("/create")
	public DataModel<Object> insert(Orders order){		
		return service.insertOrder(order);
	}
	
	@RequestMapping("/find")
	public DataModel<Object> find(@RequestParam(value="restaurant_id",required=true)String restaurant_id){
		return service.findByRestaurantId(restaurant_id);
		
	}
	
	

}
