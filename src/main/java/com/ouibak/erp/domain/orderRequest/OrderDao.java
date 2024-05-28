package main.java.com.ouibak.erp.domain.orderRequest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface OrderDao {

    // 발주 요청하는 메서드
    public int craeteOrder(String sqlQuery) throws SQLException;

    // 발주 요청 후 상품 목록을 db에 insert하는 메서드
    public void addOrderList(String sqlQuery, int orderId, List<Object[]> list) throws SQLException;
}
