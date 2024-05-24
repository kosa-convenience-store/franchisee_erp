package main.java.com.ouibak.erp.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DBDao {
    void commitDB() throws SQLException;

    /*
        SELECT DATA
    */
    ResultSet getData(String sqlQuery) throws SQLException;
    PreparedStatement getPreStmt(String sqlQuery) throws SQLException;
    CallableStatement getCStmt(String sqlQuery) throws SQLException;

    // IN데이터 없이 RETURN : INT데이터 1개 반환의 경우
    int getIntData(String sqlQuery) throws SQLException;

    // IN데이터 없이 RETURN : INT 데이터 여러개 반환의 경우
    int[] getIntData(String sqlQuery, int size) throws SQLException;

    // IN데이터 있이 RETURN : INT 1개 반환
    int getIntData(String sqlQuery, String[] values) throws SQLException;
//    int getIntData(String sqlQuery, int[] values) throws SQLException;
//    int getIntData(String sqlQuery, Object[] values) throws SQLException;

    // IN데이터 있이 RETURN : INT 여러개 반환
    //    int getIntData(String sqlQuery, int size, String[] values) throws SQLException;
    //    int getIntData(String sqlQuery, int size, int[] values) throws SQLException;
    //    int getIntData(String sqlQuery, int size, Object[] values) throws SQLException;


    //

    // IN 데이터 없이 RETURN : STRING 1개 반환
    String getStringData(String sqlQuery) throws SQLException;

    // IN 데이터 없이 RETURN : STRING 여러개 반환
    String[] getStringData(String sqlQuery, int size) throws SQLException;

    String[][] getStringArr(String sqlQuery, int size) throws SQLException;

    // IN 데이터 있이 RETURN : STRING 여러개 반환
    String[] getStringData(String sqlQuery, int size, String[] values) throws SQLException;

    List<String[]> getResultList(ResultSet rs, int size) throws SQLException;

    /*
    INSERT DATA
     */
}

