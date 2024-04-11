package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.RedisValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final RedisValueCache valueCache;

    @Autowired
    public CategoryController(final RedisValueCache valueCache) {
        this.valueCache = valueCache;
    }
    @PostMapping
    public void cacheCategory(@RequestBody final Category category) {
        valueCache.cache(category.getCategoryId(), category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable final String id) {
        return (Category) valueCache.getCachedValue(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable final String id) {
        valueCache.deleteCachedValue(id);
    }

    @GetMapping("/all")
    public List<Object> getAll() {
        return valueCache.getAll("c");
    }
}
