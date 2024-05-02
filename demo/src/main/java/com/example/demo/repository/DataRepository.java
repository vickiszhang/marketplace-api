package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository {

    void save(String key, Object data);

    Object getById(String key);

    void delete(String key);

    List<Object> getAll(String idPattern);
}