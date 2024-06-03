package main.java.com.ouibak.erp.domain.inventory;

public class InventoryDto {
    private String productName;
    private int count;

    public InventoryDto(String productName, int count) {
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
