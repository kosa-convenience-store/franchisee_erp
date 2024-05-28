package main.java.com.ouibak.erp.gui.tabpannel.statisticGui;

import com.toedter.calendar.JDateChooser;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.List;

public class TestTestGui extends JFrame {
    private JTabbedPane tabbedPane;
    private StatisticsGui statisticsPanel;

    public void TestTest() {
        setTitle("XXX ERP 프로그램");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("홈 패널"));
        tabbedPane.addTab("홈", homePanel);

        JPanel paymentPanel = new JPanel();
        paymentPanel.add(new JLabel("결제 패널"));
        tabbedPane.addTab("결제", paymentPanel);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.add(new JLabel("재고 패널"));
        tabbedPane.addTab("재고", inventoryPanel);

        JPanel orderPanel = new JPanel();
        orderPanel.add(new JLabel("발주 패널"));
        tabbedPane.addTab("발주", orderPanel);

        JPanel receivingPanel = new JPanel();
        receivingPanel.add(new JLabel("입고 패널"));
        tabbedPane.addTab("입고", receivingPanel);

        JPanel disposalPanel = new JPanel();
        disposalPanel.add(new JLabel("폐기 패널"));
        tabbedPane.addTab("폐기", disposalPanel);

        JPanel salesSettlementPanel = new JPanel();
        salesSettlementPanel.add(new JLabel("판매결산 패널"));
        tabbedPane.addTab("판매결산", salesSettlementPanel);

        statisticsPanel = new StatisticsGui();
        tabbedPane.addTab("통계", statisticsPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TestTestGui mainFrame = new TestTestGui();
            mainFrame.setVisible(true);
        });
    }
}

class StatisticsPanelTest extends JPanel {
    private JComboBox<String> productSearchField;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private JButton searchButton;
    private JPanel chartPanel;

    public StatisticsPanelTest() {
        setLayout(new BorderLayout());

        // Form panel with centered layout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        formPanel.add(new JLabel("상품 명"));

        productSearchField = new JComboBox<>(fetchProductNames());
        productSearchField.setEditable(true);
        AutoCompleteDecorator.decorate(productSearchField);
        formPanel.add(productSearchField);

        formPanel.add(new JLabel("시작 날짜"));
        startDateChooser = new JDateChooser();
        formPanel.add(startDateChooser);

        formPanel.add(new JLabel("종료 날짜"));
        endDateChooser = new JDateChooser();
        formPanel.add(endDateChooser);

        searchButton = new JButton("검색");
        formPanel.add(searchButton);

        chartPanel = new JPanel(new BorderLayout());

        add(formPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAndDisplayChart();
            }
        });
    }

    private void searchAndDisplayChart() {
        String productName = (String) productSearchField.getSelectedItem();
        Date startDate = startDateChooser.getDate();
        Date endDate = endDateChooser.getDate();

        if (productName == null || startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "모든 필드를 입력해 주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<DataPoint> dataPoints = fetchData(productName, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));

        Map<String, List<Integer>> salesData = new HashMap<>();
        Map<String, List<Integer>> orderData = new HashMap<>();
        Map<String, List<Integer>> stockData = new HashMap<>();

        for (DataPoint dataPoint : dataPoints) {
            String dayOfWeek = dataPoint.getDayOfWeek();
            salesData.computeIfAbsent(dayOfWeek, k -> new ArrayList<>()).add(dataPoint.getSales());
            orderData.computeIfAbsent(dayOfWeek, k -> new ArrayList<>()).add(dataPoint.getOrders());
            stockData.computeIfAbsent(dayOfWeek, k -> new ArrayList<>()).add(dataPoint.getStock());
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String dayOfWeek : salesData.keySet()) {
            int avgSales = (int) salesData.get(dayOfWeek).stream().mapToInt(Integer::intValue).average().orElse(0);
            int avgOrders = (int) orderData.get(dayOfWeek).stream().mapToInt(Integer::intValue).average().orElse(0);
            int avgStock = (int) stockData.get(dayOfWeek).stream().mapToInt(Integer::intValue).average().orElse(0);

            dataset.addValue(avgSales, "판매량", dayOfWeek);
            dataset.addValue(avgOrders, "발주량", dayOfWeek);
            dataset.addValue(avgStock, "재고", dayOfWeek);
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "상품 명",
                "요일",
                "개수",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        this.chartPanel.removeAll();
        this.chartPanel.add(chartPanel, BorderLayout.CENTER);
        this.chartPanel.revalidate();
    }

    private List<DataPoint> fetchData(String productName, java.sql.Date startDate, java.sql.Date endDate) {
        List<DataPoint> dataPoints = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "your_username";
        String password = "your_password";

        String query = "SELECT DAYNAME(date) as day, sales, orders, stock " +
                "FROM your_table " +
                "WHERE product_name = ? AND date BETWEEN ? AND ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, productName);
            stmt.setDate(2, startDate);
            stmt.setDate(3, endDate);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String dayOfWeek = rs.getString("day");
                int sales = rs.getInt("sales");
                int orders = rs.getInt("orders");
                int stock = rs.getInt("stock");

                dataPoints.add(new DataPoint(dayOfWeek, sales, orders, stock));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataPoints;
    }

    private String[] fetchProductNames() {
        List<String> productNames = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "your_username";
        String password = "your_password";

        String query = "SELECT DISTINCT product_name FROM your_table";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                productNames.add(rs.getString("product_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productNames.toArray(new String[0]);
    }
}

class DataPoint {
    private final String dayOfWeek;
    private final int sales;
    private final int orders;
    private final int stock;

    public DataPoint(String dayOfWeek, int sales, int orders, int stock) {
        this.dayOfWeek = dayOfWeek;
        this.sales = sales;
        this.orders = orders;
        this.stock = stock;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getSales() {
        return sales;
    }

    public int getOrders() {
        return orders;
    }

    public int getStock() {
        return stock;
    }
}