/**   
 * @Package: com.alpha.booking.datasource 
 * @author: jiacanli 
 * @date: 2018年5月30日 下午2:31:53 
 */
package com.alpha.booking.datasource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 
 * @ClassName: MultiDataSourceManager 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年5月30日 下午2:31:53  
 */
public class MultiDataSourceManager extends AbstractRoutingDataSource {

	
	public final Logger log = Logger.getLogger(this.getClass());
	/**
	 * 
	 */
	public MultiDataSourceManager() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		String datasource = DataSourceHolder.getDataSource();
		if(datasource==null) {
			DataSourceHolder.setDataSource("default");
			return "default";
		}
		
		return datasource;
	}

}
