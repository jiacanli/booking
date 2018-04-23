package com.alpha.booking.dao;

import com.alpha.booking.model.SellItem;

public interface SellItemMapper {
    SellItem selectByPrimaryKey(Integer id);
}