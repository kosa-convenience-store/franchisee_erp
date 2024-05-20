package miniprojectGUI.pannel.order;

import javax.swing.*;
import java.awt.*;

public class OrderTab extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public JPanel createOrderManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel sidePanel = new JPanel(new GridLayout(2, 1));
        JButton registerButton = new JButton("발주 등록");
        JButton historyButton = new JButton("발주 내역");
        sidePanel.add(registerButton);
        sidePanel.add(historyButton);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(new OrderPannel().createOrderRegistrationPanel(), "발주 등록");
        cardPanel.add(new OrderListPannel().createOrderHistoryPanel(), "발주 내역");

        panel.add(sidePanel, BorderLayout.WEST);
        panel.add(cardPanel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> cardLayout.show(cardPanel, "발주 등록"));
        historyButton.addActionListener(e -> cardLayout.show(cardPanel, "발주 내역"));

        return panel;
    }

}
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellRenderer;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Arrays;
//import java.util.List;
//
//public class OrderTab extends JFrame {
//    private List<String> availableProducts = Arrays.asList("Product 1", "Product 2", "Product 3");
//    private JTextField totalAmountField;
//
//    public JPanel orderTab() {
//
//        JPanel orderPanel = new JPanel(new BorderLayout());
//
//        JPanel sidePanel = new JPanel(new GridLayout(2, 1));
//        JButton registerButton = new JButton("발주 등록");
//        JButton historyButton = new JButton("발주 내역");
//        sidePanel.add(registerButton);
//        sidePanel.add(historyButton);
//
//        JPanel formPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//        gbc.anchor = GridBagConstraints.WEST;
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        formPanel.add(new JLabel("상품 명"), gbc);
//
//        gbc.gridx = 1;
//        JTextField productSearchField = new JTextField(10);
//        formPanel.add(productSearchField, gbc);
//
//        gbc.gridx = 2;
//        JButton searchButton = new JButton("검색");
//        formPanel.add(searchButton, gbc);
//
//        gbc.gridx = 3;
//        formPanel.add(new JLabel("개수"), gbc);
//
//        gbc.gridx = 4;
//        JTextField quantityField = new JTextField(5);
//        formPanel.add(quantityField, gbc);
//
//        gbc.gridx = 5;
//        JButton addButton = new JButton("담기");
//        formPanel.add(addButton, gbc);
//
//        String[] columnNames = {"상품 이름", "개수", "가격", "총 가격", "수정", "삭제"};
//        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
//        JTable table = new JTable(tableModel) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return column == 4 || column == 5;
//            }
//        };
//
//        JScrollPane tableScrollPane = new JScrollPane(table);
//
//        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        totalPanel.add(new JLabel("총 금액"));
//        totalAmountField = new JTextField(10);
//        totalAmountField.setEditable(false);
//        totalPanel.add(totalAmountField);
//        totalPanel.add(new JLabel("원"));
//        JButton orderRequestButton = new JButton("발주요청");
//        totalPanel.add(orderRequestButton);
//
//        orderPanel.add(sidePanel, BorderLayout.WEST);
//        orderPanel.add(formPanel, BorderLayout.NORTH);
//        orderPanel.add(tableScrollPane, BorderLayout.CENTER);
//        orderPanel.add(totalPanel, BorderLayout.SOUTH);
//
//        addButton.addActionListener(e -> {
//            String productName = productSearchField.getText();
//            String quantity = quantityField.getText();
//            String price = "100"; // Example price, this should be retrieved from product details
//            int totalPrice = Integer.parseInt(quantity) * Integer.parseInt(price);
//            tableModel.addRow(new Object[]{productName, quantity, price, String.valueOf(totalPrice), "수정", "삭제"});
//            updateTotalAmount(tableModel);
//        });
//
//        searchButton.addActionListener(e -> {
//            String searchText = productSearchField.getText();
//            java.util.List<String> searchResults = searchProducts(searchText);
//            if (searchResults.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "검색된 상품이 없습니다.", "검색 결과", JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                String selectedProduct = (String) JOptionPane.showInputDialog(
//                        this,
//                        "상품을 선택하세요.",
//                        "검색 결과",
//                        JOptionPane.PLAIN_MESSAGE,
//                        null,
//                        searchResults.toArray(),
//                        searchResults.get(0)
//                );
//                if (selectedProduct != null) {
//                    productSearchField.setText(selectedProduct);
//                }
//            }
//        });
//
//        table.getColumn("수정").setCellRenderer(new ButtonRenderer());
//        table.getColumn("수정").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table, "수정"));
//        table.getColumn("삭제").setCellRenderer(new ButtonRenderer());
//        table.getColumn("삭제").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table, "삭제"));
//
//        return orderPanel;
//    }
//
//    private List<String> searchProducts(String searchText) {
//        return availableProducts.stream()
//                .filter(product -> product.toLowerCase().contains(searchText.toLowerCase()))
//                .toList();
//    }
//
//    void updateTotalAmount(DefaultTableModel tableModel) {
//        int rowCount = tableModel.getRowCount();
//        int totalAmount = 0;
//        for (int i = 0; i < rowCount; i++) {
//            totalAmount += Integer.parseInt((String) tableModel.getValueAt(i, 3));
//        }
//        totalAmountField.setText(String.valueOf(totalAmount));
//    }
//
//    class ButtonRenderer extends JButton implements TableCellRenderer {
//        public ButtonRenderer() {
//            setOpaque(true);
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            setText((value == null) ? "" : value.toString());
//            return this;
//        }
//    }
//}
//
//
//////
//class ButtonEditor extends DefaultCellEditor {
//    private String label;
//    private boolean isPushed;
//    private JTable table;
//    private DefaultTableModel tableModel;
//    private String actionType;
//
//    public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel, JTable table, String actionType) {
//        super(checkBox);
//        this.tableModel = tableModel;
//        this.table = table;
//        this.actionType = actionType;
//    }
//
//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        label = (value == null) ? "" : value.toString();
//        isPushed = true;
//        JButton button = new JButton();
//        button.setText(label);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (isPushed) {
//                    if (actionType.equals("수정")) {
//                        editRow(table.getSelectedRow());
//                    } else if (actionType.equals("삭제")) {
//                        deleteRow(table.getSelectedRow());
//                    }
//                }
//                isPushed = false;
//                fireEditingStopped();
//            }
//        });
//        return button;
//    }
//
//    @Override
//    public Object getCellEditorValue() {
//        return label;
//    }
//
//    @Override
//    public boolean stopCellEditing() {
//        isPushed = false;
//        return super.stopCellEditing();
//    }
//
//    private void editRow(int row) {
//        String productName = (String) tableModel.getValueAt(row, 0);
//        String quantity = (String) tableModel.getValueAt(row, 1);
//        String price = (String) tableModel.getValueAt(row, 2);
//        String newQuantity = JOptionPane.showInputDialog(null, "수정할 개수 입력:", quantity);
//        if (newQuantity != null && !newQuantity.trim().isEmpty()) {
//            int totalPrice = Integer.parseInt(newQuantity) * Integer.parseInt(price);
//            tableModel.setValueAt(newQuantity, row, 1);
//            tableModel.setValueAt(String.valueOf(totalPrice), row, 3);
//            ((OrderTab) SwingUtilities.getWindowAncestor(table)).updateTotalAmount(tableModel);
//        }
//    }
//
//    private void deleteRow(int row) {
//        tableModel.removeRow(row);
//        ((OrderTab) SwingUtilities.getWindowAncestor(table)).updateTotalAmount(tableModel);
//    }
//}
