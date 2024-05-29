package main.java.com.ouibak.erp.gui.tabpannel.orderGui;

import main.java.com.ouibak.erp.domain.orderList.Order;
import main.java.com.ouibak.erp.domain.orderList.OrderDao;
import main.java.com.ouibak.erp.domain.orderList.OrderDetail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class OrderListGui {
    private DefaultTableModel orderTableModel;
    private OrderDao orderDao;
    private int currentPage = 1;
    private int pageSize = 10;
    private boolean isLoading = false;

    public OrderListGui() {
        orderDao = new OrderDao();
    }

    public JPanel createOrderHistoryPanel() {
        JPanel orderHistoryPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"발주 번호", "발주 일자", "발주 상태", "도착 일자"};
        orderTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // 모든 셀 read-only
            }
        };
        JTable orderTable = new JTable(orderTableModel);

        // 행 높이 설정
        orderTable.setRowHeight(30);

        // 무한 스크롤
        JScrollPane orderTableScrollPane = new JScrollPane(orderTable);
        orderTableScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (!isLoading && !e.getValueIsAdjusting() && e.getAdjustable().getMaximum() == (e.getValue() + e.getAdjustable().getVisibleAmount())) {
                    isLoading = true;
                    currentPage++;
                    updateTableData();
                }
            }
        });

        orderHistoryPanel.add(orderTableScrollPane, BorderLayout.CENTER);

        // Add a mouse listener to handle clicks on the order table
        orderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = orderTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int orderNumber = (Integer) orderTableModel.getValueAt(selectedRow, 0);
                        showOrderDetails(orderNumber);
                    }
                }
            }
        });

        updateTableData();
        return orderHistoryPanel;
    }

    public void updateTableData() {
        SwingWorker<Void, Order> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                List<Order> orders = orderDao.getRecentOrdersPaging(currentPage, pageSize);
                for (Order order : orders) {
                    publish(order);
                }
                return null;
            }

            @Override
            protected void process(List<Order> chunks) {
                for (Order order : chunks) {
                    orderTableModel.addRow(new Object[]{order.getOrderIdx(), order.getOrderDate(), order.getOrderState(), order.getArriveDate()});
                }
                isLoading = false;
            }
        };
        worker.execute();
    }

    public void resetTableData() {
        orderTableModel.setRowCount(0);  // Clear existing data
        currentPage = 1;
        updateTableData();
    }

    private void showOrderDetails(int orderIdx) {
        List<OrderDetail> orderDetails = orderDao.getOrderDetails(orderIdx);

        // 상세 내역을 보여줄 다이얼로그 생성
        String[] columnNames = {"발주 번호", "물품 이름", "수량"};
        DefaultTableModel detailTableModel = new DefaultTableModel(columnNames, 0);
        for (OrderDetail detail : orderDetails) {
            detailTableModel.addRow(new Object[]{detail.getOrderIdx(), detail.getProductName(), detail.getCount()});
        }

        JTable detailTable = new JTable(detailTableModel);
        detailTable.setRowHeight(30);
        JScrollPane detailScrollPane = new JScrollPane(detailTable);

        JDialog dialog = new JDialog((Frame) null, "발주 상세", true);
        dialog.setSize(400, 300);
        dialog.add(detailScrollPane, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
