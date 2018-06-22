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
import com.alpha.booking.service.OrderItemService;
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
	/**
	 * 
	 */
	public OrderItemController() {
		// TODO Auto-generated constructor stub
	}
	
}
