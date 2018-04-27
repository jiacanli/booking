package com.alpha.booking.dao;

import java.util.List;

import com.alpha.booking.model.CategoryWithItem;
import com.alpha.booking.model.SellItem;

public interface SellItemMapper {
    SellItem selectByPrimaryKey(Integer id);
    List<CategoryWithItem> selectAllItemsByRestaurantId(String id);
}