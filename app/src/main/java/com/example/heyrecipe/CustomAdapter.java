package com.example.heyrecipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private ArrayList recipe_id, recipe_name, recipe_ingre, recipe_steps;


    CustomAdapter(Context context, ArrayList recipe_id, ArrayList recipe_name, ArrayList recipe_ingre, ArrayList recipe_steps){
        this.context = context;
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
        this.recipe_ingre = recipe_ingre;
        this.recipe_steps = recipe_steps;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {
        holder.recipe_id_txt.setText(String.valueOf(recipe_id.get(position)));
        holder.recipe_name_txt.setText(String.valueOf(recipe_name.get(position)));
        holder.recipe_ingre_txt.setText(String.valueOf(recipe_ingre.get(position)));
        holder.recipe_steps_txt.setText(String.valueOf(recipe_steps.get(position)));
        holder.mainRecipeRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityUpdateRecipe.class);
                intent.putExtra("id", String.valueOf(recipe_id.get(position)));
                intent.putExtra("name", String.valueOf(recipe_name.get(position)));
                intent.putExtra("ingre", String.valueOf(recipe_ingre.get(position)));
                intent.putExtra("steps", String.valueOf(recipe_steps.get(position)));
                        context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipe_id.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
    TextView recipe_id_txt, recipe_name_txt, recipe_ingre_txt, recipe_steps_txt;
    LinearLayout mainRecipeRowLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipe_id_txt = itemView.findViewById(R.id.recipeID);
            recipe_name_txt = itemView.findViewById(R.id.recipeName);
            recipe_ingre_txt = itemView.findViewById(R.id.recipeIngre);
            recipe_steps_txt = itemView.findViewById(R.id.recipeSteps);
            mainRecipeRowLayout = itemView.findViewById(R.id.mainRecipeRowLayout);
        }
    }
}
