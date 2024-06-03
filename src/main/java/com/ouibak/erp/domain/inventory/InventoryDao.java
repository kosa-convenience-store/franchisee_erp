package main.java.com.ouibak.erp.domain.inventory;

import java.util.List;

public interface InventoryDao {

    List<InventoryDto> getInventory(int pageNumber, int pageSize, int franchiseeIdx) throws Exception;
}
