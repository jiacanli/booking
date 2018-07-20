package com.alpha.booking.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OrderUtil {
	private static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("10", "已确认");
		map.put("20", "未确认");
		map.put("30", "已支付");
		map.put("40", "未支付");
	}

	public OrderUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static String newOrder() {
		return getTimeStamp()+randomNum();
	}
	public static String getStatusString(String status) {		
		return map.get(status);
	}

	
	
	public static String getTimeStamp() {
		Long stamp = System.nanoTime();
		String string_format = String.valueOf(stamp);
		int hashcode = string_format.substring(2).hashCode();
		return (hashcode&0x7FFFFFFF)+"";
//		return string_format.substring(8);
	}
	
	public static String randomNum() {
		Long seed = System.nanoTime();
		Random random = new Random(seed);
		StringBuffer sBuffer = new StringBuffer();
		for(int i =0;i<4;i++) {
			sBuffer.append(String.valueOf(random.nextInt(10)));
		}
		return sBuffer.toString();
	}
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0;i<3000000;i++) {
			String key = newOrder();
			if(map.containsKey(key)) {
				System.err.println(i);
				break;
			}
			map.put(key, "");
		}
		
		System.out.println(newOrder());
	}

}
