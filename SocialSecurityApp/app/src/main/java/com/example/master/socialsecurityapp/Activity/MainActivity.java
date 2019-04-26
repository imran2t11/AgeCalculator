package com.example.master.socialsecurityapp.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.master.socialsecurityapp.AddContact;
import com.example.master.socialsecurityapp.R;

public class MainActivity extends AppCompatActivity {
    EditText emailET, passwordET;
    Button loginbtn, signupbutton;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        signupbutton = (Button) findViewById(R.id.signupbutton);
        sharedPreferences = getSharedPreferences("loginInfo", MODE_PRIVATE);


    }

    public void login(View view) {
        String email = emailET.getText().toString();
        String pass = passwordET.getText().toString();
        String emaildatasaved = sharedPreferences.getString("emailsaved", "Data not found");
        String passdatasaved = sharedPreferences.getString("passsaved", "Data not found");
        String namesaved = sharedPreferences.getString("name", "Data Not found");
        String emergencyNo = sharedPreferences.getString("emergencyNo", "Data Not found");
        Log.d("email of shared Pref", "login: " + emaildatasaved);
        Log.d("email of shared Pref", "login: " + email);
        Log.d("email of shared Pref", "login: " + passdatasaved);
        Log.d("email of shared Pref", "login: " + pass);
        Log.d("name", "login: " + namesaved);
        Log.d("num", "login: " + emergencyNo);




        if((email.matches(emaildatasaved))&&(pass.matches(passdatasaved))) {
            Intent intent = new Intent(MainActivity.this,AddContact.class);
            startActivity(intent);
        }


    }


    public void signUp(View view) {
        Intent in=new Intent(this,SignUpActivity.class);
        startActivity(in);

    }
}
