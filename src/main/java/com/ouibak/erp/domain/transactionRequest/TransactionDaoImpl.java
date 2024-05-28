package main.java.com.ouibak.erp.domain.transactionRequest;

import main.java.com.ouibak.erp.dao.DBDaoImpl;
import main.java.com.ouibak.erp.domain.franchisee.FranchiseeVO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class TransactionDaoImpl extends DBDaoImpl implements TransactionDao {
    private static TransactionDaoImpl dao;

    public TransactionDaoImpl() {
    }

    public static TransactionDaoImpl getInstance() {
        if (dao == null) {
            dao = new TransactionDaoImpl();
        }
        return dao;
    }

    @Override
    public int craeteTransaction(String sqlQuery, int totalPrice) throws SQLException {
        CallableStatement cstmt = getCStmt(sqlQuery);
        cstmt.setInt(1, FranchiseeVO.getFranchiseeId());
        System.out.println(totalPrice);
        cstmt.setInt(2, totalPrice);
        cstmt.registerOutParameter(3, Types.INTEGER);
        cstmt.execute();
        int transactionIdx = cstmt.getInt(3);
        return transactionIdx;
    }

    @Override
    public void addTransactionList(String sqlQuery, int transacIdx, List<Object[]> list) throws SQLException {
        PreparedStatement pstmt = getPreStmt(sqlQuery);
        for (Object[] arr : list) {
            //transaction idx
            pstmt.setInt(1, transacIdx);
            //product idx
            pstmt.setString(2, String.valueOf(arr[0]));
            //count
            pstmt.setInt(3, Integer.parseInt((String.valueOf(arr[1]))));

            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.clearBatch();
    }
}
