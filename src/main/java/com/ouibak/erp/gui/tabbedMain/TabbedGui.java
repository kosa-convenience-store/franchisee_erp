package main.java.com.ouibak.erp.gui.tabbedMain;

import main.java.com.ouibak.erp.gui.tabpannel.infoGui.InfoGui;
import main.java.com.ouibak.erp.gui.tabpannel.inventoryGui.InventoryGui;
import main.java.com.ouibak.erp.gui.tabpannel.orderGui.OrderTabGui;
import main.java.com.ouibak.erp.gui.tabpannel.statisticGui.StatisticsGui;
import main.java.com.ouibak.erp.gui.tabpannel.transactionGui.TransactionTabGui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabbedGui extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel paymentPanel, invenPanel, orderPanel, wareHousingPanel,
            disposePanel, statisticsPanel, storeInfoPanel;
    private InventoryGui inventoryGui;

    public TabbedGui() {
        setTitle("XXXX ERP 프로그램");
        setSize(1115, 672);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new BorderLayout());

        JLabel storeLabelPanel = new JLabel("xxxx 가맹점  ", SwingConstants.RIGHT);
        storeLabelPanel.setFont(new Font("맑은 고딕", 0, 14));
        storeLabelPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        northPanel.add(storeLabelPanel, BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);

        //create the tabbed pane
        tabbedPane = new JTabbedPane();

        //create panels for each tab
        TransactionTabGui transactionTabGui = new TransactionTabGui();
        paymentPanel = transactionTabGui.createTransactionManagementPanel();

        inventoryGui = new InventoryGui();
        invenPanel = inventoryGui.createInventoryPanel();
        orderPanel = new OrderTabGui().createOrderManagementPanel();
        wareHousingPanel = new JPanel();
        disposePanel = new JPanel();
        statisticsPanel = new StatisticsGui().createStatisticsPanel();
        storeInfoPanel = new InfoGui().createStoreInfoPanel();

        //Add tabs to the tabbed pane
        tabbedPane.addTab("결제", paymentPanel);
        tabbedPane.addTab("재고", invenPanel);
        tabbedPane.addTab("발주", orderPanel);
        tabbedPane.addTab("입고", wareHousingPanel);
        tabbedPane.addTab("폐기", disposePanel);
        tabbedPane.addTab("통계", statisticsPanel);

        //Add change listener to the tabbed pane
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedComponent() == invenPanel) {
                    inventoryGui.resetInvenTableData();
                }
            }
        });

        //Add mouse listener to the store label panel
        storeLabelPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showStoreInfoPanel();
            }
        });

        //Add tabbed pane to the frame
        add(tabbedPane, BorderLayout.CENTER);
    }

//    private JPanel createStoreInfoPanel() {
//        JPanel storeInfoPanel = new JPanel(new BorderLayout());
//
//        JPanel leftPanel = new JPanel(new GridLayout(0, 1));
//        leftPanel.setBorder(BorderFactory.createTitledBorder("가맹점 기본 정보"));
//        leftPanel.add(new JLabel("가맹점 이름: xxx"));
//        leftPanel.add(new JLabel("가맹점 주소: xxx"));
//        leftPanel.add(new JLabel("가맹점 전화번호: xxx"));
//
//        // Add error rate setting label and text field with edit button
//        JPanel errorRatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JLabel errorRateLabel = new JLabel("발주 에러 방지 비율 설정: ");
//        errorRateField = new JTextField("25", 3);
//        errorRateField.setEditable(false);
//        editButton = new JButton("수정");
//
//        editButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (errorRateField.isEditable()) {
//                    errorRateField.setEditable(false);
//                    editButton.setText("수정");
//                } else {
//                    errorRateField.setEditable(true);
//                    editButton.setText("저장");
//                }
//            }
//        });
//
//        errorRatePanel.add(errorRateLabel);
//        errorRatePanel.add(errorRateField);
//        errorRatePanel.add(editButton);
//        leftPanel.add(errorRatePanel);
//
//        JPanel rightPanel = new JPanel(new GridLayout(0, 1));
//        rightPanel.setBorder(BorderFactory.createTitledBorder("지난 주 판매 순위"));
//        rightPanel.add(new JLabel("1위: 상품 A"));
//        rightPanel.add(new JLabel("2위: 상품 B"));
//        rightPanel.add(new JLabel("3위: 상품 C"));
//
//        // Use JSplitPane to set the ratio between leftPanel and rightPanel
//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
//        splitPane.setResizeWeight(0.7); // 7:3 ratio
//        splitPane.setDividerLocation(0.7); // Set initial location of the divider
//
//        storeInfoPanel.add(splitPane, BorderLayout.CENTER);
//
//        // Add logout section at the bottom
//        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        JLabel logoutLabel = new JLabel("로그아웃: ");
//        logoutButton = new JButton("로그아웃");
//        Dimension buttonSize = new Dimension(70, 25); // Set the desired size
//        logoutButton.setPreferredSize(buttonSize);
//        logoutButton.setMinimumSize(buttonSize);
//        logoutButton.setMaximumSize(buttonSize);
//
//        logoutButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int response = JOptionPane.showConfirmDialog(TabbedGui.this,
//                        "정말 로그아웃 하시겠습니까?", "로그아웃 확인",
//                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if (response == JOptionPane.YES_OPTION) {
//                    System.exit(0);
//                }
//            }
//        });
//
//        logoutPanel.add(logoutLabel);
//        logoutPanel.add(logoutButton);
//        storeInfoPanel.add(logoutPanel, BorderLayout.SOUTH);
//
//        return storeInfoPanel;
//    }

    private void showStoreInfoPanel() {
        tabbedPane.addTab("가맹점 정보", storeInfoPanel);
        tabbedPane.setSelectedComponent(storeInfoPanel);
    }

}
