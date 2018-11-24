package com.jecrc_university.ju_mess;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu extends AppCompatActivity {

    // TextView textViewDate;
    RecyclerView recyclerView;
    DishAdapter adapter;
    List<Dish> dishList;
    TextView textViewMenuData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        String MealTime = getIntent().getExtras().getString("Meal Time");
        String day = getIntent().getExtras().getString("Day");
        String month = getIntent().getExtras().getString("Month");
        String year = getIntent().getExtras().getString("Year");

        String CurDate = ""+day+""+month+""+year;
        android.util.Log.d("Date Target",CurDate);

        setTitle(MealTime);
        //textViewDate = (TextView)findViewById(R.id.textViewDate);
        //textViewDate.setText(""+day+"/"+month+"/"+year);
        textViewMenuData = (TextView)findViewById(R.id.textviewMenuData);
        textViewMenuData.setText(day+"/"+month+"/"+year+" -> "+MealTime);

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        dishList = new ArrayList<>();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Menu/"+CurDate+"/"+MealTime+"/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    for (DataSnapshot dishSnapshot : dataSnapshot.getChildren()){
                        Dish dish = dishSnapshot.getValue(Dish.class);
                        dishList.add(dish);
                    }
                    adapter = new DishAdapter(FoodMenu.this,dishList);
                    recyclerView.setAdapter(adapter);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });



    }





}
