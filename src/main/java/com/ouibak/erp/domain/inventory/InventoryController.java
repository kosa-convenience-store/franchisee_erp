package main.java.com.ouibak.erp.domain.inventory;

import java.util.List;

public class InventoryController {
    InventoryService service = new InventoryService();
    public List<InventoryDto> getInventory(int pageNumber, int pageSize, int franchiseeIdx) {
        return service.getInventory(pageNumber, pageSize, franchiseeIdx);
    }
}
