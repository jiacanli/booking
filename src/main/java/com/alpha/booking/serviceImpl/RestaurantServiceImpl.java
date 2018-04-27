package com.alpha.booking.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.booking.dao.SellItemMapper;
import com.alpha.booking.model.CategoryWithItem;
import com.alpha.booking.service.RestaurantService;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

@Service
public class RestaurantServiceImpl implements RestaurantService  {
	@Autowired
	private SellItemMapper mapper;
	
	public DataModel<Object> register(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModel<Object> getAllItems(String restaurant_id) {
		// TODO Auto-generated method stub
		List<CategoryWithItem> list = mapper.selectAllItemsByRestaurantId(restaurant_id);
		return ResultMapUtils.getResultMap(list);
	}

	public DataModel<Object> getDetail(String restaurant_id) {
		// TODO Auto-generated method stub
		return null;
	}
	



}
