package main.java.com.ouibak.erp.domain.statistics;

import java.sql.SQLException;
import java.util.List;

public interface StatisticsDao {

    List<List<Integer>> getStatistics(String sqlQuery, String productName) throws SQLException;

}
