package com.alpha.booking.util;

import com.alpha.common.connect.BasePoolConfig;
import com.alpha.common.connect.PoolFactory;
import com.alpha.common.connect.RedisConnectionPool;

import redis.clients.jedis.Jedis;

public class Redis {

	public Redis() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static class prefix{
		public static final String ORDER = "order";
		public static final String ITEM = "item";
	}
	
	
	
	private static  RedisConnectionPool POOL ;
	
	public static Jedis getInstance() {
		if(POOL==null) {
			initPool();
		}
		return POOL.getResource();
	}
	
	private synchronized static void initPool() {
		
		BasePoolConfig config = new BasePoolConfig("localhost", 6379, "ljcc");
		try {
			POOL = (RedisConnectionPool) PoolFactory.buid(RedisConnectionPool.class, config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
