package main.java.com.ouibak.erp.gui.tabpannel.inventoryGui;

import main.java.com.ouibak.erp.domain.inventory.InventoryDaoImpl;
import main.java.com.ouibak.erp.domain.inventory.InventoryVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.SQLException;
import java.util.List;

public class InventoryGui {
    private DefaultTableModel invenTableModel;
    private InventoryDaoImpl inventoryDao;
    private int currentPage = 1;
    private int pageSize = 10;
    private boolean isLoading = false;

    public InventoryGui() {
        inventoryDao = new InventoryDaoImpl();
    }

    public JPanel createInventoryPanel() {
        JPanel inventoryPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"상품 이름", "재고"};
        invenTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // 모든 셀 read-only
            }
        };
        JTable invenTable = new JTable(invenTableModel);

        // 행 높이 설정
        invenTable.setRowHeight(30);

        // 무한 스크롤
        JScrollPane inventoryTableScrollPane = new JScrollPane(invenTable);
        inventoryTableScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (!isLoading && !e.getValueIsAdjusting() && e.getAdjustable().getMaximum() == (e.getValue() + e.getAdjustable().getVisibleAmount())) {
                    isLoading = true;
                    currentPage++;
                    updateInvenTableData();
                }
            }
        });

        inventoryPanel.add(inventoryTableScrollPane, BorderLayout.CENTER);

        updateInvenTableData();
        return inventoryPanel;
    }

    private void updateInvenTableData() {
        SwingWorker<Void, InventoryVO> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                List<InventoryVO> inventory = null;
                try {
                    inventory = inventoryDao.getInventory(currentPage, pageSize);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                for (InventoryVO inven : inventory) {
                    publish(inven);
                }
                return null;
            }

            @Override
            protected void process(List<InventoryVO> chunks) {
                for (InventoryVO inven : chunks) {
                    invenTableModel.addRow(new Object[]{inven.getProductName(), inven.getCount()});
                }
                isLoading = false;
            }
        };
        worker.execute();
    }

    public void resetInvenTableData() {
        invenTableModel.setRowCount(0);
        currentPage = 1;
        updateInvenTableData();
    }
}
