package main.java.com.ouibak.erp.domain.statistics;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticsService {
    StatisticsDao dao = new StatisticsDaoImpl();

    List<List<Integer>> getStastistics(int franchiseeIdx, String productName, Date startDate, Date endDate) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
        list.add(Arrays.asList(0, 0, 0, 0, 0, 0, 0));

        try {
            List<StatisticsDto> stastisticsList = dao.getStastistics(franchiseeIdx, productName, startDate, endDate);
            for (StatisticsDto dto : stastisticsList) {
                String week = dto.getDayOfWeek();
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
                list.get(0).set(idx, dto.getAvgTransactionCount());
                list.get(1).set(idx, dto.getAvgOrderCount());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
