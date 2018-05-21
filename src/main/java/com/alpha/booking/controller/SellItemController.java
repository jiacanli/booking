/**   
 * @Package: com.alpha.booking.controller 
 * @author: jiacanli 
 * @date: 2018年5月17日 下午2:07:38 
 */
package com.alpha.booking.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alpha.booking.dao.SellItemMapper;
import com.alpha.booking.model.SellItem;

/** 
 * @ClassName: SellItemController 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年5月17日 下午2:07:38  
 */

@RestController
@RequestMapping("/sellitem")
public class SellItemController {
	@Autowired
	private SellItemMapper mapper;
	/**
	 * 
	 */
	public SellItemController() {
		// TODO Auto-generated constructor stub
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
