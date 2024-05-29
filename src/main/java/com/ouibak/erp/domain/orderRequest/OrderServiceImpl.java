package main.java.com.ouibak.erp.domain.orderRequest;

import main.java.com.ouibak.erp.dao.Query;

import java.sql.*;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDaoImpl dao;
    private static OrderServiceImpl instance;

    private OrderServiceImpl() {
        dao = OrderDaoImpl.getInstance();
    }

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public void orderProduct(List<Object[]> values) throws SQLException {
        int orderId = dao.craeteOrder(Query.getQuery("createOrder"));
        System.out.println("orderId = " + orderId);
        dao.addOrderList(Query.getQuery("insertOrderList"), orderId, values);
        dao.commitDB();
    }

    public String checkOrderErrors(int franchiseeIdx, List<Object[]> values, int threshold) throws SQLException {
        StringBuilder warningMessages = new StringBuilder();
        boolean hasError = false;

        for (Object[] value : values) {
            int productId = Integer.parseInt(value[0].toString());
            int orderQuantity = Integer.parseInt(value[1].toString());
            String productName = getProductNameById(productId);

            int isError = 0;
            double avgDailyCount = 0;
            CallableStatement cstmt = dao.getCStmt(Query.getQuery("orderErrorCheck"));
            cstmt.setInt(1, franchiseeIdx);
            cstmt.setInt(2, productId);
            cstmt.setInt(3, orderQuantity);
            cstmt.setInt(4, threshold);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.DOUBLE);
            cstmt.execute();
            isError = cstmt.getInt(5);
            avgDailyCount = cstmt.getDouble(6);
            cstmt.close();

            if (isError == 1) {
                hasError = true;
                warningMessages.append("물품: ").append(productName)
                        .append(" - 발주량이 평균 결제량(").append(avgDailyCount).append(")보다 ")
                        .append(threshold).append("% 이상 많습니다. 계속하시겠습니까?\n");
            } else if (isError == 2) {
                hasError = true;
                warningMessages.append("물품: ").append(productName)
                        .append(" - 발주량이 평균 결제량(").append(avgDailyCount).append(")보다 ")
                        .append(threshold).append("% 이상 적습니다. 계속하시겠습니까?\n");
            }
        }

        return hasError ? warningMessages.toString() : "";
    }

    public int getProductIdByName(String productName) throws SQLException {
        String query = "SELECT PRODUCT_IDX FROM TBL_PRODUCT WHERE PRODUCT_NAME = ?";
        PreparedStatement pstmt = dao.getPreStmt(query);
        pstmt.setString(1, productName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("PRODUCT_IDX");
        } else {
            throw new SQLException("No product found with name: " + productName);
        }
    }

    public String getProductNameById(int productId) throws SQLException {
        String query = "SELECT PRODUCT_NAME FROM TBL_PRODUCT WHERE PRODUCT_IDX = ?";
        PreparedStatement pstmt = dao.getPreStmt(query);
        pstmt.setInt(1, productId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("PRODUCT_NAME");
        } else {
            throw new SQLException("No product found with id: " + productId);
        }
    }
}
