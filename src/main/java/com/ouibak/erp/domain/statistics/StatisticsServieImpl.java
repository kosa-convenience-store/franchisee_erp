package main.java.com.ouibak.erp.domain.statistics;

import main.java.com.ouibak.erp.dao.Query;

import java.sql.Date;
import java.sql.SQLException;
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
    public List<List<Integer>> getStatistics(String productName, Date startDate, Date endDate) throws SQLException {
        return dao.getStatistics(Query.getQuery("getWeeklyStatistics"), productName, startDate, endDate);
    }


}