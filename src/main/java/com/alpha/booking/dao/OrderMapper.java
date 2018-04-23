package com.alpha.booking.dao;

import com.alpha.booking.model.Order;

public interface OrderMapper {
    Order selectByPrimaryKey(String orderNum);
}