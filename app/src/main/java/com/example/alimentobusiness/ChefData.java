package com.example.alimentobusiness;

public class ChefData {

    String  chefname, chefphno;
    public ChefData(){

    }
    public ChefData(String chefname, String chefphno) {
        this.chefname = chefname;
        this.chefphno = chefphno;
    }

    public String getChefname() { return chefname; }

    public void setChefname(String chefname) { this.chefname = chefname; }

    public String getChefphno() { return chefphno; }

    public void setChefphno(String chefphno) { this.chefphno = chefphno; }
}
