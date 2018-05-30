/**   
 * @Package: com.alpha.booking.datasource 
 * @author: jiacanli 
 * @date: 2018年5月30日 下午3:03:31 
 */
package com.alpha.booking.datasource;

import java.lang.annotation.*;

/** 
 * @ClassName: DataSource 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年5月30日 下午3:03:31  
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	
	String DataSourceName() default "default";

	
	
}
