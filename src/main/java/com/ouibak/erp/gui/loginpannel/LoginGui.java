package main.java.com.ouibak.erp.gui.loginpannel;

import main.java.com.ouibak.erp.domain.franchisee.FranchiseeController;
import main.java.com.ouibak.erp.gui.Cookie;
import main.java.com.ouibak.erp.gui.tabbedMain.TabbedGui;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginGui extends javax.swing.JFrame {
    private FranchiseeController controller;

    private JTextField inputIdTextField;
    private JPasswordField inputPasswordField;
    private JLabel jLabel1;
    private JButton loginButton;

    public LoginGui() {
        controller = new FranchiseeController();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("KOSA ERP 프로그램");
        setSize(1115, 672);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        jLabel1 = new javax.swing.JLabel();
        inputIdTextField = new javax.swing.JTextField();
        inputPasswordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("휴먼엑스포", 1, 48));
        jLabel1.setText("KOSA ERP PROGRAM");

        inputIdTextField.setFont(new java.awt.Font("맑은 고딕", 0, 14));
        inputIdTextField.setText("ID");
        inputIdTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputIdTextField.getText().equals("ID")) {
                    inputIdTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputIdTextField.getText().isEmpty()) {
                    inputIdTextField.setText("ID");
                }
            }
        });
        inputIdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        inputPasswordField.setFont(new java.awt.Font("맑은 고딕", 0, 14));
        inputPasswordField.setText("Password");
        inputPasswordField.setEchoChar((char) 0); // Show placeholder text
        inputPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(inputPasswordField.getPassword()).equals("Password")) {
                    inputPasswordField.setText("");
                    inputPasswordField.setEchoChar('●'); // Hide text with bullet characters
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(inputPasswordField.getPassword()).isEmpty()) {
                    inputPasswordField.setText("Password");
                    inputPasswordField.setEchoChar((char) 0); // Show placeholder text
                }
            }
        });
        inputPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        loginButton.setText("로그인");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        // 포커스 순서 설정
        inputIdTextField.setNextFocusableComponent(inputPasswordField);
        inputPasswordField.setNextFocusableComponent(loginButton);

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

        // 초기 포커스를 없애기 위해 타이머를 사용하여 포커스를 설정합니다.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getContentPane().requestFocusInWindow();
            }
        });
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // 입력 필드에서 텍스트 가져오기
        String userId = inputIdTextField.getText();
        String password = new String(inputPasswordField.getPassword());
        int result = controller.validateLogin(userId, password);

        // 로그인 검증 함수 결과 출력
        if (result == -1) {
            JOptionPane.showMessageDialog(this, "아이디가 틀렸습니다. 다시 시도하세요.");
        } else if (result == -2) {
            JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다. 다시 시도하세요.");
        } else if (result == -99) {
            JOptionPane.showMessageDialog(this, "데이터베이스 오류가 발생했습니다.");
        } else {
            JOptionPane.showMessageDialog(this, "로그인 성공");
            Cookie.setFranchiseeIdx(result);
            new TabbedGui().setVisible(true);
            setVisible(false);
        }
    }
}
