package main.java.com.ouibak.erp.gui.tabpannel.statisticGui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class ChartPanel extends JPanel {
    private List<Integer> salesData;
    private List<Integer> orderData;
    private int maxData;

    public ChartPanel() {
        setPreferredSize(new Dimension(900, 523));
        setBackground(Color.WHITE); // Set background color to white
    }

    public void updateData(List<Integer> salesData, List<Integer> orderData) {
        this.salesData = salesData;
        this.orderData = orderData;
        this.maxData = Math.max(7, Math.max(getMaxValue(salesData), getMaxValue(orderData))) + 3;
        repaint();
    }

    private int getMaxValue(List<Integer> data) {
        return data.stream().max(Integer::compare).orElse(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (salesData != null && orderData != null) {
            drawAxes(g);
            drawLineChart(g, salesData, Color.GREEN, "Sales");
            drawLineChart(g, orderData, Color.orange, "Orders");
        }
    }

    private void drawAxes(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int padding = 50;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;

        // Draw X-axis and Y-axis
        g2d.drawLine(padding, height + padding, width + padding, height + padding); // X-axis
        g2d.drawLine(padding, padding, padding, height + padding); // Y-axis

        // Axis labels
        g2d.drawString("요일", width + padding / 2, height + padding + 40);
        g2d.drawString("평균 개수", padding - 40, padding - 10);

        // Draw X-axis labels
        String[] days = {"일", "월", "화", "수", "목", "금", "토"};
        int pointWidth = width / (salesData.size() - 1);
        for (int i = 0; i < salesData.size(); i++) {
            g2d.drawString(days[i % 7], padding + i * pointWidth - 10, height + padding + 20);
        }

        // Draw Y-axis labels and ticks
        for (int i = 0; i <= maxData; i++) {
            int y = height + padding - (i * height / maxData);
            g2d.drawString(Integer.toString(i), padding - 30, y + 5);
            g2d.drawLine(padding - 5, y, padding, y);
        }
    }

    private void drawLineChart(Graphics g, List<Integer> data, Color color, String name) {
        if (data.size() < 2) return;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2));

        int padding = 50;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;
        int pointWidth = width / (data.size() - 1);

        int previousX = padding;
        int previousY = height + padding - (data.get(0) * height / maxData);  // Adjust scaling accordingly
        for (int i = 1; i < data.size(); i++) {
            int x = padding + i * pointWidth;
            int y = height + padding - (data.get(i) * height / maxData);  // Adjust scaling accordingly
            g2d.drawLine(previousX, previousY, x, y);
            previousX = x;
            previousY = y;
        }

        // Draw series name legend
        g2d.setColor(Color.BLACK);
        g2d.drawString(name, previousX + 5, previousY - 5);
    }
}
