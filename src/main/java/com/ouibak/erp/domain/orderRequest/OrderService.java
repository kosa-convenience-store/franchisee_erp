package main.java.com.ouibak.erp.domain.orderRequest;

import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

public interface OrderService {

    public void orderProduct(List<Object[]> values) throws SQLException;

    public String checkOrderErrors(int franchiseeIdx, List<Object[]> values, int threshold) throws SQLException;
}
