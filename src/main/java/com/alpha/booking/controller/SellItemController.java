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
import com.alpha.booking.result.model.TurnOver;
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
@RequestMapping("")
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
	
	@RequestMapping("/sellitem/statistics")
	public DataModel<Object> sellItemStatistics(@RequestParam(value ="value",required = true) String value){
		try {
			String plain_params = AuthenticateUtil.decrypt(value);
			log.debug(String.format("明文 ------> %s", plain_params));
			JSONObject param_json = JSONObject.parseObject(plain_params);
			String start = param_json.getString("start");
			String end = param_json.getString("end");
			Long id = param_json.getLong("id");
			List<Long> sell_items = JSONObject.parseArray(param_json.getJSONArray("item_id").toJSONString(), Long.class);
			List<SellItemStatistics> result = sellItemService.sellItemStatistics(start, end, id, sell_items);
			String result_str = JSON.toJSONString(result);
			log.debug(String.format("clear result ： %s",result_str ));
			String plain_result = AuthenticateUtil.AESencrypt(result_str);
			return ResultMapUtils.getResultMap(plain_result);
//			return ResultMapUtils.getResultMap(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMapUtils.getFailResultMap("400", "授权失败");
		}
	}
	
	@RequestMapping("/salesbytime")
	public DataModel<Object> salesbytime(@RequestParam(value="value",required = true)String value){
		try {
			String plain_params = AuthenticateUtil.decrypt(value);
			log.debug(String.format("明文 ------> %s", plain_params));
			JSONObject param_json = JSONObject.parseObject(plain_params);
			String start = param_json.getString("time");
			Long id = param_json.getLong("id");
			List<TurnOver> result =  sellItemService.turnOverByTime(id, start, null, null);
			String result_str = JSON.toJSONString(result);
			log.debug(String.format("claear result : %s", result_str));
			String plain_result = AuthenticateUtil.AESencrypt(result_str);
			return ResultMapUtils.getResultMap(plain_result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMapUtils.getFailResultMap("400", "授权失败");
		}
		
	}
	

	

}
