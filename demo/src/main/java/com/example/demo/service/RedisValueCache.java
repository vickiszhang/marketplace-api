package com.example.demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RedisValueCache {

    private final ValueOperations<String, Object> valueOps;
    private final RedisTemplate<String, Object> template;
    private ArrayList<String> keys = new ArrayList<>();

    public RedisValueCache(final RedisTemplate<String, Object> template) {
        this.valueOps = template.opsForValue();
        this.template = template;
    }
    public void cache(final String key, final Object data) {
        valueOps.set(key, data);
        keys.add(key);
    }

    public Object getCachedValue(final String key) {
        return valueOps.get(key);
    }

    public void deleteCachedValue(final String key) {
        valueOps.getOperations().delete(key);
        keys.remove(key);
    }

    public List<Object> getAll(String idPattern) {
        Set<String> keys = template.keys(idPattern + "*");
        assert keys != null;
        return valueOps.multiGet(keys);
    }
}
