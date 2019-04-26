package com.example.master.navigationdrawerpro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfPSFragment extends Fragment {

    ListView psLV;
    ArrayList<String>numberList;
    TextView numberTV;
    Button callbtn,backbtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listofps,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numberTV=view.findViewById(R.id.numberTV);
        callbtn=view.findViewById(R.id.callbtn);
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numberTV!=null)
                {
                    String psnum=numberTV.getText().toString();
                    Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",psnum, null));
                    startActivity(callintent);
                }
                else
                    Toast.makeText(getActivity(), "Tap a staion for selection,then call", Toast.LENGTH_SHORT).show();


            }
        });


        numberList=new ArrayList<>();
        psLV=view.findViewById(R.id.psLV);
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
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1,numberList);
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










}
