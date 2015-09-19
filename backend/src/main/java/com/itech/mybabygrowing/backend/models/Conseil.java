package com.itech.mybabygrowing.backend.models;

/**
 * Created by Ahmed-PC on 19-09-2015.
 */
public class Conseil {


    private int week ;
    private String conseil ;
    private byte[] image ;


    public Conseil(){

    }
    public Conseil(int week, String conseil , byte[] image){
        this.week=week ;
        this.conseil=conseil ;
        this.image=image ;


    }


    public String getConseil() {
        return conseil;
    }

    public void setConseil(String conseil) {
        this.conseil = conseil;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
