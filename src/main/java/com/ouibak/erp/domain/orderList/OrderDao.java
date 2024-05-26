package main.java.com.ouibak.erp.domain.orderList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.java.com.ouibak.erp.dao.DBConnection;
import main.java.com.ouibak.erp.dao.DBDaoImpl;
import oracle.jdbc.OracleTypes;

public class OrderDao extends DBDaoImpl {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "franchisee";
    private static final String PASSWORD = "0000";

    // 발주 조회
    public List<Order> getRecentOrdersPaging(int pageNumber, int pageSize) {
        List<Order> orders = new ArrayList<>();
        String query = "{CALL get_recent_orders_paging(?, ?, ?)}";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setInt(1, pageNumber);
            callableStatement.setInt(2, pageSize);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);

            callableStatement.execute();

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(3)) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderIdx(resultSet.getInt("order_idx"));
                    order.setFIdx(resultSet.getInt("franchisee_idx"));
                    order.setOrderDate(resultSet.getTimestamp("order_date"));
                    order.setOrderState(resultSet.getString("order_state"));
                    order.setArriveDate(resultSet.getTimestamp("arrive_date"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // 발주 상세 조회
    public List<OrderDetail> getOrderDetails(int orderIdx) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "{CALL get_order_details(?, ?)}";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setInt(1, orderIdx);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute(); // 프로시저 실행

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(2)) {
                while (resultSet.next()) {
                    OrderDetail detail = new OrderDetail();
                    detail.setOrderIdx(resultSet.getInt("order_idx"));
                    detail.setProductName(resultSet.getString("product_name"));
                    detail.setCount(resultSet.getInt("count"));
                    orderDetails.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

}


