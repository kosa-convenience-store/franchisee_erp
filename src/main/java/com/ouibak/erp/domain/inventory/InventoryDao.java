package main.java.com.ouibak.erp.domain.inventory;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDao {
    List<InventoryVO> getInventory( int pageNumber, int pageSize) throws SQLException;
}
