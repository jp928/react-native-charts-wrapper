package com.github.wuxudong.rncharts.charts;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.wuxudong.rncharts.data.BarDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

public class BarChartManager extends BarLineChartBaseManager<BarChart, BarEntry> {

    @Override
    public String getName() {
        return "RNBarChart";
    }

    @Override
    protected BarChart createViewInstance(ThemedReactContext reactContext) {
        BarChart barChart = new BarChart(reactContext);
        barChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(barChart));
        barChart.setOnChartGestureListener(new RNOnChartGestureListener(barChart));
        YAxis left = barChart.getAxisLeft();
        left.setDrawGridLines(false);
        left.setDrawAxisLine(true);
        left.setAxisMinimum(0f);

        return barChart;
    }

    @Override
    DataExtract getDataExtract() {
        return new BarDataExtract();
    }

    @ReactProp(name = "barRadius")
    public void setBoarderRadius(BarChart chart, Integer radius) {
        if (radius != null) {
            RoundedBarChartRenderer roundedBarChartRenderer = new RoundedBarChartRenderer(chart, chart.getAnimator(), chart.getViewPortHandler(), radius);
            chart.setRenderer(roundedBarChartRenderer);
        }
    }

    @ReactProp(name = "drawValueAboveBar")
    public void setDrawValueAboveBar(BarChart chart, boolean enabled) {
        chart.setDrawValueAboveBar(enabled);
    }

    @ReactProp(name = "drawBarShadow")
    public void setDrawBarShadow(BarChart chart, boolean enabled) {
        chart.setDrawBarShadow(enabled);
    }

    @ReactProp(name = "highlightFullBarEnabled")
    public void setHighlightFullBarEnabled(BarChart chart, boolean enabled) {
        chart.setHighlightFullBarEnabled(enabled);
    }

    @ReactProp(name = "descriptionEnabled")
    public void setDescriptionEnabled(BarChart chart, boolean enabled) {
        chart.getDescription().setEnabled(enabled);
    }

    @ReactProp(name = "rightYAxisEnabled")
    public void setRightYAxisEnabled(BarChart chart, boolean enabled) {
        chart.getAxisRight().setEnabled(enabled);
    }
}
