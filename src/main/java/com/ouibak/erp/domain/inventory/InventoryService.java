package main.java.com.ouibak.erp.domain.inventory;

import java.util.List;

public class InventoryService {
    InventoryDao dao = new InventoryDaoImpl();

    List<InventoryDto> getInventory(int pageNumber, int pageSize, int franchiseeIdx) {
        try {
            return dao.getInventory(pageNumber, pageSize, franchiseeIdx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
