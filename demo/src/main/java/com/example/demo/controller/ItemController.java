package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.service.RedisValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final RedisValueCache valueCache;

    @Autowired
    public ItemController(final RedisValueCache valueCache) {
        this.valueCache = valueCache;
    }
    @PostMapping
    public void cacheItem(@RequestBody final Item item) {
        valueCache.cache(item.getItemId(), item);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable final String id) {
        return (Item) valueCache.getCachedValue(id);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable final String id) {
        valueCache.deleteCachedValue(id);
    }

}
