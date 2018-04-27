package com.alpha.booking.service;

import com.alpha.booking.model.Order;
import com.alpha.common.web.DataModel;

public interface OrderService extends BaseService<Order>{
	DataModel<Object> additem(String restaurant_id,String table_num,String item_detail);
	DataModel<Object> insertOrder(Order order);
}
