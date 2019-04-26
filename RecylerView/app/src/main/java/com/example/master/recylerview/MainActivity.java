package com.example.master.recylerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView listRV;
RVAdapter rvAdapter;
List<Model>models=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listRV=findViewById(R.id.listRV);
        listRV.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter=new RVAdapter(this,models);
        listRV.setAdapter(rvAdapter);
        loadData();
    }



    public void loadData(){
        Model m1=new Model("Bla",R.drawable.ic_launcher_background);
        models.add(m1);
        rvAdapter.notifyDataSetChanged();
    }
}
