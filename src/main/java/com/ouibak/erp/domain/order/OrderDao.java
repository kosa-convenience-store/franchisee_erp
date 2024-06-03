package main.java.com.ouibak.erp.domain.order;

import main.java.com.ouibak.erp.domain.order.dto.OrderDetailDto;
import main.java.com.ouibak.erp.domain.order.dto.OrderListDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    int craeteOrder(int franchiseeIdx) throws SQLException;

    List<OrderListDto> getRecentOrdersPaging(int pageNumber, int pageSize, int franchiseeIdx) throws SQLException;

    List<OrderDetailDto> getOrderDetails(int orderIdx) throws SQLException;

    void addOrderList(int orderId, List<Object[]> list) throws SQLException;

    Object[] checkOrderErrors(int franchiseeIdx, Object[] value, int threshold) throws SQLException;
}
