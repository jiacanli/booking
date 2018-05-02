package com.alpha.booking.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.booking.service.RestaurantService;
import com.alpha.common.web.DataModel;
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	private RestaurantService service; 
	
	public RestaurantController() {
		// TODO Auto-generated constructor stub
	}
	
	public DataModel<Object> register(HttpServletRequest request,@RequestParam(value = "license",
			required = false) MultipartFile file) {
		return null;
		
	}
	
	@RequestMapping("/items")
	public DataModel<Object> getItems(
			@RequestParam(value="restaurant_id",required=true)String restaurant_id){
		
		return service.getAllItems(restaurant_id);
		
	}
	
	@RequestMapping("/categories")
	public DataModel<Object> getCategory(){
		return null;
	}

}
