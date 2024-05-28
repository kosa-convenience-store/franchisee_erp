package main.java.com.ouibak.erp.domain.product;

import main.java.com.ouibak.erp.dao.DBDaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProdcutDao extends DBDaoImpl {
    public void setProductData() throws SQLException {
        List<String> productNames = new ArrayList<String>();
        HashMap<Integer, Integer> productIdxPrice = new HashMap<>();
        HashMap<String, Integer> productNamePrice = new HashMap<>();

        String sql = "select product_idx, product_name, product_price from tbl_product";
        ResultSet rs = getData(sql);

        while (rs.next()) {
            int product_idx = rs.getInt(1);
            String product_name = rs.getString(2);
            int product_price = rs.getInt(3);

            productNames.add(product_name);
            productIdxPrice.put(product_idx, product_price);
            productNamePrice.put(product_name, product_price);
        }
        ProductVO.setProductNames(productNames);
        ProductVO.setProductIdxPrice(productIdxPrice);
        ProductVO.setProductNamePrice(productNamePrice);
    }

}
