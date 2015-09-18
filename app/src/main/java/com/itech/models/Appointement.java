package com.itech.models;

/**
 * Created by oSunshine on 03/07/2015.
 */
public class Appointement {

    private int id ;

    String nom, date, heure, commentaire;

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Appointement(String nom , String date , String heure , String commentaire){

        this.nom = nom ;
        this.date=date ;
        this.heure=heure ;
        this.commentaire=commentaire ;

    }

    public Appointement(){

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    int icon ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
