package com.alpha.booking.dao;

import com.alpha.booking.model.Orders;

public interface OrdersMapper extends BaseMapper<Orders> {
   int insert0(Orders order);
}