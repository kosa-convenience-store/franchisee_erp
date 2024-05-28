package main.java.com.ouibak.erp.domain.statisticsList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.java.com.ouibak.erp.dao.Query;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.OracleResultSet;

public class StatisticsDao {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "franchisee";
    private static final String PASSWORD = "0000";

    public List<StatisticsResult> getStatistics(int franchiseeIdx, int productId, Date startDate, Date endDate) {
        List<StatisticsResult> statisticsResults = new ArrayList<>();
        String query = Query.getQuery("getStatistics");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setInt(1, franchiseeIdx);
            callableStatement.setInt(2, productId);
            callableStatement.setDate(3, startDate);
            callableStatement.setDate(4, endDate);
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);

            // 프로시저 실행
            callableStatement.execute();

            // 결과 가져오기
            try (OracleResultSet resultSet = (OracleResultSet) callableStatement.getObject(5)) {
                while (resultSet.next()) {
                    StatisticsResult result = new StatisticsResult();
                    result.setDayOfWeek(resultSet.getString("day_of_week"));
                    result.setProductName(resultSet.getString("product_name"));
                    result.setAvgTransactionCount(resultSet.getDouble("avg_transaction_count"));
                    result.setAvgOrderCount(resultSet.getDouble("avg_order_count"));
                    statisticsResults.add(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statisticsResults;
    }
}