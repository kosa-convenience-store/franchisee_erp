package main.java.com.ouibak.erp.domain.product;

import java.util.HashMap;
import java.util.List;

public class ProductController {
    ProductService service = new ProductService();

    public int getProductIdxByName(String productName) {
        return service.getProductIdxByName(productName);
    }

    public List<String> getProductNames() {return service.getProductNames();}

    public HashMap<Integer, Object[]> getProductPriceMap() {
        return service.getProductPriceMap();
    }

    public HashMap<String, Integer> getProductNamePrice() { return service.getProductNamePrice(); }
}
