package main.java.com.ouibak.erp.gui.tabpannel.statisticGui;

import org.knowm.xchart.internal.chartpart.Chart;

public interface ExampleChart<C extends Chart<?, ?>> {

    C getChart();
}