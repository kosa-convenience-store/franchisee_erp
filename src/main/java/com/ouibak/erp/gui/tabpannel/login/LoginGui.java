//package main.java.com.ouibak.erp.gui.tabpannel.login;
//
//import main.java.com.ouibak.erp.gui.tabbedMain.TabbedGui;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class LoginGui extends JPanel {
//    private JTabbedPane tabbedPane;
//
//    public JPanel createLoginPannel(){
//
//        JPanel loginPanel = new JPanel();
//        JLabel usernameLabel = new JLabel("Username:");
//        JTextField usernameField = new JTextField(10);
//        JLabel passwordLabel = new JLabel("Password:");
//        JPasswordField passwordField = new JPasswordField(10);
//        JButton loginButton = new JButton("Login");
//
//        //Add components to login panel
//        loginPanel.add(usernameLabel);
//        loginPanel.add(usernameField);
//        loginPanel.add(passwordLabel);
//        loginPanel.add(passwordField);
//        loginPanel.add(loginButton);
//
//        // Add action listener for login button
//        loginButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Perform login authentication here
//                // If login successful, switch to tab 1
//                tabbedPane.setSelectedIndex(1); // Tab index 1 corresponds to Tab 1
//            }
//        });
//
//        return loginPanel;
//    }
//}
