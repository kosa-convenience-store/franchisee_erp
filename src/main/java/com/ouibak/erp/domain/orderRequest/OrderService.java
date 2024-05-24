package main.java.com.ouibak.erp.domain.order;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface OrderService {
    public Map<String, Integer> getProductMap() throws SQLException;

    public void orderProduct(List<Object[]> values) throws SQLException;

}
