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
import com.google.firebase.auth.FirebaseUser;

public class CatererSignup extends AppCompatActivity {
    TextView catsigninlink;
    EditText catsignname,catsignpass,catsignphno,catsignemail,catconfpass;
    Button catsignbtn;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_signup);

        catsigninlink=findViewById(R.id.tv_catsignlink);
        catsignemail=findViewById(R.id.ed_catsignemail);
        catsignname=findViewById(R.id.ed_catsignname);
        catsignphno=findViewById(R.id.ed_catsignphno);
        catsignpass=findViewById(R.id.ed_catsignpasswrd);
        catconfpass=findViewById(R.id.ed_catsignconfpasswrd);
        catsignbtn=findViewById(R.id.button_catsignin);
        auth=FirebaseAuth.getInstance();


        catsigninlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(CatererSignup.this, CatererLogin.class);
                startActivity(i);
            }
        });

        catsignbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String catereremail = catsignemail.getText().toString().trim();
                String catererpassword = catsignpass.getText().toString().trim();
                String catererconfpassword = catconfpass.getText().toString().trim();

                if (catereremail.isEmpty()){
                    catsignemail.setError("This field is required");
                    return;
                }

                if (!catererpassword.equals(catererconfpassword)){
                    catconfpass.setError("Password does not match");
                    return;
                }

                auth.createUserWithEmailAndPassword(catereremail, catererpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(CatererSignup.this, "You are in!!!", Toast.LENGTH_SHORT).show();


                                    Intent i =new Intent(CatererSignup.this, CatererLogin.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(CatererSignup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CatererSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });


    }
}
