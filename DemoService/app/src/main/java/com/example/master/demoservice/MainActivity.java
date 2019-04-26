package com.example.master.demoservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startbtn, stopbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbtn = findViewById(R.id.startbtn);
        stopbtn = findViewById(R.id.stopbtn);

    }

    public void stopClick(View view) {
        startService(new Intent(this,MyService.class));
    }

    public void startClick(View view) {
        stopService(new Intent(this,MyService.class));
    }
}
