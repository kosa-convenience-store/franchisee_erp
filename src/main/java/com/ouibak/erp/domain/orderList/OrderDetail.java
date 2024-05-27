package main.java.com.ouibak.erp.domain.orderList;

public class OrderDetail {
    private int orderIdx;
    private String productName;
    private int count;

    // Getters and setters
    public int getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(int orderIdx) {
        this.orderIdx = orderIdx;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
