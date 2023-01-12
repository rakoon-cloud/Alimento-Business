package com.example.alimentobusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CatererViewProfile extends AppCompatActivity {
    TextView profileagencyname,profileownername,profileaddress,profileemail,profilephno,profiledescription,profilecapacity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_view_profile);

        profileagencyname = findViewById(R.id.tv_catdisplay1);
        profileownername = findViewById(R.id.tv_catdisplay2);
        profileaddress = findViewById(R.id.tv_catdisplay3);
        profileemail = findViewById(R.id.tv_catdisplay4);
        profilephno = findViewById(R.id.tv_catdisplay5);
        profiledescription = findViewById(R.id.tv_catdisplay6);
        profilecapacity = findViewById(R.id.tv_catdisplay7);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentid = user.getUid();
        DocumentReference reference;
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        reference = firestore.collection("caterers").document(currentid);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){

                            String agencynameresult = task.getResult().getString("agencyname");
                            String ownernameresult = task.getResult().getString("ownername");
                            String agencyaddressresult = task.getResult().getString("agencyaddress");
                            String agencyemailresult = task.getResult().getString("agencyemail");
                            String agencyphnoresult = task.getResult().getString("agencyphno");
                            String agencydescriptionresult = task.getResult().getString("agencydescription");
                            String agencycapacityresult = task.getResult().getString("agencycapacity");

                            profileagencyname.setText(agencynameresult);
                            profileownername.setText(ownernameresult);
                            profileaddress.setText(agencyaddressresult);
                            profileemail.setText(agencyemailresult);
                            profilephno.setText(agencyphnoresult);
                            profiledescription.setText(agencydescriptionresult);
                            profilecapacity.setText(agencycapacityresult);

                        }else {
                            Intent a = new Intent(CatererViewProfile.this, CatererAddProfile.class);
                            startActivity(a);
                        }
                    }
                });











    }






}