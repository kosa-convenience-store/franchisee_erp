package main.java.com.ouibak.erp.domain.statisticsList;

import java.sql.Date;
import java.util.List;

public class Statistics {
    public static void main(String[] args) {
        int franchiseeIdx = 1; // 예시 가맹점 번호
        int productId = 1; // 예시 물품번호
        Date startDate = Date.valueOf("2024-05-01"); // 예시 시작 날짜
        Date endDate = Date.valueOf("2024-05-21");   // 예시 도착 날짜

        StatisticsService statisticsService = new StatisticsService();
        List<StatisticsResult> results = statisticsService.getStatistics(franchiseeIdx, productId, startDate, endDate);

        for (StatisticsResult result : results) {
            System.out.println("Day of Week: " + result.getDayOfWeek());
            System.out.println("Product Name: " + result.getProductName());
            System.out.println("Average Transaction Quantity: " + result.getAvgTransactionCount());
            System.out.println("Average Order Quantity: " + result.getAvgOrderCount());
            System.out.println("-----------------------------");
        }
    }
}