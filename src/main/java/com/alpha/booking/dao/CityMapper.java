package com.alpha.booking.dao;

import com.alpha.booking.model.City;

public interface CityMapper {
    City selectByPrimaryKey(Long id);
}