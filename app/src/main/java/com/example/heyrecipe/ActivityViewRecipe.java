package com.example.heyrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityViewRecipe extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, desc, steps;

    String data1, data2, data3;
    int myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.titleRecipe);
        desc = findViewById(R.id.descRecipe);
        steps = findViewById(R.id.recipe);

        getData();
        setData();
        setSupportActionBar(findViewById(R.id.my_toolbar));
    }

    private void getData(){
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){

            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            myImage = getIntent().getIntExtra("myImage",1);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        title.setText(data1);
        desc.setText(data2);
        steps.setText(data3);
        mainImageView.setImageResource(myImage);
    }
}