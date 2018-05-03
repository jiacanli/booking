package com.alpha.booking.util;

import java.util.Collections;

import com.alpha.common.connect.BasePoolConfig;
import com.alpha.common.connect.PoolFactory;
import com.alpha.common.connect.RedisConnectionPool;

import redis.clients.jedis.Jedis;

public class Redis {

	
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    
	public Redis() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static final class prefix{
		public static final String ORDER = "order";
		public static final String ITEM = "item";
		public static final String PAY_LOCK = "pay-lock";
		public static final String NEW_ORDER_LOCK = "new-order-lock";
		
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
	
	public static boolean tryLock(Jedis redis,String lock,String value,int expireTime) {
        String result = redis.set(lock, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
		return false;
		
	}
	
	public static boolean releaseLock(Jedis redis,String lock,String value) {
	     String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
	        Object result = redis.eval(script, Collections.singletonList(lock), Collections.singletonList(value));

	        if (RELEASE_SUCCESS.equals(result)) {
	            return true;
	        }
	        
		return false;
	}
	

}
