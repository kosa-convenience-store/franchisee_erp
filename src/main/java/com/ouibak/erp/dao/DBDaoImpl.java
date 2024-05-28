package main.java.com.ouibak.erp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDaoImpl implements DBDao {
//    private static DBDaoImpl instance = null;
//    private DBDaoImpl() {}
//    public static DBDaoImpl getInstance() {
//        if (instance == null) {instance = new DBDaoImpl();}
//        return instance;
//    }

    //---------------------------------------------------------------
    @Override
    public void commitDB() throws SQLException {
        Connection conn = DBConnection.getConnection();
        conn.commit();
    }

    /*
    SELECT DATA
     */
    @Override
    public ResultSet getData(String sqlQuery) throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        return rs;
    }

    @Override
    public PreparedStatement getPreStmt(String sqlQuery) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
        return pstmt;
    }

    @Override
    public CallableStatement getCStmt(String sqlQuery) throws SQLException {
        Connection conn = DBConnection.getConnection();
        CallableStatement cstmt = conn.prepareCall(sqlQuery);
        return cstmt;
    }

    /*
    Int
     */

    // IN데이터 없이 RETURN : INT데이터 1개 반환의 경우
    @Override
    public int getIntData(String sqlQuery) throws SQLException {
        ResultSet rs = getData(sqlQuery);
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public int[] getIntData(String sqlQuery, int size) throws SQLException {
        ResultSet rs = getData(sqlQuery);
        int[] result = new int[size];
        int idx = 0;
        while (rs.next()) {
            for (int i = 0; i < size; i++) {
                result[i] = rs.getInt(i + 1);
            }
        }
        return result;
    }

    @Override
    public int getIntData(String sqlQuery, String[] values) throws SQLException {
        PreparedStatement pstmt = getPreStmt(sqlQuery);
        pstmt.setString(1, values[0]);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    /*
    String
     */

    @Override
    public String getStringData(String sqlQuery) throws SQLException {
        ResultSet rs = getData(sqlQuery);
        rs.next();
        return rs.getString(1);
    }

    @Override
    public String[] getStringData(String sqlQuery, int size) throws SQLException {
        ResultSet rs = getData(sqlQuery);
        String[] result = new String[size];
        while (rs.next()) {
            for (int i = 0; i < size; i++) {
                result[i] = rs.getString(i + 1);
            }
        }
        return result;
    }

    @Override
    public String[][] getStringArr(String sqlQuery, int size) throws SQLException {
        ResultSet rs = getData(sqlQuery);
        List<String[]> result = new ArrayList<>();
        while (rs.next()) {
            String[] row = new String[size];
            for (int i = 0; i < size; i++) {
                row[i] = rs.getString(i+1);
            }
            result.add(row);
        }
        return result.toArray(new String[result.size()][size]);
    }

//    public String[] getStringArray(String sqlQuery) throws SQLException {
//        ResultSet rs = getData(sqlQuery);
//        ArrayList<String> result = new ArrayList<>();
//        while (rs.next()) {
//
//        }
//    }

    @Override
    public String[] getStringData(String sqlQuery, int size, String[] values) throws SQLException {
        PreparedStatement pstmt = getPreStmt(sqlQuery);
        for (int i = 0; i < values.length; i++) {
            pstmt.setString(i + 1, values[i]);
        }
        ResultSet rs = pstmt.executeQuery();
        List<String[]> result = getResultList(rs, size);
        return result.get(0);
    }


    @Override
    public List<String[]> getResultList(ResultSet rs, int size) throws SQLException {
        List<String[]> result = new ArrayList<String[]>();
        while (rs.next()) {
            String[] row = new String[size];
            for (int i = 0; i < size; i++) {
                row[i] = rs.getString(i + 1);
            }
            result.add(row);
        }
        return result;
    }



    /*
    INSERT DATA
     */
    public void testtest(String sqlQuery) throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sqlQuery);
        stmt.close();
    }
    public List<String> testtestString(String sqlQuery) throws SQLException {
        Connection conn = DBConnection.getConnection();
        CallableStatement cstmt = getCStmt(sqlQuery);
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.execute();
        List<String> list = cstmt.getObject(1, List.class);
        return list;
    }

    public void insertIntData(String sqlQuery, List<int[]> values) throws SQLException {
        PreparedStatement pstmt = getPreStmt(sqlQuery);
        for (int[] arr: values){
            for (int i = 0; i < arr.length; i++) {
                pstmt.setInt(i + 1, arr[i]);
            }
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.clearBatch(); pstmt.close();
    }


}
