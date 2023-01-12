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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChefSignup extends AppCompatActivity {
    TextView chefsign;
    EditText names,emai,phoneno,passwrd,conpasswrd;
    Button sign;

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_signup);

        chefsign=findViewById(R.id.tv_chefsignlink);
        names=findViewById(R.id.ed_chefsignname);
        emai=findViewById(R.id.ed_chefsignemail);
        phoneno=findViewById(R.id.ed_chefphone);
        passwrd=findViewById(R.id.ed_chefsignpass);
        conpasswrd=findViewById(R.id.ed_signconfpass);
        sign=findViewById(R.id.button_chefsignup);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chefname = names.getText().toString().trim();
                String chefemail = emai.getText().toString().trim();
                String chefphno = phoneno.getText().toString().trim();
                String chefpasswrd = passwrd.getText().toString().trim();
                String chefconpasswrd = conpasswrd.getText().toString().trim();

                if (chefname.isEmpty()){
                    names.setError("This field is required");
                    return;
                }
                if (chefemail.isEmpty()){
                    emai.setError("This field is required");
                    return;
                }
                if (chefphno.isEmpty()){
                    phoneno.setError("This field is required");
                    return;
                }
                if (!chefpasswrd.equals(chefconpasswrd)){
                    conpasswrd.setError("Password does not match");
                    return;
                }

                auth.createUserWithEmailAndPassword(chefemail, chefpasswrd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(ChefSignup.this, "You are in!!!", Toast.LENGTH_SHORT).show();
                                    ChefData obj = new ChefData(chefname, chefphno);
                                    firestore.collection("CHEFS").document(chefemail).set(obj);
                                    Intent i =new Intent(ChefSignup.this, ChefLogin.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(ChefSignup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ChefSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                ChefData obj = new ChefData(chefname, chefphno);
                firestore.collection("CHEFS").document(chefemail).set(obj);
            }
        });

        chefsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ChefSignup.this, ChefLogin.class);
                startActivity(i);
            }
        });
    }
}