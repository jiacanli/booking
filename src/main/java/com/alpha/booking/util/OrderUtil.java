package com.alpha.booking.util;

import java.util.Random;

public class OrderUtil {

	public OrderUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static String newOrder() {
		return getTimeStamp()+randomNum();
	}
	
	public static String getTimeStamp() {
		Long stamp = System.nanoTime();
		String string_format = String.valueOf(stamp);
		return string_format.substring(1);
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

}
