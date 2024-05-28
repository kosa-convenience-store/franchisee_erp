package main.java.com.ouibak.erp.domain.statistics;

import main.java.com.ouibak.erp.dao.DBDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDaoImpl extends DBDaoImpl implements StatisticsDao{
    private static StatisticsDaoImpl dao;

    public StatisticsDaoImpl() {}

    public static StatisticsDaoImpl getInstance(){
        if(dao == null){ dao = new StatisticsDaoImpl();}
        return dao;
    }

    @Override
    public List<List<Integer>> getStatistics(String sqlQuery, String productName) throws SQLException {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        String sql = "SELECT avg_transaction_count, avg_order_count FROM WEEKLY_STATISTICS_VIEW\n" +
                "WHERE PRODUCT_NAME = ?";
        PreparedStatement pstmt = getPreStmt(sql);
        pstmt.setString(1, productName);
        pstmt.executeQuery();
        ResultSet rs = pstmt.getResultSet();
        while (rs.next()){

            list.get(0).add(rs.getInt(1));
            list.get(1).add(rs.getInt(2));
        }
        pstmt.close();
        System.out.println(list.toString());
        return list;
    }
}
