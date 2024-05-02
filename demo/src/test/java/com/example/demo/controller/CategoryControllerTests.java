package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.RedisValueCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTests {
    @Mock
    private RedisValueCache valueCache;

    @InjectMocks
    private CategoryController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCacheCategory() {
        Category category = new Category();
        category.setCategory("clothing");
        category.setCategoryId("c1");
        controller.cacheCategory(category);
        verify(valueCache, times(1)).cache("c1", category);
    }

    @Test
    public void testGetCategory() {
        Category category = new Category();
        category.setCategoryId("c1");
        category.setCategory("tools");

        Category category2 = new Category();
        category2.setCategoryId("c2");
        category2.setCategory("clothing");

        when(valueCache.getCachedValue("c1")).thenReturn(category);
        when(valueCache.getCachedValue("c2")).thenReturn(category2);

        Category result = controller.getCategory("c1");
        assertEquals(category, result);

        Category result2 = controller.getCategory("c2");
        assertEquals(category2, result2);
    }

    @Test
    public void testDeleteCategory() {
        controller.deleteCategory("c1");

        verify(valueCache, times(1)).deleteCachedValue("c1");
    }

    @Test
    public void testGetAll() {
        controller.getAll();

        verify(valueCache, times(1)).getAll("c");
    }

}
