/**   
 * @Package: com.alpha.booking.result.model 
 * @author: jiacanli 
 * @date: 2018年8月1日 下午2:16:49 
 */
package com.alpha.booking.result.model;

 /** 
 * @ClassName: OrderStaticsDetailDaily 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年8月1日 下午2:16:49  
 */
public class OrderStaticsDetailDaily {

	/**
	 * 
	 */
	public OrderStaticsDetailDaily() {
		// TODO Auto-generated constructor stub
	}
	
	private int order_total = 0;
	private int order_cancel = 0;
	private int order_confirm = 0;
	private int order_pay = 0;
	
	public int getOrder_total() {
		return order_total;
	}
	
	
	public void cancelIncrese() {
		order_cancel++;
		
	}
	
	public void confirmIncrese() {
		order_confirm++;
		
	}
	
	public void payIncrese() {
		order_pay++;
	}
	
	
	public void setOrder_total(int order_total) {
		this.order_total = order_total;
	}
	public int getOrder_cancel() {
		return order_cancel;
	}
	public void setOrder_cancel(int order_cancel) {
		this.order_cancel = order_cancel;
	}
	public int getOrder_confirm() {
		return order_confirm;
	}
	public void setOrder_confirm(int order_confirm) {
		this.order_confirm = order_confirm;
	}
	public int getOrder_pay() {
		return order_pay;
	}
	public void setOrder_pay(int order_pay) {
		this.order_pay = order_pay;
	}
	

}
