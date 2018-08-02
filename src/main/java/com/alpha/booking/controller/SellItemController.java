/**   
 * @Package: com.alpha.booking.controller 
 * @author: jiacanli 
 * @date: 2018年5月17日 下午2:07:38 
 */
package com.alpha.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alpha.booking.dao.SellItemMapper;
import com.alpha.booking.result.model.SellItemStatistics;
import com.alpha.booking.service.SellItemService;
import com.alpha.booking.util.AuthenticateUtil;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

/** 
 * @ClassName: SellItemController 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年5月17日 下午2:07:38  
 */

@RestController
@RequestMapping("/sellitem")
public class SellItemController {
	/**
	 * 
	 */
	
	private static Logger log = LoggerFactory.getLogger(SellItemController.class);
	@Autowired
	private SellItemService sellItemService;
	
	public SellItemController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/statistics")
	public DataModel<Object> sellItemStatistics(@RequestParam(value ="value",required = true) String value){
		try {
			String plain_params = AuthenticateUtil.decrypt(value);
			log.debug(String.format("明文 ------> %s", plain_params));
			JSONObject param_json = JSONObject.parseObject(plain_params);
			String start = param_json.getString("start");
			String end = param_json.getString("end");
			Long id = param_json.getLong("id");
			List<Integer> sell_items = 	JSONObject.parseArray(param_json.getJSONArray("item_id").toJSONString(), Integer.class);
			List<SellItemStatistics> result = sellItemService.sellItemStatistics(start, end, id, sell_items);
			String result_str = JSON.toJSONString(result);
			String plain_result = AuthenticateUtil.AESencrypt(result_str);
			return ResultMapUtils.getResultMap(plain_result);
//			return ResultMapUtils.getResultMap(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMapUtils.getFailResultMap("400", "授权失败");
		}
	}
//	@RequestMapping("/init")
//	public void init() {
//		JSONReader reader;
//		int count = 0;
//		int start_category = 2;
//		List<SellItem> records = new ArrayList<SellItem>();
//		try {
//			reader = new JSONReader(new FileReader("d:\\wx\\items.json"));
//			reader.startArray();
//			while(reader.hasNext()&&start_category<9) {
//				JSONObject object = (JSONObject) reader.readObject();
//				SellItem item = new SellItem();				
//				item.setDescription(object.getString("description"));
//				item.setDiscount(0);
//				item.setImage(object.getString("image"));
//				item.setName(object.getString("name"));
//				item.setIsSoldout(0);
//				item.setPrice(object.getDouble("price"));
//				item.setOrigPrice(object.getDouble("price"));
//				item.setOrderNum(object.getInteger("sold"));
//				item.setUnit(object.getString("unit"));
//				item.setRestaurantId(1L);
//				records.add(item);
//				item.setCategory(start_category);
//				count++;
//				if(count%5==0)
//					start_category++;
//			}
//			
//			mapper.insertList(records);
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	

}
