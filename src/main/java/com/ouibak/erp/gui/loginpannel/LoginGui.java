package main.java.com.ouibak.erp.gui.loginpannel;

import main.java.com.ouibak.erp.domain.franchisee.FranchiseeDao;
import main.java.com.ouibak.erp.gui.tabbedMain.TabbedGui;

import javax.swing.*;

public class LoginGui extends javax.swing.JFrame {
    private javax.swing.JTextField inputIdTextField;
    private javax.swing.JPasswordField inputPasswordField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loginButton;

    public LoginGui() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("XXXX ERP 프로그램");
        setSize(1115, 672);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1 = new javax.swing.JLabel();
        inputIdTextField = new javax.swing.JTextField();
        inputPasswordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("휴먼엑스포", 1, 48));
        jLabel1.setText("XXXX ERP PROGRAM");

        inputIdTextField.setFont(new java.awt.Font("맑은 고딕", 0, 14));
        inputIdTextField.setText("ID");

        inputPasswordField.setText("Password");

        loginButton.setText("로그인");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(303, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(275, 275, 275))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(inputPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(inputIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(365, 365, 365))))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(jLabel1)
                                .addGap(93, 93, 93)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(inputIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(inputPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(277, Short.MAX_VALUE))
        );

        pack();
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        FranchiseeDao dao = new FranchiseeDao();

            // 입력 필드에서 텍스트 가져오기
            String userId = inputIdTextField.getText();
            String password = new String(inputPasswordField.getPassword());
            int result = dao.validateLogin(userId, password);
            System.out.println(result);

            // 로그인 검증 함수 결과 출력
            if (result == -1) {
                JOptionPane.showMessageDialog(this, "아이디가 틀렸습니다. 다시 시도하세요.");
            } else if (result == -2) {
                JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다. 다시 시도하세요.");
            } else if (result == -99) {
                JOptionPane.showMessageDialog(this, "데이터베이스 오류가 발생했습니다.");
            } else {
                JOptionPane.showMessageDialog(this, "로그인 성공");
                new TabbedGui().setVisible(true);
                setVisible(false);
            }
    }
}
