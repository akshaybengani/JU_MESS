package com.jecrc_university.ju_mess;

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
public class HomeFragment extends Fragment implements  View.OnClickListener{

    ImageView buttonBreakfast,buttonLunch,buttonSnacks,buttonDinner;
    int my_day,my_month,my_year;
    Button buttondatepicker;
    private int mYear, mMonth, mDay;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        // TODO onCreate Starts here

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

//        java.util.Date c = Calendar.getInstance().getTime();
//        //System.out.println("Current time => " + c);
//        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
//        String formattedDate = df.format(c);
//        Log.d("Target2",formattedDate);

        my_day = mDay;
        my_month = mMonth+1;
        my_year = mYear;

        buttondatepicker = (Button)view.findViewById(R.id.buttonDatePicker);
        buttondatepicker.setText(""+mDay + "-" + (mMonth + 1) + "-" + mYear);


        buttonBreakfast = (ImageView)view.findViewById(R.id.Buttonbreakfast);
        buttonLunch = (ImageView)view.findViewById(R.id.ButtonLunch);
        buttonSnacks = (ImageView)view.findViewById(R.id.ButtonSnacks);
        buttonDinner = (ImageView)view.findViewById(R.id.ButtonDinner);

        buttonBreakfast.setOnClickListener(this);
        buttonLunch.setOnClickListener(this);
        buttonSnacks.setOnClickListener(this);
        buttonDinner.setOnClickListener(this);

        buttondatepicker.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(getContext(),FoodMenu.class);
        if (v == buttondatepicker)
        {

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            my_day = dayOfMonth;
                            my_month = monthOfYear+1;
                            my_year = year;
                            buttondatepicker.setText(""+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        // Customize date in my format
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
