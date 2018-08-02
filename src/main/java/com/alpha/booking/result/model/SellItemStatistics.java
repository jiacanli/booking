/**   
 * @Package: com.alpha.booking.result.model 
 * @author: jiacanli 
 * @date: 2018年8月2日 下午1:40:10 
 */
package com.alpha.booking.result.model;

 /** 
 * @ClassName: OrderItemStatistics 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年8月2日 下午1:40:10  
 */
public class SellItemStatistics {
	
	private String day;
	private long item_id;
	private int total;
	
	

	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public long getItem_id() {
		return item_id;
	}



	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	/**
	 * 
	 */
	public SellItemStatistics() {
		// TODO Auto-generated constructor stub
	}

}
