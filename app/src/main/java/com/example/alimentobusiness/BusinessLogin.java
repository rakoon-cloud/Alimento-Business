package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BusinessLogin extends AppCompatActivity {
    CardView cardres;
    CardView cardcat;
    CardView cardche;
    CardView cardsta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor (this.getResources().getColor(R.color.black));
        }

        cardres=findViewById(R.id.card_rest);
        cardcat=findViewById(R.id.card_cat);
        cardche=findViewById(R.id.card_che);
        cardsta=findViewById(R.id.card_staff);


        cardres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r=new Intent(BusinessLogin.this, RestaurantLogin.class);
                startActivity(r);
            }
        });

        cardcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c=new Intent(BusinessLogin.this, CatererLogin.class);
                startActivity(c);
            }
        });

        cardche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(BusinessLogin.this, ChefLogin.class);
                startActivity(i);

            }
        });

        cardsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent s=new Intent(BusinessLogin.this, StaffLogin.class);
                startActivity(s);
            }
        });
    }
}