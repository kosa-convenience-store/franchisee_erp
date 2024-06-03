package main.java.com.ouibak.erp.domain.order.dto;

import java.sql.Timestamp;

public class OrderListDto {
    private int orderIdx;
    private int fIdx;
    private Timestamp orderDate;
    private String orderState;
    private Timestamp arriveDate;

    public OrderListDto(int orderIdx, int fIdx, Timestamp orderDate, String orderState, Timestamp arriveDate) {
        this.orderIdx = orderIdx;
        this.fIdx = fIdx;
        this.orderDate = orderDate;
        this.orderState = orderState;
        this.arriveDate = arriveDate;
    }

    public int getOrderIdx() {
        return orderIdx;
    }

    public int getfIdx() {
        return fIdx;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public String getOrderState() {
        return orderState;
    }

    public Timestamp getArriveDate() {
        return arriveDate;
    }
}
