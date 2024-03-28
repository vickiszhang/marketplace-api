package com.example.demo.model;

public class User {
    private int userId;
    private String name;
    private int numItemsSold;
    private int numAvailableItems;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumItemsSold() {
        return numItemsSold;
    }

    public void setNumItemsSold(int numItemsSold) {
        this.numItemsSold = numItemsSold;
    }

    public int getNumAvailableItems() {
        return numAvailableItems;
    }

    public void setNumAvailableItems(int numAvailableItems) {
        this.numAvailableItems = numAvailableItems;
    }

    @Override
    public String toString() {
        return "UsersModel{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", numItemsSold=" + numItemsSold +
                ", numAvailableItems=" + numAvailableItems +
                '}';
    }
}