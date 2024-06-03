package main.java.com.ouibak.erp.domain.product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductService {
    private static List<ProductDto> productDtoList;

    private static List<String> productNames = new ArrayList<>();
    private static HashMap<Integer, String> productIdxName = new HashMap<>();
    private static HashMap<String, Integer> productNameIdx = new HashMap<>();
    private static HashMap<String , Integer> productNamePrice = new HashMap<>();
    private HashMap<Integer, Object[]> productPriceMap = new HashMap<>();
    ProductDao dao = new ProductDao();

    private void getAllProducts() {
        if (productDtoList == null) {
            try {
                productDtoList = dao.getAllProducts();
                for (ProductDto productDto : productDtoList) {
                    int productIdx = productDto.getProductIdx();
                    String productName = productDto.getProductName();
                    int productPrice = productDto.getProductPrice();

                    productNames.add(productName);
                    productIdxName.put(productIdx, productName);
                    productNameIdx.put(productName, productIdx);
                    productNamePrice.put(productName, productPrice);
                    productPriceMap.put(productIdx, new Object[] {productName, productPrice});
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    int getProductIdxByName(String productName){
        getAllProducts();
        return productNameIdx.get(productName);
    }

    List<String> getProductNames() {
        getAllProducts();
        return productNames;
    }

    HashMap<Integer, Object[]>  getProductPriceMap(){
        getAllProducts();
        return productPriceMap;
    }

    HashMap<String, Integer> getProductNamePrice(){
        getAllProducts();
        return productNamePrice;
    }
}
