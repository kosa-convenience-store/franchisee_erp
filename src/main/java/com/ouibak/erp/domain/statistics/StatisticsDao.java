package main.java.com.ouibak.erp.domain.statistics;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface StatisticsDao {
    List<StatisticsDto> getStastistics(int franchiseeIdx, String productName, Date startDate, Date endDate) throws SQLException;
}
