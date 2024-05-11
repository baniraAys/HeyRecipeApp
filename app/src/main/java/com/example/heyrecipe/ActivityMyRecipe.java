package com.example.heyrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivityMyRecipe extends AppCompatActivity {
FloatingActionButton backFloat;
    RecyclerView recyclerView;
    FloatingActionButton add_recipe;
    DBHelper2 dbHelper;
    ArrayList<String> recipe_id, recipe_name, recipe_ingredients, recipe_steps;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe);
        setSupportActionBar(findViewById(R.id.my_toolbar));

        recyclerView = findViewById(R.id.recyclerViewMyRecipe);
        add_recipe = findViewById(R.id.addRecipe);
        add_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMyRecipe.this, ActivityMyRecipe_add.class);
                startActivity(intent);
            }
        });


        dbHelper = new DBHelper2(ActivityMyRecipe.this);
        recipe_id = new ArrayList<>();
        recipe_name = new ArrayList<>();
        recipe_ingredients = new ArrayList<>();
        recipe_steps = new ArrayList<>();
        storeArray();

        customAdapter = new CustomAdapter(ActivityMyRecipe.this, recipe_id, recipe_name, recipe_ingredients, recipe_steps);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((ActivityMyRecipe.this)));

    }
    void storeArray(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                recipe_id.add(cursor.getString(0));
                recipe_name.add(cursor.getString(1));
                recipe_ingredients.add(cursor.getString(2));
                recipe_steps.add(cursor.getString(3));
            }
        }
    }
}