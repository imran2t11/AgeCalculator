package com.example.borhan.foodorder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FoodAttributesAdapter extends RecyclerView.Adapter<FoodAttributesAdapter.FoodAttributesViewHolder>{

    private Context context;

    public FoodAttributesAdapter(Context context, List<FoodAttributes> foodAttributesList) {
        this.context = context;
        this.foodAttributesList = foodAttributesList;
    }

    private List<FoodAttributes>foodAttributesList;

    @NonNull
    @Override
    public FoodAttributesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.home_fragment_list_layout,null);
        FoodAttributesViewHolder foodAttributesViewHolder=new FoodAttributesViewHolder(view);

        return foodAttributesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAttributesViewHolder holder, int position) {

        FoodAttributes foodAttributes=foodAttributesList.get(position);
        holder.nameoffoodTV.setText(foodAttributes.getFoodname());
        holder.foodtypeTV.setText(foodAttributes.getFoodtype());
        holder.priceTV.setText(String.valueOf(foodAttributes.getFoodprice()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.foodImageIV.setImageDrawable(context.getDrawable(foodAttributes.getFoodimage()));
        }
        else
            Toast.makeText(context, "Sorry!This feature is not supported under LOLLIPOP.You will need to upgrade your Android Operating System", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return foodAttributesList.size();
    }

    class FoodAttributesViewHolder extends RecyclerView.ViewHolder{

        ImageView foodImageIV;
        TextView nameoffoodTV,priceTV,foodtypeTV;

        public FoodAttributesViewHolder(View itemView) {
            super(itemView);

            foodImageIV=itemView.findViewById(R.id.foodImageIV);
            nameoffoodTV=itemView.findViewById(R.id.nameoffoodTV);
            priceTV=itemView.findViewById(R.id.priceTV);
            foodtypeTV=itemView.findViewById(R.id.foodtypeTV);
        }
    }
}
