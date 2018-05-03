package com.alpha.booking.service;

import com.alpha.booking.model.Discount;
import com.alpha.common.web.DataModel;

public interface DiscountService extends BaseService<Discount> {
	DataModel<Object> selectByid(Long restaurant_id);
}
