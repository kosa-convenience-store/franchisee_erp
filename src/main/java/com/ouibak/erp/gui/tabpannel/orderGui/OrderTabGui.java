//package main.java.com.ouibak.erp.gui.tabpannel.orderGui;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class OrderTabGui extends JFrame {
//    private JPanel cardPanel;
//    private CardLayout cardLayout;
//
//    public JPanel createOrderManagementPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//
//        JPanel sidePanel = new JPanel(new GridLayout(2, 1));
//        JButton registerButton = new JButton("발주 등록");
//        JButton historyButton = new JButton("발주 내역");
//        sidePanel.add(registerButton);
//        sidePanel.add(historyButton);
//
//        cardLayout = new CardLayout();
//        cardPanel = new JPanel(cardLayout);
//        cardPanel.add(new OrderGui().createOrderRegistrationPanel(), "발주 등록");
//        cardPanel.add(new OrderListGui().createOrderHistoryPanel(), "발주 내역");
//
//        panel.add(sidePanel, BorderLayout.WEST);
//        panel.add(cardPanel, BorderLayout.CENTER);
//
//        registerButton.addActionListener(e -> cardLayout.show(cardPanel, "발주 등록"));
//        historyButton.addActionListener(e -> cardLayout.show(cardPanel, "발주 내역"));
//
//        return panel;
//    }
//
//}

package main.java.com.ouibak.erp.gui.tabpannel.orderGui;

import javax.swing.*;
import java.awt.*;

public class OrderTabGui extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private OrderListGui orderListGui;

    public OrderListGui getOrderListGui() {
        return orderListGui;
    }

    public JPanel createOrderManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel sidePanel = new JPanel(new GridLayout(2, 1));
        JButton registerButton = new JButton("발주 등록");
        JButton historyButton = new JButton("발주 내역");
        sidePanel.add(registerButton);
        sidePanel.add(historyButton);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(new OrderGui().createOrderRegistrationPanel(), "발주 등록");
        orderListGui = new OrderListGui();
        cardPanel.add(orderListGui.createOrderHistoryPanel(), "발주 내역");

        panel.add(sidePanel, BorderLayout.WEST);
        panel.add(cardPanel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> cardLayout.show(cardPanel, "발주 등록"));
        historyButton.addActionListener(e -> {
            orderListGui.resetTableData();  // 발주 내역 갱신
            cardLayout.show(cardPanel, "발주 내역");
        });

        return panel;
    }

}
