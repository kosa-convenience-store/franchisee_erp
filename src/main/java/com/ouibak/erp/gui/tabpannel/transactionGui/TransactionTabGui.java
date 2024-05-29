package main.java.com.ouibak.erp.gui.tabpannel.transactionGui;

import javax.swing.*;
import java.awt.*;

public class TransactionTabGui extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private TransactionListGui transactionListGui;

    public TransactionTabGui() {
        transactionListGui = new TransactionListGui();
    }

    public TransactionListGui getTransactionListGui() {
        return transactionListGui;
    }

    public JPanel createTransactionManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel sidePanel = new JPanel(new GridLayout(2, 1));
        JButton registerButton = new JButton("결제");
        JButton historyButton = new JButton("결제 내역");
        sidePanel.add(registerButton);
        sidePanel.add(historyButton);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(new TransactionGui().createTransactionPanel(), "결제");
        cardPanel.add(transactionListGui.createTransactionPanel(), "결제 내역"); // 동일 인스턴스를 사용

        panel.add(sidePanel, BorderLayout.WEST);
        panel.add(cardPanel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> cardLayout.show(cardPanel, "결제"));
        historyButton.addActionListener(e -> {
            transactionListGui.resetTransacTableData(); // transactionListGui 인스턴스를 재사용
            cardLayout.show(cardPanel, "결제 내역");
        });

        return panel;
    }
}
