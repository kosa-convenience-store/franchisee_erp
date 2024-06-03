package main.java.com.ouibak.erp.domain.order;

import main.java.com.ouibak.erp.common.dao.DBDao;
import main.java.com.ouibak.erp.common.query.Query;
import main.java.com.ouibak.erp.domain.order.dto.OrderDetailDto;
import main.java.com.ouibak.erp.domain.order.dto.OrderListDto;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends DBDao implements OrderDao {

    @Override
    public int craeteOrder(int franchiseeIdx) throws SQLException {
        CallableStatement cstmt = getCallableStatement(Query.getQuery("createOrder"));
        cstmt.setInt(1, franchiseeIdx);
        cstmt.registerOutParameter(2, Types.INTEGER);
        cstmt.execute();
        int order_idx = cstmt.getInt(2);
        return order_idx;
    }

    @Override
    public void addOrderList(int orderId, List<Object[]> list) throws SQLException {
        PreparedStatement pstmt = getPreparedStatement(Query.getQuery("insertOrderList"));
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


    @Override
    public List<OrderListDto> getRecentOrdersPaging(int pageNumber, int pageSize, int franchiseeIdx) throws SQLException {
        List<OrderListDto> list = new ArrayList<>();
        CallableStatement cstmt = getCallableStatement(Query.getQuery("getOrderList"));
        cstmt.setInt(1, pageNumber);
        cstmt.setInt(2, pageSize);
        cstmt.setInt(3, franchiseeIdx);
        cstmt.registerOutParameter(4, OracleTypes.CURSOR);
        cstmt.execute();

        ResultSet rs = (ResultSet) cstmt.getObject(4);
        while (rs.next()) {
            list.add(new OrderListDto(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getString(4), rs.getTimestamp(5)));
        }
        return list;
    }

    @Override
    public List<OrderDetailDto> getOrderDetails(int orderIdx) throws SQLException {
        List<OrderDetailDto> list = new ArrayList<>();
        CallableStatement cstmt = getCallableStatement(Query.getQuery("getOrderDetail"));
        cstmt.setInt(1, orderIdx);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();

        ResultSet rs = (ResultSet) cstmt.getObject(2);
        while (rs.next()) {
            list.add(new OrderDetailDto(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return list;
    }

    @Override
    public Object[] checkOrderErrors(int franchiseeIdx, Object[] value, int threshold) throws SQLException {
        CallableStatement cstmt = getCallableStatement(Query.getQuery("orderErrorCheck"));
        cstmt.setInt(1, franchiseeIdx);
        cstmt.setInt(2, Integer.parseInt(value[0].toString())); //productIdx
        cstmt.setInt(3, Integer.parseInt(value[1].toString())); //orderQuantity
        cstmt.setInt(4, threshold);
        cstmt.registerOutParameter(5, Types.INTEGER);
        cstmt.registerOutParameter(6, Types.DOUBLE);
        cstmt.registerOutParameter(7,Types.VARCHAR);
        cstmt.execute();
        int isError = cstmt.getInt(5);
        double avgDailyCount = cstmt.getDouble(6);
        String productName = cstmt.getString(7);
        return new Object[] {isError, avgDailyCount, productName};
    }
}
