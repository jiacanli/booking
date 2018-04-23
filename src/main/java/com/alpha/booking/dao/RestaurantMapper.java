package com.alpha.booking.dao;

import com.alpha.booking.model.Restaurant;

public interface RestaurantMapper {
    Restaurant selectByPrimaryKey(Long id);
}