/**   
 * @Package: com.alpha.booking.serviceImpl 
 * @author: jiacanli 
 * @date: 2018年6月21日 下午5:24:52 
 */
package com.alpha.booking.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.booking.dao.OrderItemMapper;
import com.alpha.booking.model.OrderItem;
import com.alpha.booking.model.OrderItem.OrderItemDetail;
import com.alpha.booking.service.OrderItemService;

/** 
 * @ClassName: OrderItemServiceImpl 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年6月21日 下午5:24:52  
 */
@Service
public class OrderItemServiceImpl  extends BaseServiceImpl<OrderItem> implements OrderItemService{
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	/**
	 * 
	 */
	public OrderItemServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.alpha.booking.service.OrderItemService#selectItemDetail()
	 */
	public List<OrderItemDetail> selectItemDetail(String order_num) {
		// TODO Auto-generated method stub
		List<OrderItem.OrderItemDetail> result = orderItemMapper.selectItemDetail(order_num);
		return result;
	}

}
