package com.example.alimentobusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RestaurantLogin extends AppCompatActivity {

   TextView restloginlink;
    Button restloginbtn;
    EditText restlogemail, restlogpasswrd;

    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_login);


        restloginlink=findViewById(R.id.tv_restloglink);
        restlogemail=findViewById(R.id.ed_restlogemail);
        restlogpasswrd=findViewById(R.id.ed_restlogpasswrd);
        restloginbtn = findViewById(R.id.button_restlog);

        mAuth = FirebaseAuth.getInstance();

        restloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String restloginemail = restlogemail.getText().toString().trim();
                String restloginpasswrd = restlogpasswrd.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(restloginemail, restloginpasswrd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user =mAuth.getCurrentUser();
                                    Intent i =new Intent(RestaurantLogin.this, RestaurantHome.class);
                                    startActivity(i);

                                }else {
                                    Toast.makeText(RestaurantLogin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
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



