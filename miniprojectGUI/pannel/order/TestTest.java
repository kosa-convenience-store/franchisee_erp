package miniprojectGUI.pannel.order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class TestTest extends JFrame {

    private List<String> availableProducts = Arrays.asList("Product 1", "Product 2", "Product 3");
    private JTextField totalAmountField;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private DefaultTableModel orderTableModel;

    public TestTest() {
        setTitle("XXX ERP 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("결제", new JPanel());
        tabbedPane.addTab("재고", new JPanel());
        tabbedPane.addTab("발주", createOrderManagementPanel());
        tabbedPane.addTab("입고", new JPanel());
        tabbedPane.addTab("폐기", new JPanel());
        tabbedPane.addTab("판매결산", new JPanel());
        tabbedPane.addTab("통계", new JPanel());

        add(tabbedPane, BorderLayout.CENTER);

        JLabel storeLabel = new JLabel("xx 가맹점");
        add(storeLabel, BorderLayout.NORTH);

        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private JPanel createOrderManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel sidePanel = new JPanel(new GridLayout(3, 1));
        JButton registerButton = new JButton("발주 등록");
        JButton historyButton = new JButton("발주 내역");
        sidePanel.add(new JButton("발주서"));
        sidePanel.add(registerButton);
        sidePanel.add(historyButton);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createOrderRegistrationPanel(), "발주 등록");
        cardPanel.add(createOrderHistoryPanel(), "발주 내역");

        panel.add(sidePanel, BorderLayout.WEST);
        panel.add(cardPanel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> cardLayout.show(cardPanel, "발주 등록"));
        historyButton.addActionListener(e -> cardLayout.show(cardPanel, "발주 내역"));

        return panel;
    }

    private JPanel createOrderRegistrationPanel() {
        JPanel orderRegistrationPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("상품 명"), gbc);

        gbc.gridx = 1;
        JTextField productSearchField = new JTextField(10);
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
        table.getColumn("수정").setCellEditor(new ButtonEditor2(new JCheckBox(), tableModel, table, "수정"));
        table.getColumn("삭제").setCellRenderer(new ButtonRenderer());
        table.getColumn("삭제").setCellEditor(new ButtonEditor2(new JCheckBox(), tableModel, table, "삭제"));

        return orderRegistrationPanel;
    }
//
    private JPanel createOrderHistoryPanel() {
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
        JOptionPane.showMessageDialog(this, "Showing details for order: " + orderNumber);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TestTest().setVisible(true);
        });
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

class ButtonEditor2 extends DefaultCellEditor {
    private String label;
    private boolean isPushed;
    private JTable table;
    private DefaultTableModel tableModel;
    private String actionType;

    public ButtonEditor2(JCheckBox checkBox, DefaultTableModel tableModel, JTable table, String actionType) {
        super(checkBox);
        this.tableModel = tableModel;
        this.table = table;
        this.actionType = actionType;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        JButton button = new JButton(label);
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            if ("수정".equals(actionType)) {
                // Handle modify action
                JOptionPane.showMessageDialog(table, "수정 버튼 클릭됨", "수정", JOptionPane.INFORMATION_MESSAGE);
            } else if ("삭제".equals(actionType)) {
                // Handle delete action
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                }
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = true;
        return super.stopCellEditing();
    }
}

