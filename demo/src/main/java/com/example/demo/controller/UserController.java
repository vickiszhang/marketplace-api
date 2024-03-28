package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final String REDIS_INDEX_KEY = "USER";

    @Autowired
    RedisTemplate<String, Object> template;

    @RequestMapping(value="/user", method= RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        System.out.println("creating user");
        template.opsForHash().put(REDIS_INDEX_KEY, user.getUserId(), user.toString());
        return "success";
    }

    @RequestMapping(value="/user", method= RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        return new ResponseEntity<>(template.opsForHash().entries(REDIS_INDEX_KEY), HttpStatus.OK);
    }

    @RequestMapping(value="/user/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable int userId) {
        return new ResponseEntity<>(template.opsForHash().delete(REDIS_INDEX_KEY, userId), HttpStatus.OK);
    }


}
