package com.alpha.booking.dao;

import com.alpha.booking.model.Order;

public interface OrderMapper extends BaseMapper<Order> {
   int insert0(Order order);
}