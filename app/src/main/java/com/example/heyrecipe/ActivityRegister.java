package com.example.heyrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    EditText inpUser, inpPw, inpRePw;
    Button btnRegister, btnGoLogin;
    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        inpUser = findViewById(R.id.regUsername);
        inpPw = findViewById(R.id.regPassword);
        inpRePw = findViewById(R.id.regRepassword);

        btnGoLogin = findViewById(R.id.signin);
        btnRegister = findViewById(R.id.signup);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr , pwd, repwd;
                usr = inpUser.getText().toString();
                pwd = inpPw.getText().toString();
                repwd = inpRePw.getText().toString();
                if(usr.equals("") || pwd.equals("") || repwd.equals("")){
                    Toast.makeText(ActivityRegister.this, "Please fill up all fields", Toast.LENGTH_LONG).show();
                }
                else{
                    if(pwd.equals(repwd)){ /*IF MATCHED*/
                        //registration
                        if(dbHelper.checkUser(usr)){
                            Toast.makeText(ActivityRegister.this, "User Already Registered!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            boolean registerSuccess = dbHelper.insertData(usr,pwd);
                            if(registerSuccess)
                                Toast.makeText(ActivityRegister.this, "Successfully Registered!", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(ActivityRegister.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(ActivityRegister.this, "Passwords doesn't match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
