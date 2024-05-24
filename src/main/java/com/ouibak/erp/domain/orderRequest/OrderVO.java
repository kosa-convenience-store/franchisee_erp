package main.java.com.ouibak.erp.domain.orderRequest;

import java.util.List;

public class OrderVO {
    private int franchiseeId;
    private List<String> productList;

    public int getFranchiseeId() {
        return franchiseeId;
    }

    public void setFranchiseeId(int franchiseeId) {
        this.franchiseeId = franchiseeId;
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }

}
