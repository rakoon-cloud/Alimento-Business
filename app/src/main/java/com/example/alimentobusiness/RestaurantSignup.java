package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RestaurantSignup extends AppCompatActivity {

    TextView restsigninlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_signup);

        restsigninlink=findViewById(R.id.tv_restsignlink);

        restsigninlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RestaurantSignup.this, RestaurantLogin.class);
                startActivity(i);
            }
        });


    }
}