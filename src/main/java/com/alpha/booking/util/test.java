package com.alpha.booking.util;

import com.alibaba.fastjson.JSONObject;
import com.alpha.booking.model.ItemCart;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
			String teString = "{\"finished\":false,\"items\":{1:3},\"last_op_time\":1525337995054,\"order_num\":\"081568620988615692\",\"paied\":false,\"restaurant_id\":1,\"table_num\":\"20\"}";
			ItemCart object = JSONObject.parseObject(teString, ItemCart.class);
			System.out.println(object.toString());
			
	}

}
