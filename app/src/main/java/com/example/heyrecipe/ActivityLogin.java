package com.example.heyrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityLogin extends AppCompatActivity {
    FloatingActionButton backFloat;
    DBHelper dbHelper;
    Button login;
    EditText userInp, passInp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);

        userInp = findViewById(R.id.logUsername);
        passInp = findViewById(R.id.logPassword);
        login = findViewById(R.id.login);
        backFloat = findViewById(R.id.backFloat);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedId = dbHelper.checkUser(userInp.getText().toString(),passInp.getText().toString());
                if(isLoggedId){
                    Intent intent = new Intent(ActivityLogin.this, ActivityDashboard.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ActivityLogin.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        backFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityHomePage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
