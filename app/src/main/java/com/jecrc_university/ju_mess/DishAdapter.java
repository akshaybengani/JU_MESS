package com.jecrc_university.ju_mess;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder>{

    // Context will be used to inflate the layout
    private Context mCtx;
    List<Dish> dishList;

    public DishAdapter(Context mCtx, List<Dish> dishList){
        this.mCtx = mCtx;
        this.dishList = dishList;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(mCtx).inflate(R.layout.dish_list,parent,false);

        DishViewHolder dishViewHolder = new DishViewHolder(view);

        return  dishViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final DishViewHolder holder, int position) {

        final Dish dish = dishList.get(position);

        holder.dish_name.setText(dish.getmName());


        Picasso.get().load(dish.getmImageUrl()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.dishImage, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(dish.getmImageUrl()).into(holder.dishImage);
            }
        });

    }

    @Override
    public int getItemCount(){
        return dishList.size();
    }

    class DishViewHolder extends RecyclerView.ViewHolder{

        ImageView dishImage;
        TextView dish_name;



        public DishViewHolder(final View itemView){
            super(itemView);

            dishImage = itemView.findViewById(R.id.imageviewDishImage);
            dish_name = itemView.findViewById(R.id.textviewDishName);

        }

    }



}
