package main.java.com.ouibak.erp.domain.statistics;

import main.java.com.ouibak.erp.dao.DBDaoImpl;
import main.java.com.ouibak.erp.domain.franchisee.FranchiseeVO;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticsDaoImpl extends DBDaoImpl implements StatisticsDao {
    private static StatisticsDaoImpl dao;

    public StatisticsDaoImpl() {
    }

    public static StatisticsDaoImpl getInstance() {
        if (dao == null) {
            dao = new StatisticsDaoImpl();
        }
        return dao;
    }

    @Override
    public List<List<Integer>> getStatistics(String sqlQuery, String productName, Date startDate, Date endDate) throws SQLException {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
        list.add(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
        CallableStatement cstmt = getCStmt(sqlQuery);

        cstmt.setInt(1, FranchiseeVO.getFranchiseeId());
        cstmt.setString(2, productName);
        cstmt.setDate(3, (java.sql.Date) startDate);
        cstmt.setDate(4, endDate);
        cstmt.registerOutParameter(5, OracleTypes.CURSOR);

        cstmt.execute();

        // 결과 가져오기
        try (OracleResultSet resultSet = (OracleResultSet) cstmt.getObject(5)) {
            while (resultSet.next()) {

                String week = resultSet.getString("day_of_week");
                System.out.println(week);
                int idx = -1;
                switch (week) {
                    case "SUN" : idx = 0; break;
                    case "MON" : idx = 1; break;
                    case "TUE" : idx = 2; break;
                    case "WED" : idx = 3; break;
                    case "THU" : idx = 4; break;
                    case "FRI" : idx = 5; break;
                    case "SAT" : idx = 6; break;
                }
                list.get(0).set(idx, resultSet.getInt("avg_transaction_count"));
                list.get(1).set(idx, resultSet.getInt("avg_order_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
