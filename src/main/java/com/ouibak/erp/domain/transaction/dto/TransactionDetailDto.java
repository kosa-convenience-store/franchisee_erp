package main.java.com.ouibak.erp.domain.transaction.dto;

public class TransactionDetailDto {
    private int transactionId;
    private String productName;
    private int count;

    public TransactionDetailDto(int transactionId, String productName, int count) {
        this.transactionId = transactionId;
        this.productName = productName;
        this.count = count;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getProductName() {
        return productName;
    }

    public int getCount() {
        return count;
    }
}
