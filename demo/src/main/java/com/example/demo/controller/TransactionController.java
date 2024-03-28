package com.example.demo.controller;

import com.example.demo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class TransactionController {
    private static final String REDIS_INDEX_KEY = "TRANSACTION";

    @Autowired
    RedisTemplate<String, Object> template;

    @RequestMapping(value="/transaction", method= RequestMethod.POST)
    public String addTransaction(@RequestBody Transaction transaction) {
        System.out.println("creating transaction");
        template.opsForHash().put(REDIS_INDEX_KEY, transaction.getTransactionId(), transaction.toString());
        return "success";
    }

    @RequestMapping(value="/transaction", method= RequestMethod.GET)
    public ResponseEntity<Object> getTransaction() {
        return new ResponseEntity<>(template.opsForHash().entries(REDIS_INDEX_KEY), HttpStatus.OK);
    }

    @RequestMapping(value="/transaction/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTransaction(@PathVariable int transactionId) {
        return new ResponseEntity<>(template.opsForHash().delete(REDIS_INDEX_KEY, transactionId), HttpStatus.OK);
    }
}
