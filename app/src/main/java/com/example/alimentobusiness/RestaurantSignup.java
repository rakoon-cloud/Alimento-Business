package com.example.alimentobusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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

public class RestaurantSignup extends AppCompatActivity {

    TextView restsigninlink;

    EditText restsignemail, restsignpasswrd, restsignconfpasswrd;
    Button restsignbtn;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_signup);

        restsigninlink=findViewById(R.id.tv_restsignlink);
        restsignemail=findViewById(R.id.ed_restsignemail);
        restsignpasswrd=findViewById(R.id.ed_restsignpasswrd);
        restsignconfpasswrd=findViewById(R.id.ed_restsignconfpasswrd);
        restsignbtn=findViewById(R.id.button_restsignup);

        mAuth = FirebaseAuth.getInstance();






        restsignbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String restaurantemail = restsignemail.getText().toString().trim();
                String restaurantpasswrd = restsignpasswrd.getText().toString().trim();



                mAuth.createUserWithEmailAndPassword(restaurantemail, restaurantpasswrd)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(RestaurantSignup.this, "You are in!!!", Toast.LENGTH_SHORT).show();
                                    Intent i =new Intent(RestaurantSignup.this, RestaurantLogin.class);
                                    startActivity(i);

                                }else {

                                    Toast.makeText(RestaurantSignup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });



        restsigninlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RestaurantSignup.this, RestaurantLogin.class);
                startActivity(i);
            }
        });


    }
}