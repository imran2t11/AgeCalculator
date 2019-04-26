package com.example.master.movieflixlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.master.movieflixlite.R;

public class DataInputActivity extends AppCompatActivity {
EditText movienameET,directornameET;
Button addbtn;
String moviename,directorname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

    movienameET=findViewById(R.id.movienameET);
    directornameET=findViewById(R.id.directornameET);
    addbtn=findViewById(R.id.addbtn);

    }

    public void saveData(View view) {

        moviename=movienameET.getText().toString();
        directorname=directornameET.getText().toString();



    }
}
