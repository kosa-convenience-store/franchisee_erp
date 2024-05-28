package main.java.com.ouibak.erp.domain.statistics;

import java.util.List;

public class StatisticsVO {
    private static StatisticsVO statisticsVO;
    private String[] productArr;
    private List<List<Integer>> statistics;

    public StatisticsVO(){}

    public static StatisticsVO getInstance(){
        if(statisticsVO == null){ statisticsVO = new StatisticsVO(); }
        return statisticsVO;
    }

//    public StatisticsVO(String[] productArr, List<Object> statistics) {
//        this.productArr = productArr;
//        this.statistics = statistics;
//    }

    public String[] getProductArr() {
        return productArr;
    }

    public void setProductArr(String[] productArr) {
        this.productArr = productArr;
    }

    public List<List<Integer>> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<List<Integer>> statistics) {
        this.statistics = statistics;
    }
}
