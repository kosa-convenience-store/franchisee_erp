package main.java.com.ouibak.erp.gui.loginpannel;

import main.java.com.ouibak.erp.gui.tabbedMain.TabbedGui;

import javax.swing.*;

public class LoginGui extends javax.swing.JFrame {
    private javax.swing.JTextField inputIdTextField;
    private javax.swing.JPasswordField inputPasswordField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loginButton;

    //
    public LoginGui() {
        initComponents();
    }

    //
    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("XXXX ERP 프로그램");
        setSize(1115, 672);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1 = new javax.swing.JLabel();
        inputIdTextField = new javax.swing.JTextField();
        inputPasswordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("휴먼엑스포", 1, 48)); // NOI18N
        jLabel1.setText("XXXX ERP PROGRAM");

        inputIdTextField.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        inputIdTextField.setText("ID");

        inputPasswordField.setText("Password");

        loginButton.setText("로그인");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        //layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        //
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
        //
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

//    private void inputIdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputIdTextFieldActionPerformed
//        // TODO add your handling code here:
//    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Get the text from the input fields
        String userId = inputIdTextField.getText();
        String password = new String(inputPasswordField.getPassword());

        // For demonstration purposes, print out the values
        System.out.println("User ID: " + userId);
        System.out.println("Password: " + password);

        // Perform login logic here (e.g., authenticate the user)

        // If login is successful, show the TabbedGui
        if (userId.equals("system") && password.equals("0000")) {
            new TabbedGui().setVisible(true);
            setVisible(false);
        }
    }


}

