package com.jecrc_university.ju_mess;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment implements  View.OnClickListener{

    ImageView buttonBreakfast,buttonLunch,buttonSnacks,buttonDinner;
    private int mYear, mMonth, mDay;

    Button buttondatepickerFeedback;
    int my_day,my_month,my_year;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view =  inflater.inflate(R.layout.fragment_feedback, container,
                false);
        // TODO OnCreate Starts here
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        my_day = mDay;
        my_month = mMonth+1;
        my_year = mYear;

        buttonBreakfast = (ImageView)view.findViewById(R.id.ButtonbreakfastFeedback);
        buttonLunch = (ImageView)view.findViewById(R.id.ButtonLunchFeedback);
        buttonSnacks = (ImageView)view.findViewById(R.id.ButtonSnacksFeedback);
        buttonDinner = (ImageView)view.findViewById(R.id.ButtonDinnerFeedback);
       // buttondatepickerFeedback = (Button)view.findViewById(R.id.buttonDatePickerFeedBack);
        //buttondatepickerFeedback.setText(""+mDay + "-" + (mMonth + 1) + "-" + mYear);

        buttonBreakfast.setOnClickListener(this);
        buttonLunch.setOnClickListener(this);
        buttonSnacks.setOnClickListener(this);
        buttonDinner.setOnClickListener(this);
        //buttondatepickerFeedback.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(getContext(),FoodMenuFeedback.class);
        /*if (v == buttondatepickerFeedback)
        {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            my_day = dayOfMonth;
                            my_month = monthOfYear+1;
                            my_year = year;
                            buttondatepickerFeedback.setText(""+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }*/
        CusDate cusDate = new CusDate(my_day,my_month,my_year);
        String st_day = cusDate.getDay();
        String st_month = cusDate.getMonth();
        String st_year = cusDate.getYear();
        Log.d("Day",""+st_day);
        Log.d("Month",""+st_month);
        Log.d("Year",""+st_year);

        if (v == buttonBreakfast)
        {
            intent1.putExtra("Meal Time","Breakfast");
            intent1.putExtra("Day",st_day);
            intent1.putExtra("Month",st_month);
            intent1.putExtra("Year",st_year);
            startActivity(intent1);
        }
        if (v == buttonLunch)
        {
            intent1.putExtra("Meal Time","Lunch");
            intent1.putExtra("Day",st_day);
            intent1.putExtra("Month",st_month);
            intent1.putExtra("Year",st_year);
            startActivity(intent1);
        }
        if (v == buttonSnacks)
        {
            intent1.putExtra("Meal Time","Snacks");
            intent1.putExtra("Day",st_day);
            intent1.putExtra("Month",st_month);
            intent1.putExtra("Year",st_year);
            startActivity(intent1);
        }
        if (v == buttonDinner)
        {
            intent1.putExtra("Meal Time","Dinner");
            intent1.putExtra("Day",st_day);
            intent1.putExtra("Month",st_month);
            intent1.putExtra("Year",st_year);
            startActivity(intent1);
        }
    }
// OnClick Method Closes here




}
