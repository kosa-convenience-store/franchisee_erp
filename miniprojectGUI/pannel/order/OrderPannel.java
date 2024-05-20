package miniprojectGUI.pannel.order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class OrderPannel extends JFrame {
    private List<String> availableProducts = Arrays.asList("Product 1", "Product 2", "Product 3");
    private JTextField totalAmountField;

    public JPanel createOrderRegistrationPanel() {
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

        String[] columnNames = {"상품 이름", "개수", "가격", "총 가격", "수정", "삭제"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5;
            }
        };

        JScrollPane tableScrollPane = new JScrollPane(table);

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
            String price = "100"; // Example price, this should be retrieved from product details
            int totalPrice = Integer.parseInt(quantity) * Integer.parseInt(price);
            tableModel.addRow(new Object[]{productName, quantity, price, String.valueOf(totalPrice), "수정", "삭제"});
            updateTotalAmount(tableModel);
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

        table.getColumn("수정").setCellRenderer(new ButtonRenderer());
        table.getColumn("수정").setCellEditor(new ButtonEditor3(new JCheckBox(), tableModel, table, "수정"));
        table.getColumn("삭제").setCellRenderer(new ButtonRenderer());
        table.getColumn("삭제").setCellEditor(new ButtonEditor3(new JCheckBox(), tableModel, table, "삭제"));

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
}

class ButtonEditor3 extends DefaultCellEditor {
    private String label;
    private boolean isPushed;
    private JTable table;
    private DefaultTableModel tableModel;
    private String actionType;

    public ButtonEditor3(JCheckBox checkBox, DefaultTableModel tableModel, JTable table, String actionType) {
        super(checkBox);
        this.tableModel = tableModel;
        this.table = table;
        this.actionType = actionType;
    }
}

