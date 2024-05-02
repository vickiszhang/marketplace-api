package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataRepositoryImpl implements DataRepository{

    @Autowired
    private RedisTemplate<String, Object> template;


    private final ArrayList<String> keys = new ArrayList<>();


    @Override
    public void save(String key, Object data) {
        template.opsForValue().set(key, data);
        keys.add(key);

    }

    @Override
    public Object getById(String key) {
        return template.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        template.opsForValue().getOperations().delete(key);
        keys.remove(key);
    }

    @Override
    public List<Object> getAll(String idPattern) {
        Set<String> matchingKeys = template.keys(idPattern + "*");
        assert matchingKeys != null;
        return template.opsForValue().multiGet(matchingKeys);
    }

    public ArrayList<String> getKeys() {
        return keys;
    }
}
