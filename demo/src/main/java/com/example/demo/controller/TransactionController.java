package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.RedisValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final RedisValueCache valueCache;

    @Autowired
    public TransactionController(final RedisValueCache valueCache) {
        this.valueCache = valueCache;
    }
    @PostMapping
    public void cacheTransaction(@RequestBody final Transaction transaction) {
        valueCache.cache(transaction.getTransactionId(), transaction);
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable final String id) {
        return (Transaction) valueCache.getCachedValue(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable final String id) {
        valueCache.deleteCachedValue(id);
    }

    @GetMapping("/all")
    public List<Object> getAll() {
        return valueCache.getAll("t");
    }
}
