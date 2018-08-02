/**   
 * @Package: com.alpha.booking.serviceImpl 
 * @author: jiacanli 
 * @date: 2018年8月2日 下午1:54:20 
 */
package com.alpha.booking.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alpha.booking.dao.SellItemMapper;
import com.alpha.booking.model.SellItem;
import com.alpha.booking.result.model.SellItemStatistics;
import com.alpha.booking.service.SellItemService;

/** 
 * @ClassName: SellItemServiceImpl 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年8月2日 下午1:54:20  
 */
@Service
public class SellItemServiceImpl extends BaseServiceImpl<SellItem> implements SellItemService {

	/**
	 * 
	 */
	public SellItemServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/** 
	* @Title: sellItemStatistics 
	* @Description: TODO 
	* @author gdkj
	* @date 2018年8月2日下午1:54:35
	*/ 
	@Override
	public List<SellItemStatistics> sellItemStatistics(String start, String end, long id, List<Integer> item_id) {
		// TODO Auto-generated method stub
		SellItemMapper mapper = (SellItemMapper) super.mapper;	
		String start_date = start+" 00:00:00";
		String end_date = end+" 23:59:59";
		return mapper.sellItemStatistics(start_date, end_date, id, item_id);
	}

}
