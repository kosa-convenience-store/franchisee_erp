package main.java.com.ouibak.erp.domain.statistics;

import main.java.com.ouibak.erp.dao.Query;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class StatisticsServieImpl implements StatisticsService{
    private final StatisticsDaoImpl dao;
    private static StatisticsServieImpl instance;

    private StatisticsServieImpl() {
        dao = StatisticsDaoImpl.getInstance();
    }

    public static StatisticsServieImpl getInstance() {
        new StatisticsServieImpl();
        if (instance == null) { instance = new StatisticsServieImpl(); }
        return instance;
    }

    @Override
    public void getProductList() throws SQLException {
        String[] arr = dao.getAllProduct(Query.getQuery("getProductListS"));
        System.out.println(Arrays.toString(arr));
        StatisticsVO.getInstance().setProductArr(arr);
//        new StatisticsVO().geti.setProductArr(arr);

//        return arr;
    }

    @Override
    public List<List<Integer>> getStatistics(String productName) throws SQLException {
//        StatisticsVO.getInstance().setStatistics(dao.getStatistics("쿼리", productName));
        return dao.getStatistics("", productName);
    }


}
