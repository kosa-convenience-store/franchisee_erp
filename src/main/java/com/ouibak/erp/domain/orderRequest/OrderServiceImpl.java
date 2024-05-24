package main.java.com.ouibak.erp.domain.order;

import main.java.com.ouibak.erp.dao.DBDaoImpl;
import main.java.com.ouibak.erp.dao.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService{
    private OrderDaoImpl dao;
    private static OrderServiceImpl instance;

    private OrderServiceImpl() {
        dao = OrderDaoImpl.getInstance();
    }

    public static OrderServiceImpl getInstance() {
        if (instance == null) { instance = new OrderServiceImpl(); }
        return instance;
    }

    @Override
    public Map<String, Integer> getProductMap() throws SQLException {
        return dao.getAllProduct(Query.getQuery("getProductList"));
    }

    @Override
    public void orderProduct(List<Object[]> values) throws SQLException {
        int orderId = dao.craeteOrder(Query.getQuery("createOrder"));
        System.out.println("orderId = " + orderId);
        dao.addOrderList(Query.getQuery("insertOrderList"), orderId, values);
        dao.commitDB();
    }
}