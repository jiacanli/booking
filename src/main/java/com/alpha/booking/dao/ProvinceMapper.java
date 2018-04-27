package com.alpha.booking.dao;

import com.alpha.booking.model.Province;

public interface ProvinceMapper {
    Province selectByPrimaryKey(Long id);
}