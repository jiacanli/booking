/**   
 * @Package: com.alpha.booking.service 
 * @author: jiacanli 
 * @date: 2018年6月21日 下午4:56:59 
 */
package com.alpha.booking.service;

import java.util.List;

import com.alpha.booking.model.OrderItem;

/** 
 * @ClassName: OrderItemService 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年6月21日 下午4:56:59  
 */
public interface OrderItemService extends BaseService<OrderItem>{
		List<OrderItem.OrderItemDetail> selectItemDetail(String order_num);
	

}
