package com.saktaWdi.MyWebApp.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BashCache {
    private Cache<String,Object> Cache= CacheBuilder.newBuilder()
            .initialCapacity(10)
            .maximumSize(100)
            .concurrencyLevel(5)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .recordStats()
            .build();

    public Cache<String, Object> getCache() {
        return Cache;
    }

    public void setCache(Cache<String, Object> Cache) {
        this.Cache = Cache;
    }
}
