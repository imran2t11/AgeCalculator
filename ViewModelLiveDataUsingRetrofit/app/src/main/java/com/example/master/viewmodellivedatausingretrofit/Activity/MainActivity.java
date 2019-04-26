package com.example.master.viewmodellivedatausingretrofit.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.master.viewmodellivedatausingretrofit.AdapterClass.HeroesAdapter;
import com.example.master.viewmodellivedatausingretrofit.Model.Hero;
import com.example.master.viewmodellivedatausingretrofit.R;
import com.example.master.viewmodellivedatausingretrofit.ViewModel.HeroViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HeroesAdapter adapter;
    List<Hero> heroList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        HeroViewModel model = ViewModelProviders.of(this).get(HeroViewModel.class);

        model.getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroList) {
                adapter = new HeroesAdapter(MainActivity.this, heroList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
