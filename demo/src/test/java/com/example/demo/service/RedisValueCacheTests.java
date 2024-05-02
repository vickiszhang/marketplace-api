package com.example.demo.service;

import com.example.demo.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RedisValueCacheTests {

    private RedisValueCache redisValueCache;

    @Mock
    private DataRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        redisValueCache = new RedisValueCache(mock(RedisTemplate.class), repository);
    }

    @Test
    public void testCache() {
        String key = "c1";
        Object data = "test category";
        redisValueCache.cache(key, data);
        verify(repository, times(1)).save(key, data);
    }

    @Test
    public void testGetCachedValue() {
        String key = "c1";
        Object expectedData = "test category";
        when(repository.getById(key)).thenReturn(expectedData);

        Object actualData = redisValueCache.getCachedValue(key);
        assertEquals(expectedData, actualData);
    }

    @Test
    public void testDeleteCachedValue() {
        String key = "c1";
        redisValueCache.deleteCachedValue(key);
        verify(repository, times(1)).delete(key);
    }

    @Test
    public void testGetAll() {
        String idPattern = "c";
        List<Object> expectedDataList = new ArrayList<>();
        expectedDataList.add("test category 1");
        expectedDataList.add("test category 2");
        when(repository.getAll(idPattern)).thenReturn(expectedDataList);

        List<Object> actualDataList = redisValueCache.getAll(idPattern);
        assertEquals(expectedDataList, actualDataList);
    }
}
