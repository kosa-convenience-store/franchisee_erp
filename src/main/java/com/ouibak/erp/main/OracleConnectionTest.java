package main.java.com.ouibak.erp.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class OracleConnectionTest {
    public static void main(String[] args) {
        // Oracle JDBC 드라이버 클래스
        String driver = "oracle.jdbc.driver.OracleDriver";
        // 데이터베이스 URL
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        // 사용자명
        String username = "franchisee";
        // 비밀번호
        String password = "0000";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // JDBC 드라이버 로딩
            Class.forName(driver);

            // 데이터베이스 연결
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Oracle Database connected successfully!");

            // SQL 쿼리 실행
            stmt = conn.createStatement();
            String query = "SELECT * FROM DUAL"; // DUAL 테이블은 Oracle에 존재하는 더미 테이블
            rs = stmt.executeQuery(query);

            // 결과 출력
            while (rs.next()) {
                System.out.println("DUAL TABLE DUMMY: " + rs.getString("DUMMY"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        } finally {
            try {
                // 자원 반납
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
