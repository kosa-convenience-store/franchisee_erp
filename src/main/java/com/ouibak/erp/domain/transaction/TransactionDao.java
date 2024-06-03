package main.java.com.ouibak.erp.domain.transaction;

import main.java.com.ouibak.erp.domain.transaction.dto.TransactionDetailDto;
import main.java.com.ouibak.erp.domain.transaction.dto.TransactionListDto;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao {

    int craeteTransaction(int franchiseeIdx, int totalPrice) throws SQLException;

    void addTransactionList(int transacIdx, List<Object[]> values) throws SQLException;

    // 결제 내역 조회
    List<TransactionListDto> getTransactionsPaging(int pageNumber, int pageSize, int franchiseeIdx) throws SQLException;

    // 결제 상세 내역 조회
    List<TransactionDetailDto> getTransactionDetails(int transactionId) throws SQLException;

}
