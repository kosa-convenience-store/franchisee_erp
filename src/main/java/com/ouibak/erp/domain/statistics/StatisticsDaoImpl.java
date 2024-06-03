package main.java.com.ouibak.erp.domain.statistics;

import main.java.com.ouibak.erp.common.dao.DBDao;
import main.java.com.ouibak.erp.common.query.Query;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDaoImpl extends DBDao implements StatisticsDao {

    @Override
    public List<StatisticsDto> getStastistics(int franchiseeIdx, String productName, Date startDate, Date endDate) throws SQLException {
        List<StatisticsDto> list = new ArrayList<>();
        CallableStatement cstmt = getCallableStatement(Query.getQuery("getWeeklyStatistics"));
        cstmt.setInt(1, franchiseeIdx);
        cstmt.setString(2, productName);
        cstmt.setDate(3, startDate);
        cstmt.setDate(4, endDate);
        cstmt.registerOutParameter(5, OracleTypes.CURSOR);
        cstmt.execute();

        ResultSet rs = (ResultSet) cstmt.getObject(5);
        while (rs.next()) {
            list.add(new StatisticsDto(rs.getString(1),  rs.getInt(2), rs.getInt(3)));
        }
        return list;
    }
}
