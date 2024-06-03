package main.java.com.ouibak.erp.domain.transaction;

import main.java.com.ouibak.erp.common.dao.DBDao;
import main.java.com.ouibak.erp.common.query.Query;
import main.java.com.ouibak.erp.domain.transaction.dto.TransactionDetailDto;
import main.java.com.ouibak.erp.domain.transaction.dto.TransactionListDto;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl extends DBDao implements TransactionDao {
    @Override
    public int craeteTransaction(int franchiseeIdx, int totalPrice) throws SQLException {
        CallableStatement cstmt = getCallableStatement(Query.getQuery("requestTransaction"));
        cstmt.setInt(1, franchiseeIdx);
        cstmt.setInt(2, totalPrice);
        cstmt.registerOutParameter(3, Types.INTEGER);
        cstmt.execute();
        int transactionIdx = cstmt.getInt(3);
        return transactionIdx;
    }

    @Override
    public void addTransactionList(int transacIdx, List<Object[]> values) throws SQLException {
        PreparedStatement pstmt = getPreparedStatement(Query.getQuery("insertTransacList"));
        for (Object[] arr : values) {
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
        commitDB();
    }

    @Override
    public List<TransactionListDto> getTransactionsPaging(int pageNumber, int pageSize, int franchiseeIdx) throws SQLException {
        List<TransactionListDto> list = new ArrayList<>();

        CallableStatement cstmt = getCallableStatement(Query.getQuery("getTransacList"));
        cstmt.setInt(1, pageNumber);
        cstmt.setInt(2, pageSize);
        cstmt.setInt(3, franchiseeIdx);
        cstmt.registerOutParameter(4, OracleTypes.CURSOR);
        cstmt.execute();

        ResultSet rs = (ResultSet) cstmt.getObject(4);
        while (rs.next()) {
            list.add(new TransactionListDto(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3)));
        }
        return list;
    }

    @Override
    public List<TransactionDetailDto> getTransactionDetails(int transactionId) throws SQLException {
        List<TransactionDetailDto> list = new ArrayList<>();

        CallableStatement cstmt = getCallableStatement(Query.getQuery("getTransacDetail"));
        cstmt.setInt(1, transactionId);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute(); // 프로시저 실행|

        ResultSet rs = (ResultSet) cstmt.getObject(2);
        while (rs.next()) {
            list.add(new TransactionDetailDto(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return list;
    }
}
