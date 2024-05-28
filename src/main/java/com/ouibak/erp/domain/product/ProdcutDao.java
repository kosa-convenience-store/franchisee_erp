package main.java.com.ouibak.erp.domain.product;

import main.java.com.ouibak.erp.dao.DBDaoImpl;
import main.java.com.ouibak.erp.dao.Query;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
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
        HashMap<Integer, Object[]> productPriceMap = new HashMap<>();

        CallableStatement cstmt = getCStmt(Query.getQuery("getProductList"));
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rs = (ResultSet) cstmt.getObject(1);
        while (rs.next()) {
            int product_idx = rs.getInt(1);
            String product_name = rs.getString(2);
            int product_price = rs.getInt(3);

            productNames.add(product_name);
            productIdxPrice.put(product_idx, product_price);
            productNamePrice.put(product_name, product_price);
            productPriceMap.put(product_idx, new Object[] { product_name, product_price });
        }

        ProductVO.setProductNames(productNames);
        ProductVO.setProductIdxPrice(productIdxPrice);
        ProductVO.setProductNamePrice(productNamePrice);
        ProductVO.setProductPriceMap(productPriceMap);
    }

}
