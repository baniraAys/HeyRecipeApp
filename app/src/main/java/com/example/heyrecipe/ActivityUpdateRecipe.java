package com.example.heyrecipe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityUpdateRecipe extends AppCompatActivity {
    FloatingActionButton backFloat;
    EditText nameInp, ingreInp, recipeInp;
    Button updateBtn, deleteBtn;

    String id, name, ingre, steps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);
        Intent intent = new Intent(ActivityUpdateRecipe.this, ActivityMyRecipe.class);
        setSupportActionBar(findViewById(R.id.my_toolbar));
        backFloat = findViewById(R.id.backFloat);
        nameInp = findViewById(R.id.recipeNameINP2);
        ingreInp = findViewById(R.id.ingredientsINP2);
        recipeInp = findViewById(R.id.recipeINP2);

        getAndSetData();
        updateBtn = findViewById(R.id.updateRecipe);
        deleteBtn = findViewById(R.id.deleteRecipe);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper2 db = new DBHelper2(ActivityUpdateRecipe.this);
                nameInp = findViewById(R.id.recipeNameINP2);
                ingreInp = findViewById(R.id.ingredientsINP2);
                recipeInp = findViewById(R.id.recipeINP2);
                Boolean result = db.updateData(id, nameInp.getText().toString(), ingreInp.getText().toString(), recipeInp.getText().toString());

                if(result){
                    Toast.makeText(ActivityUpdateRecipe.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ActivityUpdateRecipe.this, "Failed to update!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
        backFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityUpdateRecipe.this, ActivityMyRecipe.class);
                startActivity(intent);
                finish();
            }
        });


    }
    void getAndSetData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("ingre") && getIntent().hasExtra("steps")){

            //Getting data from intent
            id = getIntent().getStringExtra("id");
            Log.i("WORKING","here:" + id);
            name = getIntent().getStringExtra("name");
            ingre = getIntent().getStringExtra("ingre");
            steps = getIntent().getStringExtra("steps");

            //Setting intent data
            nameInp.setText(name);
            ingreInp.setText(ingre);
            recipeInp.setText(steps);
        }else{
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name +" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper2 mydb = new DBHelper2(ActivityUpdateRecipe.this);
                Boolean result = mydb.deleteOneRow(id);

                if(result) {
                    Toast.makeText(ActivityUpdateRecipe.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(ActivityUpdateRecipe.this, "Failed to Delete!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


}