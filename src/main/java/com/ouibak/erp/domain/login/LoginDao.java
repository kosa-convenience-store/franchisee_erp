package main.java.com.ouibak.erp.domain.login;

import main.java.com.ouibak.erp.dao.DBConnection;
import main.java.com.ouibak.erp.domain.franchisee.FranchiseeVO;

import java.sql.*;

public class LoginDao {

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
                FranchiseeVO.setFranchiseeId(franchiseeIdx);}
            return franchiseeIdx;
        } catch (SQLException e) {
            e.printStackTrace();
            return -99; // 데이터베이스 오류를 나타내는 코드
        }
    }
}
