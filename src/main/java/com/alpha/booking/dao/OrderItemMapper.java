package com.alpha.booking.dao;

import com.alpha.booking.model.OrderItem;

public interface OrderItemMapper {
    OrderItem selectByPrimaryKey(Integer id);
}