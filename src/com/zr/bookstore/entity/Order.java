package com.zr.bookstore.entity;

import java.util.Date;

public class Order {
    private String orderId;
    private String amount;
    private String receiver;
    private String phonenumber;
    private Date orderTime;

    public Order() {
    }

    public Order(String orderId, String amount, String receiver, String phonenumber, Date orderTime) {
        this.orderId = orderId;
        this.amount = amount;
        this.receiver = receiver;
        this.phonenumber = phonenumber;
        this.orderTime = orderTime;
    }

    public Order(String amount, String receiver, String phonenumber, Date orderTime) {
        this.amount = amount;
        this.receiver = receiver;
        this.phonenumber = phonenumber;
        this.orderTime = orderTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrder_id(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrder_time(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", amount='" + amount + '\'' +
                ", receiver='" + receiver + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
