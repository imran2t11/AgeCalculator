package com.example.videojsonrecylerview.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.videojsonrecylerview.Model.DemoImgModel;
import com.example.videojsonrecylerview.R;

import java.util.ArrayList;

public class DemoImgAdapter extends RecyclerView.Adapter<DemoImgAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<DemoImgModel> demoImgModelArrayList;

    public DemoImgAdapter(Context context, ArrayList<DemoImgModel> demoImgModelArrayList) {
        this.context = context;
        this.demoImgModelArrayList = demoImgModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.demoimglayout, null);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DemoImgModel demoImgModel = demoImgModelArrayList.get(i);
        myViewHolder.idtextTV.setText(demoImgModel.getPageId());
        myViewHolder.textTV.setText(demoImgModel.getDesc());
        Glide.with(context).load("https://sundarbantourist.com/sunapp/dashboard/" + demoImgModel.getPhoto()).into(myViewHolder.imgIV);

    }

    @Override
    public int getItemCount() {
        return demoImgModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idtextTV, textTV;
        ImageView imgIV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idtextTV = itemView.findViewById(R.id.idtextTV);
            textTV = itemView.findViewById(R.id.textTV);
            imgIV = itemView.findViewById(R.id.imgIV);

        }
    }
}
