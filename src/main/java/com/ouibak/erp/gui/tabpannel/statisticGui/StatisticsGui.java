package main.java.com.ouibak.erp.gui.tabpannel.statisticGui;

import main.java.com.ouibak.erp.domain.product.ProductVO;
import main.java.com.ouibak.erp.domain.statistics.StatisticsService;
import main.java.com.ouibak.erp.domain.statistics.StatisticsServieImpl;
import com.toedter.calendar.JDateChooser;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsGui extends JPanel {
    private JComboBox<String> productSearchField;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private JButton searchButton;

    private ChartPanel chartPanel;

    private StatisticsService service;
    private List<String> availableProducts;

    private void getData() {
        service = StatisticsServieImpl.getInstance();
        availableProducts = ProductVO.getProductNames();
    }

    public JPanel createStatisticsPanel() {
        getData();
        setLayout(new BorderLayout());

        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        statisticsPanel.add(new JLabel("상품 명"));

        productSearchField = new JComboBox<>(availableProducts.toArray(new String[availableProducts.size()]));
        productSearchField.setPreferredSize(new Dimension(150, 25));
        productSearchField.setEditable(true);
        AutoCompleteDecorator.decorate(productSearchField);
        statisticsPanel.add(productSearchField);

        statisticsPanel.add(new JLabel("시작 날짜"));
        startDateChooser = new JDateChooser();
        startDateChooser.setPreferredSize(new Dimension(150, 25));
        statisticsPanel.add(startDateChooser);

        statisticsPanel.add(new JLabel("종료 날짜"));
        endDateChooser = new JDateChooser();
        endDateChooser.setPreferredSize(new Dimension(150, 25));
        statisticsPanel.add(endDateChooser);

        searchButton = new JButton("검색");
        statisticsPanel.add(searchButton);

        //MAX
        chartPanel = new ChartPanel();
        chartPanel.setPreferredSize(new Dimension(900, 523));
        statisticsPanel.add(chartPanel, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<List<Integer>> test = searchAndDisplayChart();
                chartPanel.updateData(
                        test.get(0),
                        test.get(1)
                );
            }
        });

        return statisticsPanel;
    }

    private List<List<Integer>> searchAndDisplayChart() {
        String productName = (String) productSearchField.getSelectedItem();
        java.util.Date startDate = startDateChooser.getDate();
        java.util.Date endDate = endDateChooser.getDate();

        if (productName == null || startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "모든 필드를 입력해 주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        List<List<Integer>> dataPoints = fetchData(productName, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));


        if (dataPoints.isEmpty()) {
            JOptionPane.showMessageDialog(this, "해당 기간에 대한 데이터가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        else {
            dataPoints.add(Arrays.asList(setMaxData(dataPoints)));
        }
        return dataPoints;
    }

    private int setMaxData(List<List<Integer>> dataPoints){
        List<Integer> list = dataPoints.stream().flatMap(lst -> lst.stream()).collect(Collectors.toList());
        return Collections.max(list);
    }


    //
    private List<List<Integer>> fetchData(String productName, java.sql.Date startDate, java.sql.Date endDate) {
        List<List<Integer>> dataPoints = new ArrayList<>();
        try {
            dataPoints = service.getStatistics( productName );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dataPoints;
    }
}