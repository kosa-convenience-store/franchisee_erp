package main.java.com.ouibak.erp.domain.statistics;

public class StatisticsDto {
    private String dayOfWeek;
    private int avgTransactionCount;
    private int avgOrderCount;

    public StatisticsDto(String dayOfWeek, int avgTransactionCount, int avgOrderCount) {
        this.dayOfWeek = dayOfWeek;
        this.avgTransactionCount = avgTransactionCount;
        this.avgOrderCount = avgOrderCount;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getAvgTransactionCount() {
        return avgTransactionCount;
    }

    public int getAvgOrderCount() {
        return avgOrderCount;
    }
}
