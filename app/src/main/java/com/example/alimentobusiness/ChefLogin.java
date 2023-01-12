package com.example.alimentobusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.FirebaseFirestore;

public class ChefLogin extends AppCompatActivity {

    TextView loglink;
    Button chefloginbtn;
    EditText logemail, logpasswrd;

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_login);

        loglink=findViewById(R.id.tv_chefloglink);
        chefloginbtn=findViewById(R.id.button_cheflog);
        logemail=findViewById(R.id.ed_cheflogem);
        logpasswrd=findViewById(R.id.ed_cheflogpass);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        chefloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loemail=logemail.getText().toString().trim();
                String lopasswrd=logpasswrd.getText().toString().trim();

                auth.signInWithEmailAndPassword(loemail, lopasswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i= new Intent(ChefLogin.this, MainActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(ChefLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        loglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ChefLogin.this, ChefSignup.class);
                startActivity(i);
            }
        });
    }
}