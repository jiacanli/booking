package com.alpha.booking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alpha.booking.model.CategoryWithItem;
import com.alpha.booking.model.SellItem;
import com.alpha.booking.result.model.SellItemStatistics;

public interface SellItemMapper extends BaseMapper<SellItem> {
   
    List<CategoryWithItem> selectAllItemsByRestaurantId(Long id);
    List<SellItem> selectAllItemsByRestaurantId0(Long id);
    List<SellItemStatistics> sellItemStatistics(@Param("start")String start,@Param("end")String end,@Param("id")long id,@Param("item_id")List<Integer> item_id);
}