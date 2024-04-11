package com.example.demo.service;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisListCache {
    private ListOperations<String, Object> listOps;

    public RedisListCache(RedisTemplate<String, Object> template){
        listOps = template.opsForList();

    }

}
