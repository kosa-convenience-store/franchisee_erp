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
    private JPanel paymentPanel, invenPanel, orderPanel, statisticsPanel, storeInfoPanel;
    private InventoryGui inventoryGui;

    public TabbedGui() {
        setTitle("XXXX ERP 프로그램");
        setSize(1115, 672);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        statisticsPanel = new StatisticsGui().createStatisticsPanel();
        storeInfoPanel = new InfoGui().createStoreInfoPanel();

        //Add tabs to the tabbed pane
        tabbedPane.addTab("결제", paymentPanel);
        tabbedPane.addTab("재고", invenPanel);
        tabbedPane.addTab("발주", orderPanel);
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

    private void showStoreInfoPanel() {
        tabbedPane.addTab("가맹점 정보", storeInfoPanel);
        tabbedPane.setSelectedComponent(storeInfoPanel);
    }

}
