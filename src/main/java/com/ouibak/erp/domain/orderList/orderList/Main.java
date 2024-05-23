package main.java.com.ouibak.erp.domain.orderList.orderList;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        int pageNumber = 1;
        int pageSize = 10;

        List<Order> recentOrders = orderDao.getRecentOrdersPaging(pageNumber, pageSize);

        for (Order order : recentOrders) {
            System.out.println("Order ID: " + order.getOrderIdx());
            System.out.println("Franchisee ID: " + order.getFIdx());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Order State: " + order.getOrderState());
            System.out.println("Arrive Date: " + order.getArriveDate());
            System.out.println("----------");
        }
    }
}

