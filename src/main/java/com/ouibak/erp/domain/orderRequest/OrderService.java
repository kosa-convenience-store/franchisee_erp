package main.java.com.ouibak.erp.domain.orderRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface OrderService {

    public void orderProduct(List<Object[]> values) throws SQLException;

}
