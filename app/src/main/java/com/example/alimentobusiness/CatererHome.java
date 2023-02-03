package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CatererHome extends AppCompatActivity {

    Button addcatererprofile;
    CardView catinfo;
    CardView catbooking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_home2);

        addcatererprofile=findViewById(R.id.button_addcatprofile);
        catinfo=findViewById(R.id.card_catinfo);
        catbooking=findViewById(R.id.card_catbook);

        catinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(CatererHome.this, CatererViewProfile.class);
                startActivity(a);
            }
        });
        catbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewbooking=new Intent(CatererHome.this, CatererViewBooking.class);
                startActivity(viewbooking);
            }
        });

        addcatererprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(CatererHome.this, CatererAddProfile.class);
                startActivity(i);
            }
        });





    }
}