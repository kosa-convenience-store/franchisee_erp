package main.java.com.ouibak.erp.gui.tabbedMain;

import main.java.com.ouibak.erp.gui.tabpannel.orderGui.OrderTabGui;
import main.java.com.ouibak.erp.gui.tabpannel.statisticGui.StatisticsGui;
import main.java.com.ouibak.erp.gui.tabpannel.transactionGui.TransacTabGui;

import javax.swing.*;
import java.awt.*;

public class TabbedGui extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel paymentPanel, invenPanel, orderPanel, wareHousingPanel,
            disposePanel, salesPanel, statisticsPanel;

    public TabbedGui() {
        setTitle("XXXX ERP 프로그램");
        setSize(1115, 672);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel storeLabelPanel = new JLabel("xxxx 가맹점  ", SwingConstants.RIGHT);
        storeLabelPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        storeLabelPanel.setFont(new Font("맑은 고딕", 0, 14));
        northPanel.add(storeLabelPanel, BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);


        //create the tabbed pane
        tabbedPane = new JTabbedPane();

        //create panels for each tab
        paymentPanel = new TransacTabGui().createTransactionPanel();
        invenPanel = new JPanel();
        orderPanel = new OrderTabGui().createOrderManagementPanel();
        wareHousingPanel = new JPanel();
        disposePanel = new JPanel();
        salesPanel = new JPanel();
        statisticsPanel = new StatisticsGui().createStatisticsPanel();

        //Add tabs to the tabbed pane
        tabbedPane.addTab("결제", paymentPanel);
        tabbedPane.addTab("재고", invenPanel);
        tabbedPane.addTab("발주", orderPanel);
        tabbedPane.addTab("입고", wareHousingPanel);
        tabbedPane.addTab("폐기", disposePanel);
        tabbedPane.addTab("판매내역", salesPanel);
        tabbedPane.addTab("통계", statisticsPanel);

        //Add tabbed pane to the frame
        add(tabbedPane, BorderLayout.CENTER);

    }
}

