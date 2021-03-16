package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.formatter.ValueFormatter;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CustomFormatter extends ValueFormatter {

    private DecimalFormat mFormat;

    public CustomFormatter(String value) {
        mFormat = new DecimalFormat(value);
        mFormat.setRoundingMode(RoundingMode.DOWN);
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value);
    }

}
