package com.alpha.booking.dao;

import com.alpha.booking.model.Discount;

public interface DiscountMapper {
    Discount selectByPrimaryKey(Integer id);
}