package main.java.com.ouibak.erp.domain.statisticsList;

public class StatisticsResult {
    private String dayOfWeek;
    private String productName;
    private double avgTransactionCount;
    private double avgOrderCount;

    // Getters and setters
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAvgTransactionCount() {
        return avgTransactionCount;
    }

    public void setAvgTransactionCount(double avgTransactionCount) {
        this.avgTransactionCount = avgTransactionCount;
    }

    public double getAvgOrderCount() {
        return avgOrderCount;
    }

    public void setAvgOrderCount(double avgOrderCount) {
        this.avgOrderCount = avgOrderCount;
    }
}
