package com.example.demo.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Category")
public class Category implements Serializable {
    private String categoryId;
    private String category;


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                '}';
    }
}
