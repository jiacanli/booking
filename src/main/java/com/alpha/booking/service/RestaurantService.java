package com.alpha.booking.service;

import javax.servlet.http.HttpServletRequest;

import com.alpha.common.web.DataModel;

public interface RestaurantService {
	DataModel<Object> register(HttpServletRequest request);
	DataModel<Object> getAllItems(Long restaurant_id);
	DataModel<Object> getDetail(Long restaurant_id);
	DataModel<Object> getCategory(Long restaurant_id);
}
