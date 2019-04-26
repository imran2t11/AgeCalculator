package com.example.borhan.flixmovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
EditText userET,passET;
Button signinBtn;
TextView text2TV,signupLinkTV;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
    String passSp,nameSp;
    String usernametext,userpasstext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initialize();
        sharedPreferences=getSharedPreferences("UserInfo",MODE_PRIVATE);
        nameSp = sharedPreferences.getString("nameKey", null);
        passSp = sharedPreferences.getString("passKey", null);
        Log.d("shared PREF DATA", "USER ID: "+nameSp);
        Log.d("shared PREF PASSWORD", "Password: "+passSp);

    }

    private void initialize() {

        userET=findViewById(R.id.userET);
        passET=findViewById(R.id.passET);
        signinBtn=findViewById(R.id.signinBtn);
        text2TV=findViewById(R.id.text2TV);
        signupLinkTV=findViewById(R.id.signupLinkTV);

    }

    public void signin(View view) {

                usernametext=userET.getText().toString();

                userpasstext=passET.getText().toString();

                if(usernametext.matches(nameSp) && passSp.matches(userpasstext) )

                {
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(this, "Something went wrong!Check your input/Sign Up", Toast.LENGTH_SHORT).show();
                }




    }



    public void signUp(View view) {
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}
