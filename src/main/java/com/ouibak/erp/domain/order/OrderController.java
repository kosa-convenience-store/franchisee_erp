package main.java.com.ouibak.erp.domain.order;

import main.java.com.ouibak.erp.domain.order.dto.OrderDetailDto;
import main.java.com.ouibak.erp.domain.order.dto.OrderListDto;

import java.util.List;

public class OrderController {
    OrderService service = new OrderService();

    public void orderProducts(int franchiseeIdx, List<Object[]> values) {
        service.orderProducts(franchiseeIdx, values);
    }

    public List<OrderListDto> getRecentOrderPaging(int pageNumber, int pageSize, int frachiseeIdx) {
        return service.getRecentOrderPaging(pageNumber, pageSize, frachiseeIdx);
    }

    public List<OrderDetailDto> getOrderDetails(int orderIdx) {
        return service.getOrderDetails(orderIdx);
    }

    public String checkOrderErrors(int franchiseeIdx, List<Object[]> values, int threshold) {
        return service.checkOrderErrors(franchiseeIdx, values, threshold);
    }
}
