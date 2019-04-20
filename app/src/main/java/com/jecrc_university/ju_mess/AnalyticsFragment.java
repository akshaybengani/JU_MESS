package com.jecrc_university.ju_mess;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalyticsFragment extends Fragment
        implements View.OnClickListener {

    private int my_day,my_month,my_year;
    private Button buttondatepicker;
    private int mYear, mMonth, mDay;

   // private String mySelCurDate;

        private BarChart barChartBreakfast,barChartLunch, barChartSnacks,barChartDinner;

    public AnalyticsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_analytics,
                container, false);
        // TODO OnCreate Starts Here

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        my_day = mDay;
        my_month = mMonth+1;
        my_year = mYear;

        // TODO Current Date
        //mySelCurDate = ""+my_day+""+my_month+""+my_year;

        buttondatepicker = (Button)view.findViewById(R.id.buttonDatePickerAnalytics);

        barChartBreakfast = (BarChart)view.findViewById(R.id.bar_chartBreakfast);
        barChartLunch = (BarChart)view.findViewById(R.id.bar_chartLunch);
        barChartSnacks = (BarChart)view.findViewById(R.id.bar_chartSnacks);
        barChartDinner = (BarChart)view.findViewById(R.id.bar_chartDinner);



        buttondatepicker.setText(""+mDay + "-" + (mMonth + 1) +
                "-" + mYear);


        buttondatepicker.setOnClickListener(this);
        barChartBreakfast.setOnClickListener(this);
        barChartLunch.setOnClickListener(this);
        barChartSnacks.setOnClickListener(this);
        barChartDinner.setOnClickListener(this);


        ArrayList<BarEntry> BarEntry = new ArrayList<>();
        BarEntry.add(new BarEntry(1,5));
        BarEntry.add(new BarEntry(2,3));
        BarEntry.add(new BarEntry(3,9));
        BarEntry.add(new BarEntry(4,8));
        BarEntry.add(new BarEntry(5,1));
        BarEntry.add(new BarEntry(6,3));

        BarDataSet dataSet = new BarDataSet(BarEntry,
                "Dish Analytics");

        BarData barData = new BarData(dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setBarShadowColor(Color.BLACK);

        barChartBreakfast.setPinchZoom(false);
        barChartLunch.setPinchZoom(false);
        barChartSnacks.setPinchZoom(false);
        barChartDinner.setPinchZoom(false);

        barChartBreakfast.setHighlightPerTapEnabled(true);
        barChartSnacks.setHighlightPerTapEnabled(true);
        barChartLunch.setHighlightPerTapEnabled(true);
        barChartDinner.setHighlightPerTapEnabled(true);

        barChartBreakfast.setData(barData);
        barChartBreakfast.animateXY(1400,1400);
        barChartLunch.setData(barData);
        barChartLunch.animateXY(1400,1400);
        barChartSnacks.setData(barData);
        barChartSnacks.animateXY(1400,1400);
        barChartDinner.setData(barData);
        barChartDinner.animateXY(1400,1400);


        return view;
    }// end of onCreate


    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(getContext(),MealTimeAnalytics.class);
        if (v == buttondatepicker) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            my_day = dayOfMonth;
                            my_month = monthOfYear + 1;
                            my_year = year;
                            buttondatepicker.setText("" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();



        }

        // Customize date in my format
        CusDate cusDate = new CusDate(my_day, my_month, my_year);
        String st_day = cusDate.getDay();
        String st_month = cusDate.getMonth();
        String st_year = cusDate.getYear();

        Log.d("Button Day", "" + st_day);
        Log.d("Button Month", "" + st_month);
        Log.d("Button Year", "" + st_year);

        if (v == barChartBreakfast)
        {
            intent.putExtra("mealName","Breakfast");
            intent.putExtra("Day",st_day);
            intent.putExtra("Month",st_month);
            intent.putExtra("Year",st_year);
            startActivity(intent);
        }
        if (v == barChartLunch)
        {
            intent.putExtra("mealName","Lunch");
            intent.putExtra("Day",st_day);
            intent.putExtra("Month",st_month);
            intent.putExtra("Year",st_year);
            startActivity(intent);
        }
        if (v == barChartSnacks)
        {
            intent.putExtra("mealName","Snacks");
            intent.putExtra("Day",st_day);
            intent.putExtra("Month",st_month);
            intent.putExtra("Year",st_year);
            startActivity(intent);
        }
        if (v == barChartDinner)
        {
            intent.putExtra("mealName","Dinner");
            intent.putExtra("Day",st_day);
            intent.putExtra("Month",st_month);
            intent.putExtra("Year",st_year);
            startActivity(intent);
        }

    }// end of onClick




}// end of class



