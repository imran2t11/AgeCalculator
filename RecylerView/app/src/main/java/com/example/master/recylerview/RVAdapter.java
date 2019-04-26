package com.example.master.recylerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    Context context;
    List<Model>myDataModel;
    RVAdapter(Context context, List<Model>myDataModel){
        this.context=context;
        this.myDataModel=myDataModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_recylerview,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textTV.setText(myDataModel.get(i).getName());
        myViewHolder.imageIV.setImageResource(myDataModel.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return myDataModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageIV;
        TextView textTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
    imageIV=itemView.findViewById(R.id.imageIV);
    textTV=itemView.findViewById(R.id.textTV);
itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
