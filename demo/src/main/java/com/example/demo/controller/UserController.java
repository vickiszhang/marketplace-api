package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RedisValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final RedisValueCache valueCache;

    @Autowired
    public UserController(final RedisValueCache valueCache) {
        this.valueCache = valueCache;
    }
    @PostMapping
    public void cacheUser(@RequestBody final User user) {
        valueCache.cache(user.getUserId(), user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable final String id) {
        return (User) valueCache.getCachedValue(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final String id) {
        valueCache.deleteCachedValue(id);
    }
}
