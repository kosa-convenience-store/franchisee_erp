package main.java.com.ouibak.erp.domain.orderList.orderList;

import java.sql.Timestamp;

public class Order {
    private int orderIdx;
    private int fIdx;
    private Timestamp orderDate;
    private String orderState;
    private Timestamp arriveDate;

    // Getters and Setters

    public int getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(int orderIdx) {
        this.orderIdx = orderIdx;
    }

    public int getFIdx() {
        return fIdx;
    }

    public void setFIdx(int fIdx) {
        this.fIdx = fIdx;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Timestamp getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Timestamp arriveDate) {
        this.arriveDate = arriveDate;
    }
}
