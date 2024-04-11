package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RedisValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/all")
    public List<Object> getAll() {
        return valueCache.getAll("u");
    }

    @GetMapping("/min_items={minItems}")
    public List<User> getUserMinSold(@PathVariable final int minItems) {
        List<Object> allUsers = valueCache.getAll("u");
        List<User> usersMinSold = new ArrayList<>();

        for (Object obj : allUsers) {
            User user = (User) obj;
            if (user.getNumItemsSold() >= minItems) {
                usersMinSold.add(user);
            }
        }

        return usersMinSold;

    }
}
