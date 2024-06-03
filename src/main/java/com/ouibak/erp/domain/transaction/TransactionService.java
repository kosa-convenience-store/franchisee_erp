package main.java.com.ouibak.erp.domain.transaction;

import main.java.com.ouibak.erp.domain.transaction.dto.TransactionDetailDto;
import main.java.com.ouibak.erp.domain.transaction.dto.TransactionListDto;

import java.sql.SQLException;
import java.util.List;

public class TransactionService {
    TransactionDao dao = new TransactionDaoImpl();

    void createTransaction(int franchiseeIdx, int totalPrice, List<Object[]> values) {
        try {
            int transactionIdx = dao.craeteTransaction(franchiseeIdx, totalPrice);
            dao.addTransactionList(transactionIdx, values);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<TransactionListDto> getTransactionsPaging(int pageNumber, int pageSize, int franchiseeIdx) {
        try {
            return dao.getTransactionsPaging(pageNumber, pageSize, franchiseeIdx);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    List<TransactionDetailDto> getTransactionDetails(int transactionId) {
        try {
            return dao.getTransactionDetails(transactionId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
