package com.alpha.booking.service;

import com.alpha.booking.model.Orders;
import com.alpha.common.web.DataModel;

public interface OrderService extends BaseService<Orders>{
	DataModel<Object> additem(String restaurant_id,String table_num,String item_detail);
	DataModel<Object> insertOrder(Orders order);
	DataModel<Object> findByRestaurantId(String id);
	DataModel<Object> createOrder(String restaurant_id,String table_num);
}
