package com.alpha.booking.service;

import java.util.List;

import com.alpha.booking.model.Orders;
import com.alpha.booking.result.model.OrderStaticByHour;
import com.alpha.booking.result.model.OrderStaticsDetailDaily;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.PageModel;

public interface OrderService extends BaseService<Orders>{
	DataModel<Object> additem(String restaurant_id,String table_num,String item_detail);
	DataModel<Object> insertOrder(Orders order);
	DataModel<Object> findByRestaurantId(String id);
	DataModel<Object> createOrder(String restaurant_id,String table_num);
	DataModel<Object> findByRestaurantIdAndTable(String id,String table_num);
    PageModel<Orders.SimpleFormatOrder> findByDetail(String restaurant_id,int page,int pagecount,String sdate,String edate);
    List<OrderStaticByHour> OrderStatisticsByHour(String date,long id);
    OrderStaticsDetailDaily OrderStatisticsDetail(String date,long id);
}
