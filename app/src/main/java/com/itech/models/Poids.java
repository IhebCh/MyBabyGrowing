package com.itech.models;

/**
 * Created by Ahmed-PC on 18-09-2015.
 */
public class Poids {
    private String date ;
    private float poid ;

    public Poids(){

    }
    public Poids(String date , float poid){
        this.date = date ;
        this.poid= poid ;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPoid() {
        return poid;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }
}
