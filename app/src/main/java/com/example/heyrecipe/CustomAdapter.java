package com.example.heyrecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private ArrayList recipe_id, recipe_name, recipe_ingre;

    CustomAdapter(Context context, ArrayList recipe_id, ArrayList recipe_name, ArrayList recipe_ingre){
        this.context = context;
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
        this.recipe_ingre = recipe_ingre;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.recipe_id_txt.setText(String.valueOf(recipe_id.get(position)));
        holder.recipe_name_txt.setText(String.valueOf(recipe_name.get(position)));
        holder.recipe_ingre_txt.setText(String.valueOf(recipe_ingre.get(position)));
    }

    @Override
    public int getItemCount() {
        return recipe_id.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
    TextView recipe_id_txt, recipe_name_txt, recipe_ingre_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipe_id_txt = itemView.findViewById(R.id.recipeID);
            recipe_name_txt = itemView.findViewById(R.id.recipeName);
            recipe_ingre_txt = itemView.findViewById(R.id.recipeIngre);
        }
    }
}
