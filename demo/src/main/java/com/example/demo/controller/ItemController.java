package com.example.demo.controller;

import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ItemController {
    private static final String REDIS_INDEX_KEY = "ITEM";

    @Autowired
    RedisTemplate<String, Object> template;

    @RequestMapping(value="/item", method= RequestMethod.POST)
    public String addItem(@RequestBody Item item) {
        System.out.println("creating item");
        template.opsForHash().put(REDIS_INDEX_KEY, item.getItemId(), item.toString());
        return "success";
    }

    @RequestMapping(value="/item", method= RequestMethod.GET)
    public ResponseEntity<Object> getItem() {
        return new ResponseEntity<>(template.opsForHash().entries(REDIS_INDEX_KEY), HttpStatus.OK);
    }

    @RequestMapping(value="/item/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteItem(@PathVariable int itemId) {
        return new ResponseEntity<>(template.opsForHash().delete(REDIS_INDEX_KEY, itemId), HttpStatus.OK);
    }

}
