package main.java.com.ouibak.erp.domain.statistics;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface StatisticsDao {

    List<List<Integer>> getStatistics(String sqlQuery, String productName, Date startDate, Date endDate) throws SQLException;

}
