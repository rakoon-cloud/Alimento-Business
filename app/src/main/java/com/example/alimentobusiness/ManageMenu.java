package com.example.alimentobusiness;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ManageMenu extends AppCompatActivity {
    ImageView foodimg;
    EditText foodname, fooddescription, foodprice, foodtime;
    Button uploadfood;
    FirebaseFirestore firebaseFirestore;

    int PICK_IMAGE_REQUEST = 111;
    Uri filepath;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();

















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
        foodimg = findViewById(R.id.iv_foodimg);
        foodname = findViewById(R.id.ed_foodname);
        fooddescription = findViewById(R.id.ed_fooddescription);
        foodprice = findViewById(R.id.ed_foodprice);
        foodtime = findViewById(R.id.ed_foodtime);
        uploadfood = findViewById(R.id.button_uploadfood);
        foodimg = findViewById(R.id.iv_foodimg);

        firebaseFirestore = FirebaseFirestore.getInstance();


        foodimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        uploadfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = foodname.getText().toString();
                String description = fooddescription.getText().toString();
                String price = foodprice.getText().toString();
                String time = foodtime.getText().toString();

                Map<String,String>Foods=new HashMap<>();
                Foods.put("foodname", name);
                Foods.put("foodescription", description);
                Foods.put("foodprice", price);
                Foods.put("foodtime", time);

                firebaseFirestore.collection("Foods").add(Foods).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ManageMenu.this, "Food Item Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error=e.getMessage();

                        Toast.makeText(ManageMenu.this, "Food item upload failed:"+error, Toast.LENGTH_SHORT).show();
                    }
                });


                if (filepath != null){
                    StorageReference childRef = storageReference.child("foodimage");

                    UploadTask uploadTask = childRef.putFile(filepath);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(ManageMenu.this, "Upload successfull", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ManageMenu.this, "Upload failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Toast.makeText(ManageMenu.this, "Select image", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);

                foodimg.setImageBitmap(bitmap);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
