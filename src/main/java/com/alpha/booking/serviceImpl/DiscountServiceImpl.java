package com.alpha.booking.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alpha.booking.model.Discount;
import com.alpha.booking.service.DiscountService;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DiscountServiceImpl extends BaseServiceImpl<Discount> implements DiscountService {

	public DataModel<Object> selectByid(Long restaurant_id) {
		// TODO Auto-generated method stub
		Example example = new Example(Discount.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("restaurantId", restaurant_id);
		List<Discount> result = mapper.selectByExample(example);
		return ResultMapUtils.getResultMap(result);
	}



}
