package main.java.com.ouibak.erp.domain.login;

import main.java.com.ouibak.erp.dao.DBConnection;
import main.java.com.ouibak.erp.dao.DBDaoImpl;
import main.java.com.ouibak.erp.domain.franchisee.FranchiseeVO;

import java.sql.*;

public class LoginDao extends DBDaoImpl {

    public int validateLogin(String userId, String password) {
        Connection conn = null;
        CallableStatement cstm = null;
        try {
            conn = DBConnection.getConnection();

            // PL/SQL 함수 호출을 위한 SQL 문
            String sql = "{? = call validate_login(?, ?)}";

            // DB 저장 프로시저(Stored Procedure)의 호출을 실행하는데 사용
            cstm = conn.prepareCall(sql);
            cstm.registerOutParameter(1, Types.INTEGER); // 반환 값 설정
            cstm.setString(2, userId);
            cstm.setString(3, password);

            // 실행
            cstm.execute();

            // 함수의 반환 값을 가져옴
            int franchiseeIdx = cstm.getInt(1);
            if (franchiseeIdx > 0) {
                FranchiseeVO.setFranchiseeId(franchiseeIdx);
                setFranchiseeInfo();
            }
            return franchiseeIdx;
        } catch (SQLException e) {
            e.printStackTrace();
            return -99; // 데이터베이스 오류를 나타내는 코드
        }
    }

    private void setFranchiseeInfo() throws SQLException {
        String query = "SELECT franchisee_name, franchisee_loc, business_num, franchisee_tel, rate_for_order " +
                "from tbl_franchisee_info where franchisee_idx = ?";

        PreparedStatement pstmt = getPreStmt(query);
        pstmt.setInt(1, FranchiseeVO.getFranchiseeId());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        FranchiseeVO.setFranchiseeName(rs.getString(1));
        FranchiseeVO.setFranchiseeLocation(rs.getString(2));
        FranchiseeVO.setFranchiseeBusinessNum(rs.getString(3));
        FranchiseeVO.setFranchiseeTel(rs.getString(4));
        FranchiseeVO.setPercetage(rs.getInt(5));
    }

    public Object[][] getProductRanking() throws SQLException {
        Object[][] productRank = new Object[5][2];
        String query = "SELECT PRODUCT_NAME , SALE_COUNT FROM product_ranking_view " +
                "WHERE FRANCHISEE_IDX = ? AND ROWNUM <=5 ORDER BY PRODUCT_RANK";

        PreparedStatement pstmt = getPreStmt(query);
        pstmt.setInt(1, FranchiseeVO.getFranchiseeId());
        ResultSet rs = pstmt.executeQuery();
        int idx = 0;
        while (rs.next()) {
            productRank[idx][0] = rs.getString(1);
            productRank[idx][1] = rs.getInt(2);
            idx++;
        }
        return productRank;
    }

    public void updateOrderErrorRate(String oderErrorRate) throws SQLException {
        int rate = Integer.parseInt(oderErrorRate);
        System.out.println(rate);
        String query = "UPDATE TBL_FRANCHISEE_INFO SET RATE_FOR_ORDER = ? WHERE FRANCHISEE_IDX = ?";
        PreparedStatement pstmt = getPreStmt(query);
        pstmt.setInt(1, rate);
        pstmt.setInt(2, FranchiseeVO.getFranchiseeId());
        pstmt.executeUpdate();
        commitDB();
    }

}
