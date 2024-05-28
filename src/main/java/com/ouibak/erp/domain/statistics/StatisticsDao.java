package main.java.com.ouibak.erp.domain.statistics;

import java.sql.SQLException;

public interface StatisticsDao {
    //상품 전체 목록을 가져와 list로 저장하고반환
    public String[] getAllProduct(String sqlQuery) throws SQLException;

    //
}

