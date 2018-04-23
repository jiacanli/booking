package com.alpha.booking.dao;

import com.alpha.booking.model.Category;

public interface CategoryMapper {
    Category selectByPrimaryKey(Integer id);
}