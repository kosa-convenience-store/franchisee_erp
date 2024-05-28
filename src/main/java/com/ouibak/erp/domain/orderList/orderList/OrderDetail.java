package main.java.com.ouibak.erp.domain.orderList.orderList;

public class OrderDetail {
    private int orderIdx;
    private int productIdx;
    private int count;

    // Getters and setters
    public int getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(int orderIdx) {
        this.orderIdx = orderIdx;
    }

    public int getProductIdx() {
        return productIdx;
    }

    public void setProductIdx(int productIdx) {
        this.productIdx = productIdx;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
