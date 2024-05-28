package main.java.com.ouibak.erp.domain.transactionRequest;

import java.sql.SQLException;
import java.util.List;

public interface TransactionService {

    public void requestTransaction(List<Object[]> values, int totalPrice) throws SQLException;

}
