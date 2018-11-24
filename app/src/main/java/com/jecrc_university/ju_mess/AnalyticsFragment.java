package com.jecrc_university.ju_mess;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalyticsFragment extends Fragment {


    public AnalyticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_analytics, container, false);
        // TODO OnCreate Starts Here

        BarChart barChart1 = (BarChart)view.findViewById(R.id.bar_chart1);
        BarChart barChart2 = (BarChart)view.findViewById(R.id.bar_chart2);
        BarChart barChart3 = (BarChart)view.findViewById(R.id.bar_chart3);
        BarChart barChart4 = (BarChart)view.findViewById(R.id.bar_chart4);


        chart1();

        ArrayList<BarEntry> BarEntry = new ArrayList<>();
        BarEntry.add(new BarEntry(1,5));
        BarEntry.add(new BarEntry(2,3));
        BarEntry.add(new BarEntry(3,9));
        BarEntry.add(new BarEntry(4,8));
        BarEntry.add(new BarEntry(5,1));
        BarEntry.add(new BarEntry(6,3));


        BarDataSet dataSet = new BarDataSet(BarEntry,"Projects");

        final ArrayList<String> labels = new ArrayList<>();

        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");



        XAxis xval = barChart1.getXAxis();
        xval.setDrawLabels(true);
        xval.setValueFormatter(new AxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return  labels.get((int) value-1);
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        xval.setDrawGridLines(false);
        xval.setPosition(XAxis.XAxisPosition.BOTTOM);

        // finish


        BarData barData = new BarData(dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setBarShadowColor(Color.BLACK);


        barChart1.setData(barData);
        barChart1.setDescription("No of Projects");
        barChart1.animateXY(1400,1400);

        barChart2.setData(barData);
        barChart2.setDescription("No of Projects");
        barChart2.animateXY(1400,1400);

        barChart3.setData(barData);
        barChart3.setDescription("No of Projects");
        barChart3.animateXY(1400,1400);

        barChart4.setData(barData);
        barChart4.setDescription("No of Projects");
        barChart4.animateXY(1400,1400);



        return view;
    }

    private void chart1() {
    }

}
