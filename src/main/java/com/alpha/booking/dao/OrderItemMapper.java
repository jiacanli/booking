package com.alpha.booking.dao;

import java.util.List;

import com.alpha.booking.model.OrderItem;
import com.alpha.booking.result.model.SellItemStatistics;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
	List<OrderItem.OrderItemDetail> selectItemDetail(String order_num);
}