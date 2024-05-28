package main.java.com.ouibak.erp.domain.product;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ProductVO {
    private static ProductVO vo;
    private static List<Integer>  productIdx;
    private static List<String> productNames;
    private static HashMap<Integer, Integer> productIdxPrice;
    private static HashMap<Integer, String> productIdxName;
    private static HashMap<String, Integer> productNamePrice;

    public static ProductVO getInstance(){
        if(vo == null){
            vo = new ProductVO();
            ProdcutDao dao = new ProdcutDao();
            try {
                dao.setProductData();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return vo;
    }

    public static List<Integer> getProductIdx() {
        if (productIdx == null) {getInstance();}
        return productIdx;
    }

    public static void setProductIdx(List<Integer> productIdx) {
        ProductVO.productIdx = productIdx;
    }

    public static List<String> getProductNames() {
        if (productNames == null) {getInstance();}
        return productNames;
    }

    public static void setProductNames(List<String> productNames) {
        ProductVO.productNames = productNames;
    }

    public static HashMap<Integer, Integer> getProductIdxPrice() {
        if (productIdxPrice == null) {getInstance();}
        return productIdxPrice;
    }

    public static void setProductIdxPrice(HashMap<Integer, Integer> productIdxPrice) {
        ProductVO.productIdxPrice = productIdxPrice;
    }

    public static HashMap<Integer, String> getProductIdxName() {
        if (productIdxName == null) {getInstance();}
        return productIdxName;
    }

    public static void setProductIdxName(HashMap<Integer, String> productIdxName) {
        ProductVO.productIdxName = productIdxName;
    }

    public static HashMap<String, Integer> getProductNamePrice() {
        if (productNamePrice == null) {getInstance();}
        return productNamePrice;
    }

    public static void setProductNamePrice(HashMap<String, Integer> productNamePrice) {
        ProductVO.productNamePrice = productNamePrice;
    }

}
