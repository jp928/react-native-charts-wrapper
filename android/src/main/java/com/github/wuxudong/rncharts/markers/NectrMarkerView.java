package com.github.wuxudong.rncharts.markers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.wuxudong.rncharts.R;

import java.util.List;
import java.util.Map;

public class NectrMarkerView extends MarkerView {
    private TextView tvContent;
    private RelativeLayout root;

    private Drawable backgroundLeft = ResourcesCompat.getDrawable(getResources(), R.drawable.left, null);
    private Drawable backgroundRight = ResourcesCompat.getDrawable(getResources(), R.drawable.right, null);
    private Drawable backgroundRightBg = ResourcesCompat.getDrawable(getResources(), R.drawable.right_bg, null);
    private Drawable backgroundLeftBg = ResourcesCompat.getDrawable(getResources(), R.drawable.left_bg, null);

    private int digits = 0;

    public NectrMarkerView(Context context) {
        super(context, R.layout.nectr_marker);

        tvContent = (TextView) findViewById(R.id.nectr_content);
        root = (RelativeLayout) findViewById(R.id.nectr_marker);
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String text = Utils.formatNumber(e.getY(), digits, false)
                .replace(",", ".")
                .concat(" kWh");

        if (e.getData() instanceof Map) {
            if (((Map) e.getData()).containsKey("marker")) {

                Object marker = ((Map) e.getData()).get("marker");
                text = marker.toString();

                if (highlight.getStackIndex() != -1 && marker instanceof List) {
                    text = ((List) marker).get(highlight.getStackIndex()).toString();
                }

            }
        }

        if (TextUtils.isEmpty(text)) {
            tvContent.setVisibility(INVISIBLE);
        } else {
            tvContent.setText(text);
            tvContent.setVisibility(VISIBLE);
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-getWidth(), -getHeight());
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {

        MPPointF offset = getOffset();

        MPPointF offset2 = new MPPointF();

        offset2.x = offset.x;
        offset2.y = offset.y + 24;

        if (posX < 240) {
            offset2.x = - 30;
            tvContent.setBackground(backgroundLeft);
            root.setBackground(backgroundLeftBg);
        } else {
            offset2.x = - 364;
            tvContent.setBackground(backgroundRight);
            root.setBackground(backgroundRightBg);
        }

        return offset2;
    }

    public TextView getTvContent() {
        return tvContent;
    }

}

