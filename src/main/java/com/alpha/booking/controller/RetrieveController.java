/**   
 * @Package: com.alpha.booking.controller 
 * @author: jiacanli 
 * @date: 2018年6月21日 下午3:13:44 
 */
package com.alpha.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.booking.model.Orders;
import com.alpha.booking.service.OrderItemService;
import com.alpha.booking.service.OrderService;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.PageModel;
import com.alpha.common.web.ResultMapUtils;

/** 
 * @ClassName: RetrieveController 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年6月21日 下午3:13:44  
 */
@RestController
@RequestMapping("/")
public class RetrieveController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	
	

	/**
	 * 
	 */
	public RetrieveController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/findbydetail")
	public DataModel<Object> findbydetail(
			@RequestParam (value="Guid",required=true)String storeGuid,
			@RequestParam (value="sDate",required=false) String sdate,
			@RequestParam(value="eDate",required=false) String edate,
			@RequestParam(value="page",required=false) int page,
			@RequestParam(value="pageCount",required=false) int pagecount,
			@RequestParam(value="code",required = true)String code
			){
		if("check_order".equals(code)) {
		PageModel<Orders.SimpleFormatOrder> result = orderService.findByDetail(storeGuid, page, pagecount, sdate, edate);
		return ResultMapUtils.getResultMap(result);
		}
		else if("check_item".equals(code)) {
			return ResultMapUtils.getResultMap(orderItemService.selectItemDetail(storeGuid));
		}
		else {
			return ResultMapUtils.getFailResultMap("400", "参数错误");
		}
		
	}
	
	
	public static void main(String[] args) {
		System.err.println(12%10);
	}
	
	
}
