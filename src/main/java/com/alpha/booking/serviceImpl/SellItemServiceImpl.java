/**   
 * @Package: com.alpha.booking.serviceImpl 
 * @author: jiacanli 
 * @date: 2018年8月2日 下午1:54:20 
 */
package com.alpha.booking.serviceImpl;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alpha.booking.dao.SellItemMapper;
import com.alpha.booking.model.SellItem;
import com.alpha.booking.result.model.SellItemStatistics;
import com.alpha.booking.result.model.TurnOver;
import com.alpha.booking.service.SellItemService;

/** 
 * @ClassName: SellItemServiceImpl 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年8月2日 下午1:54:20  
 */
@Service
public class SellItemServiceImpl extends BaseServiceImpl<SellItem> implements SellItemService {
	
	
	
	
	
	private static final String YEAR = "year";
	private static final String MONTH = "month";
	private static final String DAY = "day";
	
	private static final String YEAR_TAIL = "-01-01 00:00:00";
	private static final String MONTH_TAIL = "-01 00:00:00";
	private static final String DAY_TAIL = " 00:00:00";
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
	public List<SellItemStatistics> sellItemStatistics(String start, String end, long id, List<Long> item_id) {
		// TODO Auto-generated method stub
		SellItemMapper mapper = (SellItemMapper) super.mapper;	
		String start_date = start+" 00:00:00";
		String end_date = end+" 23:59:59";
		return mapper.sellItemStatistics(start_date, end_date, id, item_id);
	}

	/** 
	* @Title: turnOverByTime 
	* @Description: TODO 
	* @author gdkj
	* @date 2018年8月6日下午3:38:17
	*/ 
	@Override
	public List<TurnOver> turnOverByTime(long id, String start, String end, String type) {
		// TODO Auto-generated method stub
		SellItemMapper mapper = (SellItemMapper) super.mapper;	
		if(end == null || type == null) {
			String[] date_format = start.split("-");
			switch(date_format.length) {
			case 1:
				type = YEAR;
				start = start+YEAR_TAIL;
				end = getDate(start, Calendar.YEAR, 1)+DAY_TAIL;
				break;
			case 2:
				type = MONTH;
				start = start +MONTH_TAIL;
				end = getDate(start, Calendar.MONTH, 1)+DAY_TAIL;
				break;
			case 3:
				type = DAY;
				start = start +DAY_TAIL;
				end = getDate(start, Calendar.DAY_OF_MONTH, 1)+DAY_TAIL;
				break;
			default:
				break;
			
			}
		}
		return mapper.turnOverByTime(id, start, end, type);
	}

}
