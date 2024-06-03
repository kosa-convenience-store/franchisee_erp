package main.java.com.ouibak.erp.domain.franchisee;

import main.java.com.ouibak.erp.common.dao.DBDao;
import main.java.com.ouibak.erp.common.query.Query;
import main.java.com.ouibak.erp.domain.franchisee.dto.FranchieeOrderRateDto;
import main.java.com.ouibak.erp.domain.franchisee.dto.FranchiseeDto;
import main.java.com.ouibak.erp.domain.franchisee.dto.FranchiseeInfoDto;
import main.java.com.ouibak.erp.domain.franchisee.dto.FranchiseeProductRankingDto;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FranchiseeDaoImpl extends DBDao implements FranchiseeDao {
    @Override
    public FranchiseeDto validateLogin(String loginId, String password) {
        try (CallableStatement cstmt = getCallableStatement(Query.getQuery("login"))){
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, loginId);
            cstmt.setString(3, password);
            cstmt.execute();
            return new FranchiseeDto(cstmt.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
            return new FranchiseeDto(-99);
        }

    }

    @Override
    public FranchiseeInfoDto getFranchiseeInfo(int franchiseeIdx) throws SQLException {
        CallableStatement cstmt = getCallableStatement(Query.getQuery("getFranchiseeInfo"));
        cstmt.setInt(1, franchiseeIdx);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rs = (ResultSet) cstmt.getObject(2);
        rs.next();
        return new FranchiseeInfoDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
    }

    @Override
    public List<FranchiseeProductRankingDto> getFranchiseeRank(int franchiseeIdx) throws SQLException {
        List<FranchiseeProductRankingDto> list = new ArrayList<>();
        CallableStatement cstmt = getCallableStatement(Query.getQuery("getFranchiseeProductRank"));
        cstmt.setInt(1, franchiseeIdx);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet rs = (ResultSet) cstmt.getObject(2);
        while(rs.next()) {
            list.add(new FranchiseeProductRankingDto(rs.getString(1), rs.getInt(2)));
        }
        return list;
    }

    @Override
    public FranchieeOrderRateDto getFranchieeOrderRate(int franchiseeIdx) throws SQLException {
        CallableStatement cstmt = getCallableStatement(Query.getQuery("getOrderRate"));
        cstmt.setInt(1, franchiseeIdx);
        cstmt.registerOutParameter(2, Types.INTEGER);
        cstmt.execute();
        int rs = cstmt.getInt(2);
        return new FranchieeOrderRateDto(rs);
    }

    @Override
    public void updateOrderRate(int franchiseeIdx, int orderRate) throws SQLException {
        PreparedStatement pstmt = getPreparedStatement(Query.getQuery("updateOrderRate"));
        pstmt.setInt(1, orderRate);
        pstmt.setInt(2, franchiseeIdx);
        pstmt.executeUpdate();
        commitDB();
    }
}
