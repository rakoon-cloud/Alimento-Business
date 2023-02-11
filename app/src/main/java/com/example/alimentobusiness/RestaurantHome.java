package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RestaurantHome extends AppCompatActivity {
    CardView cardaddfood, cardaddseatarrangement;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor (this.getResources().getColor(R.color.black));
        }

        cardaddfood = findViewById(R.id.cardaddmenu);
        cardaddseatarrangement = findViewById(R.id.cardaddseats);

        cardaddfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodpage = new Intent(RestaurantHome.this, ManageMenu.class);
                startActivity(foodpage);
            }
        });
        cardaddseatarrangement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seatpage = new Intent(RestaurantHome.this, AddSeats.class);
                startActivity(seatpage);
            }
        });


    }
}