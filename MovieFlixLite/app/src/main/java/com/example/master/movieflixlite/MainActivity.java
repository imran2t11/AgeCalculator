package com.example.master.movieflixlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
RecyclerView listRV;
ImageView addData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listRV=findViewById(R.id.listRV);
        addData=findViewById(R.id.addData);

    }

    public void inputData(View view) {

        Intent in=new Intent(MainActivity.this,DataInputActivity.class);
        startActivity(in);
    }
}
