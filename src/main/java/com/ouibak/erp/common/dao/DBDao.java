package main.java.com.ouibak.erp.common.dao;

import java.sql.*;

public class DBDao {

    public void commitDB() throws SQLException {
        Connection conn = DBConnection.getConnection();
        conn.commit();
    }

    protected ResultSet getData(String sqlQuery) throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        return rs;
    }

    protected PreparedStatement getPreparedStatement(String sqlQuery) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
        return pstmt;
    }

    protected CallableStatement getCallableStatement(String sqlQuery) throws SQLException {
        Connection conn = DBConnection.getConnection();
        CallableStatement cstmt = conn.prepareCall(sqlQuery);
        return cstmt;
    }
}
