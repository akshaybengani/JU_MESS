package com.jecrc_university.ju_mess;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import javax.security.auth.login.LoginException;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);                  //will hide the title
        getSupportActionBar().hide();                           //hide the title bar
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreen.this, LogInActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME);

    }


}
