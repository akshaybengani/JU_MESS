package com.jecrc_university.ju_mess;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MealTimeAnalytics extends AppCompatActivity {

    private String mealTime,curDate;
    private DatabaseReference databaseReference;
    private List<MealFeedback> mealFeedbackList;
    private List<MealAnalytics> mealAnalyticsList;
    private int dishCounter = 0 ;
    private ArrayList<String> key;
    private int totalRating,totalUsers;

    private ProgressDialog progressDialog;

    private BarChart barChartMealTime;
    private ArrayList<BarEntry> barEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_time_analytics);

        // create barChart variable

        mealTime = getIntent().getExtras().getString("mealName");
        String day = getIntent().getExtras().getString("Day");
        String month = getIntent().getExtras().getString("Month");
        String year = getIntent().getExtras().getString("Year");

         curDate = ""+day+""+month+""+year;

        Log.d("Data from Intent",mealTime+" "+curDate);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading ...");
        progressDialog.setMessage
                ("Please wait while we calculate Analysis for you");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        barChartMealTime = (BarChart)findViewById
                (R.id.bar_chartMealTime);
        barEntries = new ArrayList<>();
        mealFeedbackList = new ArrayList<>();
        mealAnalyticsList = new ArrayList<>();
        key = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance()
                .getReference("/Analytics/"+curDate+"/"+mealTime+"/");
            databaseReference.addValueEventListener
                    (new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        Log.d("SomethingHappened","DataSnapshot"+ds);
                        MealFeedback mealFeedback = ds.getValue
                                (MealFeedback.class);
                        key.add(ds.getKey());
                        mealFeedbackList.add(mealFeedback);
                    }
                    for (int i =0;i<key.size();i++)
                    {
                        MealAnalytics mealAnalytics =
                                new MealAnalytics(key.get(i),
                                        mealFeedbackList.get(i).getTotalRating(),
                                        mealFeedbackList.get(i).getTotalUsers());
                        mealAnalyticsList.add(mealAnalytics);
                    }
                    Log.d("Ready to display","Yes we are ready");
                    displayDataOnGraph();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });


    }//end of onCreate

    private void displayDataOnGraph() {
        float averageRating;
        for (int i =0;i<mealAnalyticsList.size();i++)
        {
            Log.d("MealName is ",mealAnalyticsList.get(i).mealName+
                    " TotalRating is "+mealAnalyticsList.get(i).totalRating+
                    " TotalUsers is "+mealAnalyticsList.get(i).totalUsers);
            averageRating = mealAnalyticsList.get(i).
                    totalRating/mealAnalyticsList.get(i).totalUsers;
            barEntries.add(new BarEntry(i+1,averageRating));
            // we already have labels list named as key
        }
        BarDataSet barDataSet = new BarDataSet(barEntries,"Dishes");
        XAxis xval = barChartMealTime.getXAxis();
        xval.setDrawLabels(true);
        xval.setValueFormatter(new AxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d("KeyValueFormatter", "Float value " +
                        value + " Int Value " + (int) value);
                return key.get((int) value);
            }
            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        progressDialog.dismiss();
        xval.setDrawGridLines(false);
        xval.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        BarData barData = new BarData(barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setBarShadowColor(Color.BLACK);

        barChartMealTime.setData(barData);
        //barChartMealTime.setDescription("No of Projects");
        barChartMealTime.animateXY(1400,1400);
    }


}// end of Class
