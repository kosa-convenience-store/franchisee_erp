package main.java.com.ouibak.erp.domain.statistics;

import java.sql.SQLException;
import java.util.List;

public interface StatisticsDao {

    List<List<Integer>> getStatistics(String sqlQuery, String productName) throws SQLException;
    //상품 전체 목록을 가져와 list로 저장하고반환
//    public String[] getAllProduct(String sqlQuery) throws SQLException;

    //
}

