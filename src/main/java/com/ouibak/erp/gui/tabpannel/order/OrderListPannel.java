package main.java.com.ouibak.erp.gui.tabpannel.order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderListPannel {
    private DefaultTableModel orderTableModel;

    public OrderListPannel() {}

    public JPanel createOrderHistoryPanel() {
        JPanel orderHistoryPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"발주 번호", "발주 일자", "발주 상태", "도착 일자"};
        orderTableModel = new DefaultTableModel(columnNames, 0);
        JTable orderTable = new JTable(orderTableModel);

        JScrollPane orderTableScrollPane = new JScrollPane(orderTable);

        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paginationPanel.add(new JButton("<<"));
        for (int i = 1; i <= 10; i++) {
            paginationPanel.add(new JButton(String.valueOf(i)));
        }
        paginationPanel.add(new JButton(">>"));

        orderHistoryPanel.add(orderTableScrollPane, BorderLayout.CENTER);
        orderHistoryPanel.add(paginationPanel, BorderLayout.SOUTH);

        // Add a mouse listener to handle clicks on the order table
        orderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = orderTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String orderNumber = (String) orderTableModel.getValueAt(selectedRow, 0);
                        showOrderDetails(orderNumber);
                    }
                }
            }
        });

        return orderHistoryPanel;
    }

    private void showOrderDetails(String orderNumber) {
        // Show detailed order information (this could be another card in the card layout or a dialog)
        JOptionPane.showMessageDialog(this.createOrderHistoryPanel(), "Showing details for order: " + orderNumber);
    }

}