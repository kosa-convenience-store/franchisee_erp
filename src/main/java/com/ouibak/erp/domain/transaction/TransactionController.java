package main.java.com.ouibak.erp.domain.transaction;

import main.java.com.ouibak.erp.domain.transaction.dto.TransactionDetailDto;
import main.java.com.ouibak.erp.domain.transaction.dto.TransactionListDto;

import java.util.List;

public class TransactionController {
    TransactionService service = new TransactionService();

    public void createTransaction(int franchiseeIdx, int totalPrice, List<Object[]> values) {
        service.createTransaction(franchiseeIdx, totalPrice, values);
    }

    public List<TransactionListDto> getTransactionsPaging(int pageNumber, int pageSize, int franchiseeIdx) {
        return service.getTransactionsPaging(pageNumber, pageSize, franchiseeIdx);
    }

    public List<TransactionDetailDto> getTransactionDetails(int transactionIdx) {
        return service.getTransactionDetails(transactionIdx);
    }

}


