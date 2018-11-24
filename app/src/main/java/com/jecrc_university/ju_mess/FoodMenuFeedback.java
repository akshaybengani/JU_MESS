package com.jecrc_university.ju_mess;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodMenuFeedback extends AppCompatActivity {

    // RecyclerView recyclerView;
    // DishAdapter adapter;
    List<Dish> dishList;
    List<DishRating> dishRatingList;
    ImageView imageViewDishImage;
    TextView textViewDishName;
    String name, imageURl;
    SmileRating smileRating;
    EditText editTextAdditionalFeedback;
    int rating = 0;
    String feedback;
    Button buttonNext, buttonPrevious;
    String CurDate, MealTime;
    int size ;
    int counter = 0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu_feedback);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading ...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        MealTime = getIntent().getExtras().getString("Meal Time");
        String day = getIntent().getExtras().getString("Day");
        String month = getIntent().getExtras().getString("Month");
        String year = getIntent().getExtras().getString("Year");

        CurDate = "" + day + "" + month + "" + year;
        android.util.Log.d("Date Target", CurDate);

        imageViewDishImage = (ImageView) findViewById(R.id.imageView1);
        textViewDishName = (TextView) findViewById(R.id.textview1);
        smileRating = (SmileRating) findViewById(R.id.smileTaste);
        editTextAdditionalFeedback = (EditText) findViewById(R.id.editTextAddtionalFeedback);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonPrevious = (Button) findViewById(R.id.buttonPrevious);

        buttonPrevious.setEnabled(false);

        setTitle(MealTime);
        dishList = new ArrayList<>();
        dishRatingList = new ArrayList<>();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Menu/" + CurDate + "/" + MealTime + "/");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (DataSnapshot dishSnapshot : dataSnapshot.getChildren()) {
                        Dish dish = dishSnapshot.getValue(Dish.class);
                        dishList.add(dish);
                    }
                    Log.d("I call single time", "I am  here");
                    Dish d = dishList.get(counter);
                    size = dishList.size();


                    Log.d("MySizeOfArray","is "+size);
                    displayItemOnView(d);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }// onCreate Ends here

    private void displayItemOnView(Dish d) {

        // Load the Layout or dismiss the progress bar
        progressDialog.dismiss();

        // This sets the data for the 1st Time
        name = d.getmName();
        imageURl = d.getmImageUrl();
        textViewDishName.setText(name);
        Picasso.get().load(imageURl).into(imageViewDishImage);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                // Get data from Rating and Feedback
                feedback = editTextAdditionalFeedback.getText().toString();
                rating = smileRating.getRating();

//                smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
//                    @Override
//                    public void onRatingSelected(int level, boolean reselected) {
//                        rating = level;
//                        Log.d("myRating",""+rating);
//                    }
//                });// end of onRatingSelected

                Log.d("myRating","is "+rating);

                if (rating == 0) {
                    // Check for Empty Rating
//                    new AlertDialog.Builder(getApplicationContext())
//                            .setMessage("Please give the rating")
//                            .show();

                } else {
                    // Executes when rating exists
                    if ((size-counter) == 1)
                    {
                        // Set the button to submit text
                        buttonNext.setText("Submit");
                    }
                    if ((size-counter) == 0)
                    {
                        // Send data to server and exit the activity
                        DatabaseReference databaseReferenceSendRating = FirebaseDatabase.getInstance().getReference("Feedback/"+CurDate+"/"+MealTime+"/");
                        String uploadID;

                        for (DishRating dishRating : dishRatingList)
                         {
                             uploadID = databaseReferenceSendRating.push().getKey();
                             databaseReferenceSendRating.child(uploadID).setValue(dishRating);
                         }
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    if (counter <= size)
                    {
                        // continue the basic operations

                        // Add data to list
                        dishRatingList.add(counter, new DishRating(name, imageURl, feedback, rating));

                        // Clear layout
                        editTextAdditionalFeedback.clearComposingText();
                        smileRating.setSelectedSmile(BaseRating.NONE);

                        // Increase value of counter
                        counter++;

                        try
                        {
                            // Take data from list and set to the layout
                            name = dishList.get(counter).getmName();
                            imageURl = dishList.get(counter).getmImageUrl();
                            textViewDishName.setText(name);
                            Picasso.get().load(imageURl).into(imageViewDishImage);

                        }
                        catch (Exception e)
                        {
                            Log.d("Exception",e.getMessage());
                        }


                        if (counter > 0)
                        {
                            buttonPrevious.setEnabled(true);
                        }
                    }

                } // end of else
            }// end of onclick
        }); // end of View.onClickListener

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((size-counter) == 1)
                {
                    buttonNext.setText("Next");
                }
                if (counter == 1)
                {
                    buttonPrevious.setEnabled(false);
                }
                // This will remove the value from the rating
                dishRatingList.remove(counter);
                // This will decrease the counter
                counter --;
                // This will display old layout on screen
                name = dishList.get(counter).getmName();
                imageURl = dishList.get(counter).getmImageUrl();
                textViewDishName.setText(name);
                Picasso.get().load(imageURl).into(imageViewDishImage);


            }// end of onClick
        });// end of View.onClickListener



    }// end of Display Function


}
