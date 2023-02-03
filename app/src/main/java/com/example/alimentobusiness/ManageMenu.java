package com.example.alimentobusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ManageMenu extends AppCompatActivity {
    ImageView foodimg;
    EditText foodname, fooddescription, foodprice, foodtime;
    Button uploadfood;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    Food_Info info;
    String currentFoodId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor (this.getResources().getColor(R.color.black));
        }
        info=new Food_Info();
        foodimg = findViewById(R.id.iv_foodimg);
        foodname = findViewById(R.id.ed_foodname);
        fooddescription = findViewById(R.id.ed_fooddescription);
        foodprice = findViewById(R.id.ed_foodprice);
        foodtime = findViewById(R.id.ed_foodtime);
        uploadfood = findViewById(R.id.button_uploadfood);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        currentFoodId = user.getUid();

        documentReference = db.collection("foods").document(currentFoodId);
        databaseReference = database.getReference("All Foods");


        uploadfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFoods();
            }
        });

    }

    private void uploadFoods() {

        String restfoodname = foodname.getText().toString();
        String restfooddescription = fooddescription.getText().toString();
        String restfoodprice = foodprice.getText().toString();
        String restfoodtime = foodtime.getText().toString();





        if(!TextUtils.isEmpty(restfoodname) || !TextUtils.isEmpty(restfooddescription) || !TextUtils.isEmpty(restfoodprice) || !TextUtils.isEmpty(restfoodtime)){

            Map<String,String> Foods=new HashMap<>();
            Foods.put("restfoodname", restfoodname);
            Foods.put("restfooddescription", restfooddescription);
            Foods.put("restfoodprice", restfoodprice);
            Foods.put("restfoodtime", restfoodtime);
            Foods.put("uid",currentFoodId);

            info.setRestfoodname(restfoodname);
            info.setRestfooddescription(restfooddescription);
            info.setRestfoodprice(restfoodprice);
            info.setRestfoodtime(restfoodtime);


            databaseReference.child(currentFoodId).setValue(info);

            documentReference.set(Foods)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(ManageMenu.this, "Food Item Saved", Toast.LENGTH_SHORT).show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(ManageMenu.this, RestaurantHome.class);
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