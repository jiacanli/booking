package com.alpha.booking.dao;

import java.util.List;

import com.alpha.booking.model.OrderItem;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
	List<OrderItem.OrderItemDetail> selectItemDetail(String order_num);
}