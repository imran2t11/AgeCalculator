package com.example.borhan.flixmovie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
EditText usernameET,passwordET;
Button signupBtn;
TextView text1,signinLinkTV;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
String name,pass;
    public static final String Name = "nameKey";
    public static final String Pass = "passKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeallfields();

        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    private void initializeallfields() {
        usernameET=findViewById(R.id.usernameET);
        passwordET=findViewById(R.id.passwordET);
        signupBtn=findViewById(R.id.signupBtn);
        text1=findViewById(R.id.text1);
        signinLinkTV=findViewById(R.id.signinLinkTV);
    }

    public void signup(View view) {

        name=usernameET.getText().toString();
        pass=passwordET.getText().toString();

       if(name.length()<=0|| pass.length()<=0)
       {
           Toast.makeText(this, "Check your input", Toast.LENGTH_SHORT).show();

       }

       else {
           editor.putString(Name, name);
           editor.putString(Pass, pass);
           editor.commit();
           Toast.makeText(this, "Registration Sucessfull", Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(this,SignInActivity.class);
           startActivity(intent);


       }






    }

    public void signIn(View view) {

        Intent intent=new Intent(this,SignInActivity.class);
        startActivity(intent);

    }
}
