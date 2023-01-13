package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantLogin extends AppCompatActivity {

   TextView restloginlink;
    Button restloginbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);


        restloginlink=findViewById(R.id.tv_restloglink);
        restloginbtn = findViewById(R.id.button_restlog);

        restloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RestaurantLogin.this, RestaurantHome.class);
                startActivity(i);
            }
        });

        restloginlink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i= new Intent(RestaurantLogin.this, RestaurantSignup.class);
        startActivity(i);}
        });
    }
}



