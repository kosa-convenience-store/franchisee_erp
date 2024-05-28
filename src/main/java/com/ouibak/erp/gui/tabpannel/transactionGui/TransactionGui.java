package main.java.com.ouibak.erp.gui.tabpannel.transactionGui;

import main.java.com.ouibak.erp.domain.product.ProductVO;
import main.java.com.ouibak.erp.domain.transactionRequest.TransactionServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class TransactionGui extends JFrame {
    private List<Integer> availableProducts;
    private Map<Integer, Object[]> productPriceMap;

    private JTextField totalAmountField;
    DefaultTableModel tableModel = new DefaultTableModel(new String[]{"상품 이름", "개수", "가격", "총 가격", "수정", "삭제"}, 0);

    JTable table = new JTable(tableModel) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 4 || column == 5;
        }
    };

    private TransactionServiceImpl service;

    private void getData() {
        service = TransactionServiceImpl.getInstance();
        productPriceMap = ProductVO.getProductPriceMap();
        availableProducts = productPriceMap.keySet().stream().toList();
    }

    public JPanel createTransactionPanel() {
        getData();
        JPanel transactionPanel = new JPanel(new BorderLayout());

        transactionPanel.add(createFormPanel(), BorderLayout.NORTH);
        transactionPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        transactionPanel.add(createTotalPanel(), BorderLayout.SOUTH);

        table.getColumn("수정").setCellRenderer(new ButtonRenderer());
        table.getColumn("수정").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table, "수정"));
        table.getColumn("삭제").setCellRenderer(new ButtonRenderer());
        table.getColumn("삭제").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel, table, "삭제"));

        return transactionPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("상품 번호"), gbc);

        gbc.gridx = 1;
        JTextField productNumberField = new JTextField(25);
        formPanel.add(productNumberField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("개수"), gbc);

        gbc.gridx = 3;
        JTextField quantityField = new JTextField(5);
        formPanel.add(quantityField, gbc);

        gbc.gridx = 4;
        JButton addButton = new JButton("담기");
        formPanel.add(addButton, gbc);

        productNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addProductToTable(productNumberField, quantityField);
                }
            }
        });

        addButton.addActionListener(e -> addProductToTable(productNumberField, quantityField));

        return formPanel;
    }

    private JPanel createTotalPanel() {
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(new JLabel("총 금액"));
        totalAmountField = new JTextField(10);
        totalAmountField.setEditable(false);
        totalPanel.add(totalAmountField);
        totalPanel.add(new JLabel("원"));
        JButton transactionRequestButton = new JButton("결제");
        totalPanel.add(transactionRequestButton);

        transactionRequestButton.addActionListener(e -> processTransactionRequest());

        return totalPanel;
    }

    private void addProductToTable(JTextField productNumberField, JTextField quantityField) {
        int productIdx = Integer.valueOf(productNumberField.getText());
        if (!productPriceMap.containsKey(productIdx)) {
            JOptionPane.showMessageDialog(this, "존재하지 않는 상품입니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int quantity = 1;
        if (!quantityField.getText().isEmpty()) {
            quantity = Integer.parseInt(quantityField.getText());
        }
        String productName = (String) productPriceMap.get(productIdx)[0]; // Assuming product name is same as product number
        int price = (int) productPriceMap.get(productIdx)[1];
        boolean productExists = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(productName)) {
                int existingQuantity = Integer.parseInt((String) tableModel.getValueAt(i, 1));
                int newQuantity = existingQuantity + quantity;
                int totalPrice = newQuantity * price;
                tableModel.setValueAt(String.valueOf(newQuantity), i, 1);
                tableModel.setValueAt(String.valueOf(totalPrice), i, 3);
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            int totalPrice = quantity * price;
            tableModel.addRow(new Object[]{productName, String.valueOf(quantity), String.valueOf(price), String.valueOf(totalPrice), "수정", "삭제"});
        }
        updateTotalAmount();
        productNumberField.setText("");
        quantityField.setText("");
    }

    private void updateTotalAmount() {
        int rowCount = tableModel.getRowCount();
        int totalAmount = 0;
        for (int i = 0; i < rowCount; i++) {
            totalAmount += Integer.parseInt((String) tableModel.getValueAt(i, 3));
        }
        totalAmountField.setText(String.valueOf(totalAmount));
    }

    private void processTransactionRequest() {
        List<Object[]> tableList = new ArrayList<>();
        int rowCnt = tableModel.getRowCount();
        for (int i = 0; i < rowCnt; i++) {
            Object[] row = new Object[2];
            for (int j = 0; j<2; j++) {
                System.out.println(j +" " +tableModel.getValueAt(i, j));
                row[j] = tableModel.getValueAt(i, j);

            }
            tableList.add(row);
        }

        try {
            service.requestTransaction(tableList, Integer.parseInt(totalAmountField.getText()));
            alertRequestInfo();
            tableModel.getDataVector().removeAllElements();
            tableModel.fireTableDataChanged();
            totalAmountField.setText("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void alertRequestInfo() {
        JOptionPane.showMessageDialog(this, "결제 요청이 완료되었습니다.", "작업 완료", JOptionPane.INFORMATION_MESSAGE);
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
            JButton button = new JButton(label);
            button.addActionListener(e -> {
                if (isPushed) {
                    if (actionType.equals("수정")) {
                        editRow(table.getSelectedRow());
                    } else if (actionType.equals("삭제")) {
                        deleteRow(table.getSelectedRow());
                    }
                    isPushed = false;
                    fireEditingStopped();
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
                updateTotalAmount();
            }
        }

        private void deleteRow(int row) {
            tableModel.removeRow(row);
            updateTotalAmount();
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