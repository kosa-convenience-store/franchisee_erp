package main.java.com.ouibak.erp.gui.tabpannel.orderGui;

import main.java.com.ouibak.erp.domain.franchisee.FranchiseeVO;
import main.java.com.ouibak.erp.domain.product.ProductVO;
import main.java.com.ouibak.erp.domain.orderRequest.OrderServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderGui extends JFrame {
    private List<String> availableProducts;
    private Map<String, Integer> productPriceMap;
    private JTextField totalAmountField;
    DefaultTableModel tableModel = new DefaultTableModel(new String[] {"상품 이름", "개수", "가격", "총 가격", "수정", "삭제"}, 0);

    JTable table = new JTable(tableModel) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 4 || column == 5;
        }
    };

    private OrderServiceImpl service;

    private void getData() {
        service = OrderServiceImpl.getInstance();
        availableProducts = ProductVO.getProductNames();
        productPriceMap = ProductVO.getProductNamePrice();
    }

    public JPanel createOrderRegistrationPanel() {
        getData();
        JPanel orderRegistrationPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("상품 명"), gbc);

        gbc.gridx = 1;
        JTextField productSearchField = new JTextField(25);
        formPanel.add(productSearchField, gbc);

        gbc.gridx = 2;
        JButton searchButton = new JButton("검색");
        formPanel.add(searchButton, gbc);

        gbc.gridx = 3;
        formPanel.add(new JLabel("개수"), gbc);

        gbc.gridx = 4;
        JTextField quantityField = new JTextField(5);
        formPanel.add(quantityField, gbc);

        gbc.gridx = 5;
        JButton addButton = new JButton("담기");
        formPanel.add(addButton, gbc);

        JScrollPane tableScrollPane = new JScrollPane(table);

        // 행 높이 설정
        table.setRowHeight(30);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(new JLabel("총 금액"));
        totalAmountField = new JTextField(10);
        totalAmountField.setEditable(false);
        totalPanel.add(totalAmountField);
        totalPanel.add(new JLabel("원"));
        JButton orderRequestButton = new JButton("발주요청");
        totalPanel.add(orderRequestButton);

        orderRegistrationPanel.add(formPanel, BorderLayout.NORTH);
        orderRegistrationPanel.add(tableScrollPane, BorderLayout.CENTER);
        orderRegistrationPanel.add(totalPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String productName = productSearchField.getText();
            String quantity = quantityField.getText();
            int price = productPriceMap.get(productName);
            int totalPrice = Integer.parseInt(quantity) * price;
            tableModel.addRow(new Object[]{productName, quantity, String.valueOf(price), String.valueOf(totalPrice), "수정", "삭제"});
            updateTotalAmount(tableModel);
            productSearchField.setText("");
            quantityField.setText("");
        });

        searchButton.addActionListener(e -> {
            String searchText = productSearchField.getText();
            List<String> searchResults = searchProducts(searchText);
            if (searchResults.isEmpty()) {
                JOptionPane.showMessageDialog(this, "검색된 상품이 없습니다.", "검색 결과", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String selectedProduct = (String) JOptionPane.showInputDialog(
                        this,
                        "상품을 선택하세요.",
                        "검색 결과",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        searchResults.toArray(),
                        searchResults.get(0)
                );
                if (selectedProduct != null) {
                    productSearchField.setText(selectedProduct);
                }
            }
        });

        orderRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int franchiseeIdx = FranchiseeVO.getFranchiseeId();  // 로그인된 가맹점 번호
                    int threshold = 25;     // 임계값 (%)

                    List<Object[]> tableList = new ArrayList<>();
                    int rowCnt = tableModel.getRowCount();
                    for (int i = 0; i < rowCnt; i++) {
                        Object[] row = new Object[2];
                        row[0] = service.getProductIdByName((String) tableModel.getValueAt(i, 0)); // product id
                        row[1] = tableModel.getValueAt(i, 1); // quantity
                        tableList.add(row);
                    }

                    String warningMessages = service.checkOrderErrors(franchiseeIdx, tableList, threshold);

                    if (!warningMessages.isEmpty()) {
                        int response = JOptionPane.showConfirmDialog(
                                null,
                                warningMessages,
                                "경고",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                        if (response == JOptionPane.YES_OPTION) {
                            addOrderRequest();
                            productSearchField.setText("");
                            quantityField.setText("");
                        }
                    } else {
                        addOrderRequest();
                        productSearchField.setText("");
                        quantityField.setText("");
                    }
                } catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        table.getColumn("수정").setCellRenderer(new ButtonRenderer());
        table.getColumn("수정").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table, "수정"));
        table.getColumn("삭제").setCellRenderer(new ButtonRenderer());
        table.getColumn("삭제").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table, "삭제"));

        return orderRegistrationPanel;
    }

    private List<String> searchProducts(String searchText) {
        return availableProducts.stream()
                .filter(product -> product.toLowerCase().contains(searchText.toLowerCase()))
                .toList();
    }

    private void updateTotalAmount(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        int totalAmount = 0;
        for (int i = 0; i < rowCount; i++) {
            totalAmount += Integer.parseInt((String) tableModel.getValueAt(i, 3));
        }
        totalAmountField.setText(String.valueOf(totalAmount));
    }

    private void addOrderRequest() {
        List<Object[]> tableList = new ArrayList<>();
        int rowCnt = tableModel.getRowCount();
        for (int i = 0; i < rowCnt; i++) {
            Object[] row = new Object[2];
            for (int j = 0; j < 2; j++) {
                row[j] = tableModel.getValueAt(i, j);
            }
            tableList.add(row);
        }

        try {
            service.orderProduct(tableList);
            alertRequestInfo();
            tableModel.getDataVector().removeAllElements();
            tableModel.fireTableDataChanged();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void alertRequestInfo() {
        String message = "발주 요청이 완료되었습니다.";
        String title = "작업 완료";
        int messageType = JOptionPane.INFORMATION_MESSAGE;
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;
        private JTable table;
        private DefaultTableModel tableModel;
        private String actionType;

        public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel, JTable table, String actionType) {
            super(checkBox);
            this.tableModel = tableModel;
            this.table = table;
            this.actionType = actionType;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            isPushed = true;
            JButton button = new JButton();
            button.setText(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isPushed) {
                        if (actionType.equals("수정")) {
                            editRow(table.getSelectedRow());
                            isPushed = false;
                            updateTotalAmount(tableModel);
                            fireEditingStopped();
                        } else if (actionType.equals("삭제")) {
                            int editRow = table.getSelectedRow();
                            if (editRow >= 0 && editRow < tableModel.getRowCount()) {
                                deleteRow(editRow);
                            }
                            isPushed = false;
                            Component component = table.getTopLevelAncestor();
                            if (component instanceof OrderGui) {
                                ((OrderGui) component).updateTotalAmount(tableModel);
                            }
                            fireEditingStopped();
                        }
                    }
                }
            });
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        private void editRow(int row) {
            String productName = (String) tableModel.getValueAt(row, 0);
            String quantity = (String) tableModel.getValueAt(row, 1);
            String price = (String) tableModel.getValueAt(row, 2);
            String newQuantity = JOptionPane.showInputDialog(null, "수정할 개수 입력:", quantity);
            if (newQuantity != null && !newQuantity.trim().isEmpty()) {
                int totalPrice = Integer.parseInt(newQuantity) * Integer.parseInt(price);
                tableModel.setValueAt(newQuantity, row, 1);
                tableModel.setValueAt(String.valueOf(totalPrice), row, 3);
            }
        }

        private void deleteRow(int row) {
            tableModel.removeRow(row);
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
}
