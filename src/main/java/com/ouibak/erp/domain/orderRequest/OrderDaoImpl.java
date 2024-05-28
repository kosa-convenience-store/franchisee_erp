package main.java.com.ouibak.erp.domain.orderRequest;

import main.java.com.ouibak.erp.dao.DBDaoImpl;

import java.sql.*;
import java.util.List;

public class OrderDaoImpl extends DBDaoImpl implements OrderDao{
    private static OrderDaoImpl dao;

    public OrderDaoImpl(){}

    public static OrderDaoImpl getInstance(){
        if (dao == null){ dao = new OrderDaoImpl();}
        return dao;
    }

    @Override
    public int craeteOrder(String sqlQuery) throws SQLException {
        CallableStatement cstmt = getCStmt(sqlQuery);
        cstmt.setInt(1, 1);
        cstmt.registerOutParameter(2, Types.INTEGER);
        cstmt.execute();
        int order_idx = cstmt.getInt(2);
        return order_idx;
    }

    @Override
    public void addOrderList(String sqlQuery, int orderId, List<Object[]> list) throws SQLException {
        PreparedStatement pstmt = getPreStmt(sqlQuery);
        for (Object[] arr: list) {
            //franchisee_num
            pstmt.setInt(1, orderId);
            //product name
            pstmt.setString(2, String.valueOf(arr[0]));
            //count
            pstmt.setInt(3, Integer.parseInt((String.valueOf(arr[1]))));

            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.clearBatch();
    }
}
