package com.hwh.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 解决每次都读取数据库的问题，实现缓存，不用每次都读取数据库
 * @author hwh
 * @create 2018/9/27 11:13
 */
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}
