package com.example.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataRepositoryTests {

    @Mock
    private RedisTemplate<String, Object> template;


    @InjectMocks
    private DataRepositoryImpl repository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new DataRepositoryImpl(template);
    }

    @Test
    public void testSave() {
        String key = "c1";
        Object data = "test";
        repository.save(key, data);
        assertEquals(1, repository.getKeys().size());
    }

}
