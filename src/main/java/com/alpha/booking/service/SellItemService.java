/**   
 * @Package: com.alpha.booking.service 
 * @author: jiacanli 
 * @date: 2018年8月2日 下午1:51:35 
 */
package com.alpha.booking.service;

import java.util.List;

import com.alpha.booking.model.SellItem;
import com.alpha.booking.result.model.SellItemStatistics;

/** 
 * @ClassName: SellItemService 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年8月2日 下午1:51:35  
 */
public interface SellItemService extends BaseService<SellItem> {
		List<SellItemStatistics> sellItemStatistics(String start,String end,long id,List<Integer> item_id);
}
