package main.java.com.ouibak.erp.domain.orderList.orderList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class OrderDao {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "franchisee";
    private static final String PASSWORD = "0000";



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
}
