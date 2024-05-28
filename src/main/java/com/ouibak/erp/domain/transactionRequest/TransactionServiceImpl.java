package main.java.com.ouibak.erp.domain.transactionRequest;

import main.java.com.ouibak.erp.dao.Query;

import java.sql.SQLException;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionDaoImpl dao;
    private static TransactionServiceImpl instance;

    private TransactionServiceImpl() {
        dao = TransactionDaoImpl.getInstance();
    }

    public static TransactionServiceImpl getInstance() {
        new TransactionServiceImpl();
        if (instance == null) { instance = new TransactionServiceImpl(); }
        return instance;
    }

    @Override
    public void requestTransaction(List<Object[]> values, int totalPrice) throws SQLException {
        int transacIdx = dao.craeteTransaction(Query.getQuery("requestTransaction"), totalPrice);
        dao.addTransactionList(Query.getQuery("insertTransacList"), transacIdx, values);
        dao.commitDB();
    }
}
