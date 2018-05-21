package com.alpha.booking.dao;

import java.util.List;

import com.alpha.booking.model.CategoryWithItem;
import com.alpha.booking.model.SellItem;

public interface SellItemMapper extends BaseMapper<SellItem> {
   
    List<CategoryWithItem> selectAllItemsByRestaurantId(Long id);
    List<SellItem> selectAllItemsByRestaurantId0(Long id);
}