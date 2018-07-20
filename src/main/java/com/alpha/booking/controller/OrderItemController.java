/**   
 * @Package: com.alpha.booking.controller 
 * @author: jiacanli 
 * @date: 2018年6月21日 下午4:54:43 
 */
package com.alpha.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.booking.model.OrderItem;
import com.alpha.booking.model.OrderUpdateStub;
import com.alpha.booking.service.OrderItemService;
import com.alpha.booking.service.OrderUpdateStubService;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

/** 
 * @ClassName: OrderItemController 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年6月21日 下午4:54:43  
 */

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
	
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderUpdateStubService orderUpdateStubService;
	/**
	 * 
	 */
	public OrderItemController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@RequestMapping("/update")
	public DataModel<Object> update(
			@RequestParam(value = "new_item",required= true) List<OrderItem> items,
			@RequestParam(value = "old_item",required = true) List<OrderUpdateStub> olds
			){
		
		for(OrderItem item :items) {
			int update_result = orderItemService.updateByprimaryKeySelective(item);
			if(update_result==0) {
				orderItemService.deleteByPrimaryKey(item.getId());
				return ResultMapUtils.failUpdating();
			}
		}
		orderUpdateStubService.insertList(olds);
		return ResultMapUtils.getResultMap();
	}
	
}
