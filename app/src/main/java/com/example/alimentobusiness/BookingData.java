package com.example.alimentobusiness;

public class BookingData {

    String bkfullname, bkdate;

    public  BookingData(){}

    public BookingData(String bkfullname, String bkdate) {
        this.bkfullname = bkfullname;
        this.bkdate = bkdate;
    }

    public String getBkfullname() {
        return bkfullname;
    }

    public void setBkfullname(String bkfullname) {
        this.bkfullname = bkfullname;
    }

    public String getBkdate() {
        return bkdate;
    }

    public void setBkdate(String bkdate) {
        this.bkdate = bkdate;
    }
}
