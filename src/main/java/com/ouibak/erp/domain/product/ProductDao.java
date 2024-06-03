package main.java.com.ouibak.erp.domain.product;

import main.java.com.ouibak.erp.common.dao.DBDao;
import main.java.com.ouibak.erp.common.query.Query;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends DBDao {

    List<ProductDto> getAllProducts() throws SQLException {
        List<ProductDto> list = new ArrayList<>();

        CallableStatement cstmt = getCallableStatement(Query.getQuery("getProductList"));
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rs = (ResultSet) cstmt.getObject(1);
        while (rs.next()) {
            list.add(new ProductDto(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return list;
    }
}
