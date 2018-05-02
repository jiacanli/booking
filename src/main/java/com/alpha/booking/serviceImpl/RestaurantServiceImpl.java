package com.alpha.booking.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.booking.dao.CategoryMapper;
import com.alpha.booking.dao.SellItemMapper;
import com.alpha.booking.model.Category;
import com.alpha.booking.model.CategoryWithItem;
import com.alpha.booking.service.RestaurantService;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class RestaurantServiceImpl implements RestaurantService  {
	@Autowired
	private SellItemMapper mapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
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

	public DataModel<Object> getCategory(String restaurant_id) {
		// TODO Auto-generated method stub
		Example example = new Example(Category.class);
		Criteria criteria = example.createCriteria();
		criteria.andCondition("restaurantId", Long.parseLong(restaurant_id));
		List<Category> result = categoryMapper.selectByExample(example);
		
		return ResultMapUtils.getResultMap(result);
	}
	



}
