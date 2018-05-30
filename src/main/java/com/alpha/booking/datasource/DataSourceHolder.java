/**   
 * @Package: com.alpha.booking.datasource 
 * @author: jiacanli 
 * @date: 2018年5月30日 下午2:32:21 
 */
package com.alpha.booking.datasource;

 /** 
 * @ClassName: DataSourceHolder 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年5月30日 下午2:32:21  
 */
public class DataSourceHolder {

	/**
	 * 
	 */
	
	public static ThreadLocal<String> current_datasource = new ThreadLocal<String>();
	
	public DataSourceHolder() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setDataSource(String datasource) {
		current_datasource.set(datasource);
		
	}
	
	public static String getDataSource() {
		return current_datasource.get();
	}
	
	
	
	

}
