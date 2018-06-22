package com.alpha.booking.dao;

import java.util.List;

import com.alpha.booking.model.Orders;

public interface OrdersMapper extends BaseMapper<Orders> {
   int insert0(Orders order);
  
}