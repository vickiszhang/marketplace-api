package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    private String transactionId;
    private int buyerId;
    private int sellerId;
    private int itemId;
    private Date date;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", itemId=" + itemId +
                ", date=" + date +
                '}';
    }
}
