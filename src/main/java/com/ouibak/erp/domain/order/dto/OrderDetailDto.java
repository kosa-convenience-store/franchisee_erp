package main.java.com.ouibak.erp.domain.order.dto;

public class OrderDetailDto {
    private int orderIdx;
    private String productName;
    private int count;

    public OrderDetailDto(int orderIdx, String productName, int count) {
        this.orderIdx = orderIdx;
        this.productName = productName;
        this.count = count;
    }

    public int getOrderIdx() {
        return orderIdx;
    }

    public String getProductName() {
        return productName;
    }

    public int getCount() {
        return count;
    }
}
