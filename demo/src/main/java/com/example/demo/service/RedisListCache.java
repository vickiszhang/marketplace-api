package com.example.demo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisListCache {
    private RedisTemplate<String, Object> template;
    private ListOperations<String, Object> listOperations;

    public RedisListCache(RedisTemplate<String, Object> template){
        this.template = template;
        listOperations = template.opsForList();

    }

    @PostConstruct
    public void setup() {
        listOperations.leftPush("xx", "working....");
        System.out.println(listOperations.rightPop("xx"));
    }
}
