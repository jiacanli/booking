package com.alpha.booking.util;

import javax.servlet.http.HttpServletRequest;

public class ParamPreCheck {

	public ParamPreCheck() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static boolean checkNull(HttpServletRequest request,String... args) {
		for(String key:args) {
			if(request.getParameter(key)==null) {
				return false;
			}
		}
		return true;
	}

}
