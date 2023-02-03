package com.example.alimentobusiness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CatererViewBooking extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<BookingData> bookingDataArrayList;

    BookingAdapter myAdapter;

    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_view_booking);

        recyclerView = findViewById(R.id.recyclerView_bookings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        bookingDataArrayList = new ArrayList<BookingData>();
        myAdapter = new BookingAdapter(CatererViewBooking.this,bookingDataArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();
    }

    private  void EventChangeListener(){
        db.collection("BookingsCaterer").orderBy("bkfullname", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){

                                bookingDataArrayList.add(dc.getDocument().toObject(BookingData.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}