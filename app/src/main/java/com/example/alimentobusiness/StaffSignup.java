package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StaffSignup extends AppCompatActivity {

    TextView staffsiginlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_signup);

        staffsiginlink=findViewById(R.id.tv_staffsignlink);

        staffsiginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(StaffSignup.this, StaffLogin.class);
                startActivity(i);
            }
        });
    }
}