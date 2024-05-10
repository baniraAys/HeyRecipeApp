package com.example.heyrecipe;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityDashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.sinigang, R.drawable.tinola, R.drawable.karekare, R.drawable.adobo, R.drawable.caldereta, R.drawable.dinuguan};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.favorite_websites);
        s2 = getResources().getStringArray(R.array.descriptions);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(findViewById(R.id.my_toolbar));
    }
}
