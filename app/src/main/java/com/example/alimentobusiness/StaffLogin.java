package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StaffLogin extends AppCompatActivity {
    TextView staffloginlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        staffloginlink=findViewById(R.id.tv_staffloglink);

        staffloginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(StaffLogin.this, StaffSignup.class);
                startActivity(i);
            }
        });
    }
}