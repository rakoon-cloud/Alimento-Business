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

public class CatererLogin extends AppCompatActivity {

    TextView catloginlink;
    EditText catsignpass,catsignemail;
    Button catlogbtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_login);
        catloginlink=findViewById(R.id.tv_catloglink);
        catlogbtn=findViewById(R.id.button_catlogin);
        catsignpass=findViewById(R.id.ed_catlogpasswrd);
        catsignemail=findViewById(R.id.ed_catlogemail);
        auth=FirebaseAuth.getInstance();




        catlogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String catereremail = catsignemail.getText().toString().trim();
                String catererpassword = catsignpass.getText().toString().trim();

                auth.signInWithEmailAndPassword(catereremail, catererpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i= new Intent(CatererLogin.this, CatererHome.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(CatererLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        catloginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(CatererLogin.this, CatererSignup.class);
                startActivity(i);
            }
        });



    }
}