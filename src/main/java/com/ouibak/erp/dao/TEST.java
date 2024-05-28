package main.java.com.ouibak.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TEST {
    public static void main(String[] args) throws SQLException {
        Connection con = null;

        con = DBConnection.getConnection();


        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        String sql = "SELECT day_of_week, avg_transaction_count, avg_order_count FROM WEEKLY_STATISTICS_VIEW\n" +
                "WHERE PRODUCT_NAME = ?";


        PreparedStatement pstmt = con.prepareStatement(sql);


        pstmt.setString(1, "감자깡");
        ResultSet rs = pstmt.executeQuery();
//        while (rs.next()) {
//            System.out.println(rs.getI);
//        }


    }
}
