package com.example.borhan.foodorder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    RecyclerView foodRV;
    List<FoodAttributes> foodAttributesList;
    FoodAttributesAdapter foodAttributesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("Home");

        foodAttributesList=new ArrayList<>();
        foodRV=view.findViewById(R.id.foodRV);
        foodRV.setHasFixedSize(true);
        foodRV.setLayoutManager(new LinearLayoutManager(getActivity()));


        foodAttributesList = new ArrayList<>();


        //adding some items to our list
        foodAttributesList.add(
                new FoodAttributes(

                        "RiceBowl",
                        100,
                        R.mipmap.ic_launcher_round,
                        "Bangladeshi"
                ));

        foodAttributesList.add(
                new FoodAttributes(
                        "RiceBowl",
                        100,
                        R.mipmap.ic_launcher_round,
                        "Bangladeshi"));

        foodAttributesList.add(
                new FoodAttributes(
                        "RiceBowl",
                        100,
                        R.drawable.food,
                        "Bangladeshi"));

        //creating recyclerview adapter
        FoodAttributesAdapter adapter = new FoodAttributesAdapter(getActivity(), foodAttributesList);

        //setting adapter to recyclerview
        foodRV.setAdapter(adapter);


    }
}
