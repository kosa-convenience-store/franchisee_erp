package main.java.com.ouibak.erp.domain.transaction.dto;

import java.sql.Timestamp;

public class TransactionListDto {
    private int transactionId;
    private int totalPrice;
    private Timestamp transactionTime;

    public TransactionListDto(int transactionId, int totalPrice, Timestamp transactionTime) {
        this.transactionId = transactionId;
        this.totalPrice = totalPrice;
        this.transactionTime = transactionTime;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }
}
