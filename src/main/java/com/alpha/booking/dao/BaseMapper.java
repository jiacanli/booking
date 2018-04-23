package com.alpha.booking.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * 
 * @Date 2017年7月13日 下午2:41:05
 * @author yangfei
 * @version 1.0.0
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
