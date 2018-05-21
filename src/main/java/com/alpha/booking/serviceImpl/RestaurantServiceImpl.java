package com.alpha.booking.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.booking.dao.CategoryMapper;
import com.alpha.booking.dao.RestaurantMapper;
import com.alpha.booking.dao.SellItemMapper;
import com.alpha.booking.model.Category;
import com.alpha.booking.model.CategoryWithItem;
import com.alpha.booking.model.Restaurant;
import com.alpha.booking.model.SellItem;
import com.alpha.booking.service.RestaurantService;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class RestaurantServiceImpl extends BaseServiceImpl<Restaurant> implements RestaurantService  {
	@Autowired
	private SellItemMapper mapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	public DataModel<Object> register(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//获取所有商品
	public DataModel<Object> getAllItems(Long restaurant_id) {
		// TODO Auto-generated method stub
//		List<CategoryWithItem> list = mapper.selectAllItemsByRestaurantId(restaurant_id);
//		return ResultMapUtils.getResultMap(list);
		List<SellItem> result = mapper.selectAllItemsByRestaurantId0(restaurant_id);
		return ResultMapUtils.getResultMap(result);
	}

	public DataModel<Object> getDetail(Long restaurant_id) {
		// TODO Auto-generated method stub
		RestaurantMapper mapper = (RestaurantMapper) super.getMapper();
		Example example = new Example(Restaurant.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", restaurant_id);
		return ResultMapUtils.getResultMap(mapper.selectByExample(example).get(0));
		
	}
	
	//获取所有分类品种 
	public DataModel<Object> getCategory(Long restaurant_id) {
		// TODO Auto-generated method stub
		Example example = new Example(Category.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("restaurantId", restaurant_id);
		List<Category> result = categoryMapper.selectByExample(example);
		
		return ResultMapUtils.getResultMap(result);
	}
	



}
