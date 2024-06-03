package main.java.com.ouibak.erp.domain.statistics;

import java.sql.Date;
import java.util.List;

public class StatisticsController {
    StatisticsService service = new StatisticsService();

    public List<List<Integer>> getStastistics(int franchiseeIdx, String productName, Date startDate, Date endDate) {
        return service.getStastistics(franchiseeIdx, productName, startDate, endDate);
    }
}
