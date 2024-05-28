package main.java.com.ouibak.erp.domain.transactionRequest;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao {

    // 결제 요청하는 메서드
    public int craeteTransaction(String sqlQuery, int totalPrice) throws SQLException;

    // 결제 요청 후 상품 목록을 db에 insert하는 메서드
    void addTransactionList(String sqlQuery, int transacIdx, List<Object[]> list) throws SQLException;
}
