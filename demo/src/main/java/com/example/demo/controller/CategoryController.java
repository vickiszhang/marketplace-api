package com.example.demo.controller;

import com.example.demo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CategoryController {

    private static final String REDIS_INDEX_KEY = "CATEGORY";

    @Autowired
    RedisTemplate<String, Object> template;

    @RequestMapping(value="/category", method= RequestMethod.POST)
    public String addCategory(@RequestBody Category category) {
        System.out.println("creating category");
        template.opsForHash().put(REDIS_INDEX_KEY, category.getCategoryId(), category.toString());
        return "success";
    }

    @RequestMapping(value="/category", method= RequestMethod.GET)
    public ResponseEntity<Object> getCategory() {
        return new ResponseEntity<>(template.opsForHash().entries(REDIS_INDEX_KEY), HttpStatus.OK);
    }

    @RequestMapping(value="/category/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCategory(@PathVariable int categoryId) {
        return new ResponseEntity<>(template.opsForHash().delete(REDIS_INDEX_KEY, categoryId), HttpStatus.OK);
    }

}
