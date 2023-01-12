package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CatererAddProfile extends AppCompatActivity {

    EditText proagencyname,proownername,proaddress,proemail,prophno,prodescritpion,procapacity;
    Button savecatprofilebtn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    All_UserMember member;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_add_profile);

        member=new All_UserMember();
        proagencyname=findViewById(R.id.ed_catagencyname);
        proownername=findViewById(R.id.ed_catownername);
        proaddress=findViewById(R.id.ed_cataddress);
        proemail=findViewById(R.id.ed_agencyemail);
        prophno=findViewById(R.id.ed_agencyphone);
        prodescritpion=findViewById(R.id.ed_catdescription);
        procapacity=findViewById(R.id.ed_catcapacity);
        savecatprofilebtn=findViewById(R.id.button_catsaveprofile);


        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        currentUserId = user.getUid();

        documentReference = db.collection("caterers").document(currentUserId);
        databaseReference = database.getReference("All Caterers");

        savecatprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });

    }

    private void uploadData() {

        String agencyname = proagencyname.getText().toString();
        String ownername = proownername.getText().toString();
        String agencyaddress = proaddress.getText().toString();
        String agencyemail = proemail.getText().toString();
        String agencyphno = prophno.getText().toString();
        String agencydescription = prodescritpion.getText().toString();
        String agencycapacity = procapacity.getText().toString();




        if(!TextUtils.isEmpty(agencyname) || !TextUtils.isEmpty(ownername) || !TextUtils.isEmpty(agencyaddress) || !TextUtils.isEmpty(agencyemail) || !TextUtils.isEmpty(agencyphno) ||
                !TextUtils.isEmpty(agencydescription) || !TextUtils.isEmpty(agencycapacity) ){

            Map<String,String> Caterers=new HashMap<>();
            Caterers.put("agencyname", agencyname);
            Caterers.put("ownername", ownername);
            Caterers.put("agencyaddress", agencyaddress);
            Caterers.put("agencyemail", agencyemail);
            Caterers.put("agencyphno", agencyphno);
            Caterers.put("agencydescription", agencydescription);
            Caterers.put("agencycapacity", agencycapacity);
            Caterers.put("uid",currentUserId);

            member.setAgencyname(agencyname);
            member.setOwnername(ownername);
            member.setAgencyaddress(agencyaddress);
            member.setAgencyemail(agencyemail);
            member.setAgencyphno(agencyphno);
            member.setAgencydescription(agencydescription);
            member.setAgencycapacity(agencycapacity);

            databaseReference.child(currentUserId).setValue(member);

            documentReference.set(Caterers)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(CatererAddProfile.this, "Profile Saved", Toast.LENGTH_SHORT).show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(CatererAddProfile.this, CatererHome.class);
                                    startActivity(i);
                                }
                            }, 2000);
                        }
                    });


        }else{
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }
}