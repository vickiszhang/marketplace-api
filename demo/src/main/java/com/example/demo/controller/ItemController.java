package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.service.RedisValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    @GetMapping("/all")
    public List<Object> getAll() {
        return valueCache.getAll("i");
    }

    @GetMapping("/seller={userId}")
    public List<Item> getAllByUser(@PathVariable final String userId) {
        List<Object> allItems = valueCache.getAll("i");
        List<Item> itemsSoldByUser = new ArrayList<>();

        for (Object itemObj : allItems) {
            Item item = (Item) itemObj;
            if (Objects.equals(item.getUserId(), userId)) {
                itemsSoldByUser.add(item);
            }
        }

        return itemsSoldByUser;
    }

//    @GetMapping("/search?q={searchQuery}")
    @RequestMapping(value="search", method = RequestMethod.GET)
    public List<Item> getAllMatchingItems(@RequestParam("q") final String searchQuery) {
        List<Object> allItems = valueCache.getAll("i");
        List<Item> matchingItems = new ArrayList<>();

        for (Object itemObj : allItems) {
            Item item = (Item) itemObj;
            if (item.getName().contains(searchQuery) || item.getDescription().contains(searchQuery)) {
                matchingItems.add(item);
            }
        }

        return matchingItems;
    }

}
