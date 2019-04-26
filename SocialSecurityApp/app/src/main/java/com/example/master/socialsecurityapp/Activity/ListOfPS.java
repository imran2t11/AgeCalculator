package com.example.master.socialsecurityapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.master.socialsecurityapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListOfPS extends AppCompatActivity {
ListView psLV;
ArrayList<String>numberList;
TextView numberTV;
Button callbtn,backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_ps);
numberTV=(TextView) findViewById(R.id.numberTV);
callbtn=(Button)findViewById(R.id.callbtn);
backbtn=(Button)findViewById(R.id.backbtn);
        numberList=new ArrayList<>();
        psLV=(ListView) findViewById(R.id.psLV);
        numberList.add("Police Hotline Number");
        numberList.add("Pachlaish P.S.");
        numberList.add("Bandor P.S.");
        numberList.add("Halishahar P.S.");
        numberList.add("Katowali P.S.");
        numberList.add("Pahartali P.S.");
        numberList.add("Firoz Shah P.S.");
        numberList.add("EPZ P.S.");
       // numberList.add("RMP Head Quarter");
      //  numberList.add("CMP Head Quarter.");
       // numberList.add("KMP Head Quarter.");
       // numberList.add("Barisal Head Quarter");
       // numberList.add("SYLHET Head Quarter");
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,numberList);
        psLV.setAdapter(adapter);


     psLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




             if(position==0)
             {
                 numberTV.setText("00345678901");


             }
             if(position==1)
             {
                 numberTV.setText("15678912369");

             }
             if(position==2)
             {
                 numberTV.setText("25678912369");

             }

             if(position==3)
             {
                 numberTV.setText("35678912369");

             }

             if(position==4)
             {
                 numberTV.setText("45678912369");

             }

             if(position==5)
             {
                 numberTV.setText("55678912369");

             }

             if(position==6)
             {
                 numberTV.setText("65678912369");

             }

             if(position==7)
             {
                 numberTV.setText("75678912369");

             }
             if(position==8)
             {
                 numberTV.setText("85678912369");

             }




         }
     });

    }

    public void callFunction(View view) {

        if(numberTV!=null)
        {
        String psnum=numberTV.getText().toString();
            Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",psnum, null));
            startActivity(callintent);
        }
        else
            Toast.makeText(this, "Tap a staion for selection,then call", Toast.LENGTH_SHORT).show();
    }

    public void backtohome(View view) {

        Intent back=new Intent(this,HomePage.class);
        startActivity(back);
    }
}
