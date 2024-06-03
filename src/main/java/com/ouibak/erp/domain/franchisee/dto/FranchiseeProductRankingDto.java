package main.java.com.ouibak.erp.domain.franchisee.dto;

public class FranchiseeProductRankingDto {
    private String productName;
    private int count;

    public FranchiseeProductRankingDto(String productName, int count) {
        this.productName = productName;
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public int getCount() {
        return count;
    }
}
