package com.alpha.booking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alpha.booking.model.Orders;
import com.alpha.booking.result.model.OrderStaticByHour;

public interface OrdersMapper extends BaseMapper<Orders> {
   int insert0(Orders order);
   List<OrderStaticByHour> OrderStatisticsByHour(@Param("start")String start,@Param("end")String end,@Param("id")long id);
  
}