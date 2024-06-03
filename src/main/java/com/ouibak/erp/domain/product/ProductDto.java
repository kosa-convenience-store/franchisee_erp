package main.java.com.ouibak.erp.domain.product;

public class ProductDto {
    private int productIdx;
    private String productName;
    private int productPrice;

    public ProductDto(int productIdx, String productName, int productPrice) {
        this.productIdx = productIdx;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductIdx() {
        return productIdx;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }
}
