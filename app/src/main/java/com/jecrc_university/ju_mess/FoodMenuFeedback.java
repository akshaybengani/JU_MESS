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
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    List<Dish> dishList;
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
    ScrollView scrollViewFeedback;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu_feedback);

        scrollViewFeedback = (ScrollView)findViewById
                (R.id.scrollViewFeedback);
        scrollViewFeedback.setVisibility(View.GONE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading ...");
        progressDialog.setMessage("Your Feedback is Valuable to us");
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
        editTextAdditionalFeedback = (EditText) findViewById(
                R.id.editTextAddtionalFeedback);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonPrevious = (Button) findViewById(R.id.buttonPrevious);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        buttonPrevious.setEnabled(false);

        setTitle(MealTime);
        dishList = new ArrayList<>();
       // dishRatingList = new ArrayList<>();

        final DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference
                        ("Menu/" + CurDate + "/" + MealTime + "/");


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
        scrollViewFeedback.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
        name = d.getmName();
        imageURl = d.getmImageUrl();
        textViewDishName.setText(name);
        Picasso.get().load(imageURl).into(imageViewDishImage);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                feedback = editTextAdditionalFeedback.getText().toString();
                rating = smileRating.getRating();
                Log.d("myRating","is "+rating);
                if (rating == 0) {
                    // check for empty rating
                } else {
                    // Executes when rating exists
                    if ((size-counter) == 1) {
                        // Set the button to submit text
                        buttonNext.setText("Submit");
                    }
                    if ((size-counter) == 0) {
                        Intent intent = new Intent(getApplicationContext(),
                                        MainActivity.class);
                        startActivity(intent);
                    }
                    if (counter <= size) {
                        NewDishRating newDishRating = new NewDishRating
                                (rating,feedback);
                        DatabaseReference mDatabaseReference =
                                FirebaseDatabase.getInstance().getReference
                                ("Feedback/"+CurDate+"/"+MealTime+"/");
                        mDatabaseReference.child(name).child(firebaseUser.getUid())
                                .setValue(newDishRating);

                        editTextAdditionalFeedback.clearComposingText();
                        smileRating.setSelectedSmile(BaseRating.NONE);
                        counter++;
                        try {
                            name = dishList.get(counter).getmName();
                            imageURl = dishList.get(counter).getmImageUrl();
                            textViewDishName.setText(name);
                            Picasso.get().load(imageURl).into(imageViewDishImage);
                        }
                        catch (Exception e) {
                            Log.d("Exception",e.getMessage());
                        }
                        if (counter > 0)
                            buttonPrevious.setEnabled(true);
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
                counter --;
                name = dishList.get(counter).getmName();
                imageURl = dishList.get(counter).getmImageUrl();
                textViewDishName.setText(name);
                Picasso.get().load(imageURl).into(imageViewDishImage);
            }// end of onClick
        });// end of View.onClickListener

    }// end of Display Function
}
