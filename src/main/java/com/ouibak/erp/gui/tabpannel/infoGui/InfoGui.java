package main.java.com.ouibak.erp.gui.tabpannel.infoGui;

import main.java.com.ouibak.erp.domain.franchisee.FranchiseeController;
import main.java.com.ouibak.erp.gui.Cookie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoGui extends Component {
    private FranchiseeController controller;

    private JTextField errorRateField;
    private JButton editButton, logoutButton;

    public InfoGui() {
        controller = new FranchiseeController();
    }

    public JPanel createStoreInfoPanel() {
        JPanel storeInfoPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new GridLayout(0, 1));
        leftPanel.setBorder(BorderFactory.createTitledBorder("가맹점 기본 정보"));
        String[] franchiseeInfo = controller.getFranchiseeInfo(Cookie.getFranchiseeIdx());
        leftPanel.add(new JLabel("가맹점 이름: " + franchiseeInfo[0]));
        leftPanel.add(new JLabel("가맹점 주소: " + franchiseeInfo[1]));
        leftPanel.add(new JLabel("가맹점 사업자 번호: " + franchiseeInfo[2]));
        leftPanel.add(new JLabel("가맹점 전화번호: " + franchiseeInfo[3]));

        // Add error rate setting label and text field with edit button
        JPanel errorRatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel errorRateLabel = new JLabel("발주 에러 방지 비율 설정: ");
        String orderErrorRate = String.valueOf(controller.getOrderErrorRate(Cookie.getFranchiseeIdx()));

        errorRateField = new JTextField(orderErrorRate, 3);
        errorRateField.setEditable(false);
        editButton = new JButton("수정");

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (errorRateField.isEditable()) {
                    errorRateField.setEditable(false);
                    editButton.setText("수정");
                    controller.updateOrderErrorRate(Cookie.getFranchiseeIdx(), errorRateField.getText());
                } else {
                    errorRateField.setEditable(true);
                    editButton.setText("저장");

                }
            }
        });

        errorRatePanel.add(errorRateLabel);
        errorRatePanel.add(errorRateField);
        errorRatePanel.add(editButton);
        leftPanel.add(errorRatePanel);

        JPanel rightPanel = new JPanel(new GridLayout(0, 1));
        rightPanel.setBorder(BorderFactory.createTitledBorder("지난 주 판매 순위"));

        Object[][] productRanck = controller.getFranchiseeProductRank(Cookie.getFranchiseeIdx());
        rightPanel.add(new JLabel("1위: 상품 | " + productRanck[0][0] + " , 판매 수량: " + productRanck[0][1]));
        rightPanel.add(new JLabel("2위: 상품 | " + productRanck[1][0] + " , 판매 수량: " + productRanck[1][1]));
        rightPanel.add(new JLabel("3위: 상품 | " + productRanck[2][0] + " , 판매 수량: " + productRanck[2][1]));
        rightPanel.add(new JLabel("4위: 상품 | " + productRanck[3][0] + " , 판매 수량: " + productRanck[3][1]));
        rightPanel.add(new JLabel("5위: 상품 | " + productRanck[4][0] + " , 판매 수량: " + productRanck[4][1]));


        // Use JSplitPane to set the ratio between leftPanel and rightPanel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.7); // 7:3 ratio
        splitPane.setDividerLocation(0.7); // Set initial location of the divider

        storeInfoPanel.add(splitPane, BorderLayout.CENTER);

        // Add logout section at the bottom
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutButton = new JButton("로그아웃");
        Dimension buttonSize = new Dimension(100, 25); // Set the desired size
        logoutButton.setPreferredSize(buttonSize);
        logoutButton.setMinimumSize(buttonSize);
        logoutButton.setMaximumSize(buttonSize);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(InfoGui.this,
                        "정말 로그아웃 하시겠습니까?", "로그아웃 확인",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        logoutPanel.add(logoutButton);
        storeInfoPanel.add(logoutPanel, BorderLayout.SOUTH);

        return storeInfoPanel;
    }

}
