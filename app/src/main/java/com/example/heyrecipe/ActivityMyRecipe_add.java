package com.example.heyrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityMyRecipe_add extends AppCompatActivity {
    FloatingActionButton backFloat;
    EditText name, ingre, steps;
    Button add;
    DBHelper2 dbHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_add);
        dbHelper = new DBHelper2(this);
        Intent intent = new Intent(ActivityMyRecipe_add.this, ActivityMyRecipe.class);

        backFloat = findViewById(R.id.backFloat);
        name = findViewById(R.id.recipeNameINP);
        ingre = findViewById(R.id.ingredientsINP);
        steps = findViewById(R.id.recipeINP);

        add = findViewById(R.id.addRecipe);
        setSupportActionBar(findViewById(R.id.my_toolbar));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean success = dbHelper.addRecipe(name.getText().toString().trim(), ingre.getText().toString().trim(), steps.getText().toString().trim());

                if(success){
                    Toast.makeText(ActivityMyRecipe_add.this, "Recipe Added!", Toast.LENGTH_SHORT).show();
                    Log.i("CREATION", "working" );
                    startActivity(intent);
                    finish();
                }else{
                    Log.i("CREATION", "FALSE" );
                    Toast.makeText(ActivityMyRecipe_add.this, "Failed!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
        });
        backFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMyRecipe_add.this, ActivityMyRecipe.class);
                startActivity(intent);
                finish();
            }
        });
    }
}