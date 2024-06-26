package main.java.com.ouibak.erp.main;
import main.java.com.ouibak.erp.gui.loginpannel.LoginGui;

public class MainDriver {
    public static void main(String[] args) {

       setLookAndFeel();
       java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
               new LoginGui().setVisible(true);
           }
       });
   }

   private static void setLookAndFeel() {
       try {
           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
               if ("Nimbus".equals(info.getName())) {
                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   break;
               }
           }
       } catch (ClassNotFoundException ex) {
           java.util.logging.Logger.getLogger(MainDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (InstantiationException ex) {
           java.util.logging.Logger.getLogger(MainDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (IllegalAccessException ex) {
           java.util.logging.Logger.getLogger(MainDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
           java.util.logging.Logger.getLogger(MainDriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       }
    }
}



