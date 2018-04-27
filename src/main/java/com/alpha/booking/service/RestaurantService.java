package com.alpha.booking.service;

import javax.servlet.http.HttpServletRequest;

import com.alpha.booking.model.Restaurant;
import com.alpha.common.web.DataModel;

public interface RestaurantService {
	DataModel<Object> register(HttpServletRequest request);
	DataModel<Object> getAllItems(String restaurant_id);
	DataModel<Object> getDetail(String restaurant_id);
}
