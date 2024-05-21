package main.java.com.ouibak.erp.gui.util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public interface ButtonUtil{
    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;
        private JTable table;
        private DefaultTableModel tableModel;
        private String actionType;

        public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel, JTable table, String actionType) {
            super(checkBox);
            this.tableModel = tableModel;
            this.table = table;
            this.actionType = actionType;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            JButton button = new JButton(label);
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
            return button;
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

};

//class ButtonEditor extends DefaultCellEditor {
//    private String label;
//    private boolean isPushed;
//    private JTable table;
//    private DefaultTableModel tableModel;
//    private String actionType;
//
//    public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel, JTable table, String actionType) {
//        super(checkBox);
//        this.tableModel = tableModel;
//        this.table = table;
//        this.actionType = actionType;
//    }
//
//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        label = (value == null) ? "" : value.toString();
//        JButton button = new JButton(label);
//        button.setOpaque(true);
//        button.addActionListener(e -> fireEditingStopped());
//        return button;
//    }
//}
//
//class ButtonRenderer extends JButton implements TableCellRenderer {
//    public ButtonRenderer() {
//        setOpaque(true);
//    }
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        setText((value == null) ? "" : value.toString());
//        return this;
//    }
//}