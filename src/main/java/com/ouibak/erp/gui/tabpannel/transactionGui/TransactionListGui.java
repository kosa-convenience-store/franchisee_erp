package main.java.com.ouibak.erp.gui.tabpannel.transactionGui;

import main.java.com.ouibak.erp.domain.transactionList.Transaction;
import main.java.com.ouibak.erp.domain.transactionList.TransactionDao;
import main.java.com.ouibak.erp.domain.transactionList.TransactionDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TransactionListGui {
    private DefaultTableModel transactionTableModel;
    private TransactionDao transactionDao;
    private int currentPage = 1;
    private int pageSize = 10;
    private boolean isLoading = false;

    public TransactionListGui() {
        transactionDao = new TransactionDao();
    }

    public JPanel createTransactionPanel() {
        JPanel transactionPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"결제 번호", "총 금액", "결제 시간"};
        transactionTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // 모든 셀 read-only
            }
        };
        JTable transactionTable = new JTable(transactionTableModel);

        // 행 높이 설정
        transactionTable.setRowHeight(30);

        // 스크롤 패널 추가
        JScrollPane transactionTableScrollPane = new JScrollPane(transactionTable);
        transactionPanel.add(transactionTableScrollPane, BorderLayout.CENTER);

        transactionTableScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (!isLoading && !e.getValueIsAdjusting() && e.getAdjustable().getMaximum() == (e.getValue() + e.getAdjustable().getVisibleAmount())) {
                    isLoading = true;
                    currentPage++;
                    updateTransactionTableData();
                }
            }
        });

        transactionPanel.add(transactionTableScrollPane, BorderLayout.CENTER);

        // Add a mouse listener to handle clicks on the transaction table
        transactionTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = transactionTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int transactionId = (Integer) transactionTableModel.getValueAt(selectedRow, 0);
                        showTransactionDetails(transactionId);
                    }
                }
            }
        });

        updateTransactionTableData();
        return transactionPanel;
    }

    private void updateTransactionTableData() {
        SwingWorker<Void, Transaction> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                List<Transaction> transactions = transactionDao.getTransactionsPaging(currentPage, pageSize);
                for (Transaction transaction : transactions) {
                    publish(transaction);
                }
                return null;
            }

            @Override
            protected void process(List<Transaction> chunks) {
                for (Transaction transaction : chunks) {
                    transactionTableModel.addRow(new Object[]{
                            transaction.getTransactionId(),
                            transaction.getTotalPrice() + "원",
                            transaction.getTransactionTime()
                    });
                }
                isLoading = false;
            }
        };
        worker.execute();
    }

    public void resetTransacTableData() {
        transactionTableModel.setRowCount(0);
        currentPage = 1;
        updateTransactionTableData();
    }

    private void showTransactionDetails(int transactionId) {
        List<TransactionDetail> transactionDetails = transactionDao.getTransactionDetails(transactionId);

        // 상세 내역을 보여줄 다이얼로그 생성
        String[] columnNames = {"결제 번호", "물품 이름", "수량"};
        DefaultTableModel detailTableModel = new DefaultTableModel(columnNames, 0);
        for (TransactionDetail detail : transactionDetails) {
            detailTableModel.addRow(new Object[]{detail.getTransactionId(), detail.getProductName(), detail.getCount()});
        }

        JTable detailTable = new JTable(detailTableModel);
        detailTable.setRowHeight(30);
        JScrollPane detailScrollPane = new JScrollPane(detailTable);

        JDialog dialog = new JDialog((Frame) null, "결제 상세", true);
        dialog.setSize(400, 300);
        dialog.add(detailScrollPane, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}