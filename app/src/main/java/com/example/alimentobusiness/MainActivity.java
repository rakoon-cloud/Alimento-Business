package com.example.alimentobusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText repname, prep, time, ing, dir;
    Button upload;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor (this.getResources().getColor(R.color.black));
        }

        repname=findViewById(R.id.ed_repname);
        prep=findViewById(R.id.ed_prep);
        time=findViewById(R.id.ed_duration);
        ing=findViewById(R.id.ed_ing);
        dir=findViewById(R.id.ed_dir);
        upload=findViewById(R.id.button_upload);

        firebaseFirestore=FirebaseFirestore.getInstance();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String recipename=repname.getText().toString();
                String preptime=prep.getText().toString();
                String duration=time.getText().toString();
                String ingredients=ing.getText().toString();
                String directions=dir.getText().toString();

                Map<String,String>Recipes=new HashMap<>();
                Recipes.put("recipename", recipename);
                Recipes.put("prep", preptime);
                Recipes.put("time", duration);
                Recipes.put("ing", ingredients);
                Recipes.put("dir", directions);

                firebaseFirestore.collection("Recipes").add(Recipes).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(MainActivity.this, "Recipe Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error=e.getMessage();

                        Toast.makeText(MainActivity.this, "Recipe upload failed:"+error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}