package com.imran.master.agecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    Button calculateBtn, backBtn;
    DatePicker datePicker1, datePicker2;
    TextView bdtv, ctv;
    int ageYear = 0;
    int agemonth = 0;
    int ageday = 0;
    TextView resultdayTV, resultyearTV, resultmonthTV, resulttxtTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bday, bmonth, byear, currentday, currentmonth, currentyear;
                bday = datePicker1.getDayOfMonth();
                bmonth = datePicker1.getMonth();
                byear = datePicker1.getYear();
                Log.e("Dateis " + bday + bmonth + byear, "onClick: ");

                currentday = datePicker2.getDayOfMonth();
                currentmonth = datePicker2.getMonth();
                currentyear = datePicker2.getYear();

                Log.e("Date2is " + currentday + currentmonth + currentyear, "onClick: ");
                datePicker1.setVisibility(GONE);
                datePicker2.setVisibility(GONE);
                calculateBtn.setVisibility(GONE);
                bdtv.setVisibility(GONE);
                ctv.setVisibility(GONE);
                backBtn.setVisibility(View.VISIBLE);
                resulttxtTV.setVisibility(View.VISIBLE);

                if (byear <= currentyear) {


                    if (currentday >= bday) {
                        ageday = currentday - bday;
                    }
                    if (currentday < bday)

                    {
                        currentday = currentday + 31;
                        currentmonth = currentmonth - 1;
                        ageday = currentday - bday;
                    }
                    if (currentmonth >= bmonth) {
                        agemonth = currentmonth - bmonth;
                    }

                    if (currentmonth < bmonth)

                    {
                        currentmonth = currentmonth + 12;
                        currentyear = currentyear - 1;
                        agemonth = currentmonth - bmonth;
                    }

                    ageYear = currentyear - byear;
                    Log.e("resultday", "d: " + ageday);
                    Log.e("resultmonth", "mm: " + agemonth);
                    Log.e("resultyear", "y: " + ageYear);

                    if (ageday > 1) {
                        resultdayTV.setText(ageday + " days");
                        Log.e("day" + ageday, "onClick: ");
                    } else
                        resultdayTV.setText(ageday + " day");
                    if (agemonth > 1) {
                        resultmonthTV.setText(agemonth + " months");
                    } else
                        resultmonthTV.setText(agemonth + " month");
                    if (ageYear > 1) {
                        resultyearTV.setText(ageYear + " years");
                    } else
                        resultyearTV.setText(ageYear + " year");


                } else
                    Toast.makeText(MainActivity.this, "Birth year cannot be greater then the expected calculation year!Plese try again!", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void initViews() {

        calculateBtn = findViewById(R.id.calculateBtn);
        datePicker1 = findViewById(R.id.datePicker1);
        datePicker2 = findViewById(R.id.datePicker2);

        backBtn = findViewById(R.id.backBtn);
        bdtv = findViewById(R.id.bdtv);
        ctv = findViewById(R.id.ctv);
        resultyearTV = findViewById(R.id.resultyearTV);
        resultmonthTV = findViewById(R.id.resultmonthTV);
        resultdayTV = findViewById(R.id.resultdayTV);
        resulttxtTV = findViewById(R.id.resulttxtTV);
    }

    public void backclick(View view) {
        //backBtn.setVisibility(GONE);
        //datePicker1.setVisibility(View.VISIBLE);
        //datePicker2.setVisibility(View.VISIBLE);
        //calculateBtn.setVisibility(View.VISIBLE);
        //bdtv.setVisibility(View.VISIBLE);
        //ctv.setVisibility(View.VISIBLE);
        //resulttxtTV.setVisibility(GONE);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
