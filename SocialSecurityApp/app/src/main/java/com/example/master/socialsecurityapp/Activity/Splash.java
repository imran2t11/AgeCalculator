package com.example.master.socialsecurityapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.master.socialsecurityapp.R;


public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                loadActivitychkSharedPrf();
               // Intent in=new Intent(Splash.this,HomePage.class);
               // startActivity(in);
                finish();
            }
        },SPLASH_TIME_OUT);







      /*  SharedPreferences prefs = getSharedPreferences("application_settings", 0);
        int id = prefs.getInt("id", 0);
        if(id > 0) {
            startActivity(new Intent(Splash.this, HomePage.class));
        }
        else
            {
                Intent in=new Intent(this,SignUpActivity.class);
                startActivity(in);
            }
*/


    }

    private void loadActivitychkSharedPrf() {

        sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String strPref = sharedPreferences.getString("emailsaved", null);

        if(strPref != null) {
            Intent i=new Intent(this,HomePage.class);

            startActivity(i);

        }
        else
        { Intent in=new Intent(this,SignUpActivity.class);
            startActivity(in);}

    }
}
