package com.example.alimentobusiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    Context context;
    ArrayList<BookingData> bookingDataArrayList;

    public BookingAdapter(Context context, ArrayList<BookingData> bookingDataArrayList) {
        this.context = context;
        this.bookingDataArrayList = bookingDataArrayList;
    }

    @NonNull
    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.eventbooking,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.MyViewHolder holder, int position) {
        BookingData bookingData = bookingDataArrayList.get(position);
        holder.bkfullname.setText(bookingData.bkfullname);
        holder.bkdate.setText(bookingData.bkdate);

    }

    @Override
    public int getItemCount() {
        return bookingDataArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bkfullname, bkdate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bkfullname=itemView.findViewById(R.id.tv_booker);
            bkdate=itemView.findViewById(R.id.tv_bookdate);
        }
    }
}
