package com.alpha.booking.dao;

import com.alpha.booking.model.Town;

public interface TownMapper {
    Town selectByPrimaryKey(Integer id);
}