package main.java.com.ouibak.erp.domain.order;

import main.java.com.ouibak.erp.domain.order.dto.OrderDetailDto;
import main.java.com.ouibak.erp.domain.order.dto.OrderListDto;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    OrderDao dao = new OrderDaoImpl();

    void orderProducts(int franchiseeIdx, List<Object[]> values) {
        try {
            int orderId = dao.craeteOrder(franchiseeIdx);
            dao.addOrderList(orderId, values);
            OrderDaoImpl daoImpl = (OrderDaoImpl) dao;
            daoImpl.commitDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<OrderListDto> getRecentOrderPaging(int currentPage, int pageSize, int franchiseeIdx) {
        try {
            return dao.getRecentOrdersPaging(currentPage, pageSize, franchiseeIdx);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<OrderDetailDto> getOrderDetails(int orderIdx) {
        try {
            return dao.getOrderDetails(orderIdx);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    String checkOrderErrors(int franchiseeIdx, List<Object[]> values, int threshold) {
        StringBuilder warningMessages = new StringBuilder();
        boolean hasError = false;

        for (Object[] value : values) {
            Object[] result = null;
            try {
                result = dao.checkOrderErrors(franchiseeIdx, value, threshold);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            int isError = Integer.parseInt(result[0].toString());
            String productName = String.valueOf(result[2]);
            double avgDailyCount = (double) result[1];
            if (isError == 1) {
                hasError = true;
                warningMessages.append("물품: ").append(productName) //productName
                        .append(" - 발주량이 평균 결제량(").append(avgDailyCount).append(")보다 ")
                        .append(threshold).append("% 이상 많습니다. 계속하시겠습니까?\n");
            } else if (isError == 2) {
                hasError = true;
                warningMessages.append("물품: ").append(productName)
                        .append(" - 발주량이 평균 결제량(").append(avgDailyCount).append(")보다 ")
                        .append(threshold).append("% 이상 적습니다. 계속하시겠습니까?\n");
            }
        }
        return hasError ? warningMessages.toString() : "";
    }
}
