package com.example.demo.service;

import com.example.demo.repository.DataRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisValueCache {

//    private final ValueOperations<String, Object> valueOps;
//    private final RedisTemplate<String, Object> template;
    private final ArrayList<String> keys = new ArrayList<>();

    private final DataRepository repository;


    public RedisValueCache(final RedisTemplate<String, Object> template, DataRepository repository) {
//        this.valueOps = template.opsForValue();
//        this.template = template;
        this.repository = repository;
    }
    public void cache(final String key, final Object data) {
//        valueOps.set(key, data);
//        keys.add(key);
        repository.save(key, data);
    }

    public Object getCachedValue(final String key) {
        return repository.getById(key);
//        return valueOps.get(key);
    }

    public void deleteCachedValue(final String key) {
//        valueOps.getOperations().delete(key);
//        keys.remove(key);
        repository.delete(key);

    }

    public List<Object> getAll(String idPattern) {
//        Set<String> keys = template.keys(idPattern + "*");
//        assert keys != null;
//        return valueOps.multiGet(keys);
        return repository.getAll(idPattern);
    }
}
