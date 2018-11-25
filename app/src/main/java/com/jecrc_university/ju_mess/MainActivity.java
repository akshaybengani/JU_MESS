package com.jecrc_university.ju_mess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Analytics:

                    setTitle("Analytics");
                    AnalyticsFragment analyticsFragment = new AnalyticsFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionUpdates = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionUpdates.replace(R.id.content, analyticsFragment,"Analytics Fragment");
                    fragmentTransactionUpdates.commit();

                    return true;
                case R.id.navigation_home:

                    setTitle("Home");
                    HomeFragment homeFragment = new HomeFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionHome.replace(R.id.content,homeFragment,"Home Fragment");
                    fragmentTransactionHome.commit();

                    return true;
                case R.id.navigation_feedback:

                    setTitle("FeedBack");
                    FeedbackFragment feedbackFragment = new FeedbackFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionFeedback = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionFeedback.replace(R.id.content,feedbackFragment,"Feedback Fragment");
                    fragmentTransactionFeedback.commit();


                    return true;
                case R.id.navigation_user:

                    setTitle("User");
                    UserFragment userFragment = new UserFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionUser = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionUser.replace(R.id.content,userFragment,"User Fragment");
                    fragmentTransactionUser.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("Home");
        HomeFragment homeFragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransactionUpdates = getSupportFragmentManager().beginTransaction();
        fragmentTransactionUpdates.replace(R.id.content,homeFragment,"Home Fragment");
        fragmentTransactionUpdates.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser==null)
        {
            Toast.makeText(MainActivity.this,"Session Expired please login again",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,LogInActivity.class);
            startActivity(intent);
        }

    }
}
