package com.example.heyrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityDashboard extends AppCompatActivity {
    FloatingActionButton myRecipe;
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.sinigang, R.drawable.tinola, R.drawable.karekare, R.drawable.adobo, R.drawable.caldereta, R.drawable.dinuguan};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        myRecipe = findViewById(R.id.myRecipeFloat);

        s1 = getResources().getStringArray(R.array.favorite_websites);
        s2 = getResources().getStringArray(R.array.descriptions);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(findViewById(R.id.my_toolbar));

        myRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDashboard.this, ActivityMyRecipe.class);
                startActivity(intent);
            }
        });
    }
}
