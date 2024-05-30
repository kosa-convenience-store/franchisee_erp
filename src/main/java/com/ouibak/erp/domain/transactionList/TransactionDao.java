package main.java.com.ouibak.erp.domain.transactionList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.java.com.ouibak.erp.dao.DBDaoImpl;
import main.java.com.ouibak.erp.dao.Query;
import main.java.com.ouibak.erp.domain.franchisee.FranchiseeVO;
import oracle.jdbc.OracleTypes;

public class TransactionDao extends DBDaoImpl {

    // 결제 내역 조회
    public List<Transaction> getTransactionsPaging(int pageNumber, int pageSize) {
        List<Transaction> transactions = new ArrayList<>();
        String query = Query.getQuery("getTransacList");

        try (CallableStatement callableStatement = getCStmt(query)) {

            callableStatement.setInt(1, pageNumber);
            callableStatement.setInt(2, pageSize);
            callableStatement.setInt(3, FranchiseeVO.getFranchiseeId());
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);

            callableStatement.execute();

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(4)) {
                while (resultSet.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(resultSet.getInt("transac_idx"));
                    transaction.setTotalPrice(resultSet.getInt("total_price"));
                    transaction.setTransactionTime(resultSet.getTimestamp("time"));
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // 결제 상세 내역 조회
    public List<TransactionDetail> getTransactionDetails(int transactionId) {
        List<TransactionDetail> transactionDetails = new ArrayList<>();
        String query = Query.getQuery("getTransacDetail");

        try (CallableStatement callableStatement = getCStmt(query)) {

            callableStatement.setInt(1, transactionId);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute(); // 프로시저 실행

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(2)) {
                while (resultSet.next()) {
                    TransactionDetail detail = new TransactionDetail();
                    detail.setTransactionId(resultSet.getInt("transac_idx"));
                    detail.setProductName(resultSet.getString("product_name"));
                    detail.setCount(resultSet.getInt("count"));
                    transactionDetails.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionDetails;
    }
}
