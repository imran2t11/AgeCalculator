package com.example.borhan.flixmovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    SharedPreferences sharedPreferences;
    String nameSp,passSp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                loadActivitychkSharedPrf();



                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    private void loadActivitychkSharedPrf() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String strPref = sharedPreferences.getString("nameKey", null);
        if (strPref != null) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);

            startActivity(i);

        } else {
            Intent i = new Intent(SplashActivity.this, SignUpActivity.class);
            startActivity(i);

        }

    }
}

