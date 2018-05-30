/**   
 * @Package: com.alpha.booking.datasource 
 * @author: jiacanli 
 * @date: 2018年5月30日 下午3:14:11 
 */
package com.alpha.booking.datasource;

import java.lang.reflect.Method;
import java.security.Signature;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: DataSourceSwitchInterceptor 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年5月30日 下午3:14:11  
 */

@Component
@Aspect
public class DataSourceSwitchInterceptor {

	/**
	 * 
	 */
	
	private static  final Logger log = Logger.getLogger(DataSourceSwitchInterceptor.class);
	
	public DataSourceSwitchInterceptor() {
		// TODO Auto-generated constructor stub
	}
	
	@Around("@annotation(com.alpha.booking.datasource.DataSource)")
	public Object DataSourceSwitch(ProceedingJoinPoint joinPoint) throws Throwable {
		log.debug("datasource 设置");
		MethodSignature  signature = (MethodSignature) joinPoint.getSignature();
		Object target = joinPoint.getTarget();
		Method currentMethod = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
		DataSource datasource = currentMethod.getAnnotation(DataSource.class);
		if(datasource==null) {
			DataSourceHolder.setDataSource("default");
		}
		DataSourceHolder.setDataSource(datasource.DataSourceName());
		
		return joinPoint.proceed();
		
	}
	
	

}
