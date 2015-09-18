package com.itech.mybabygrowing;

/**
 * Created by oSunshine on 18/09/2015.
 */
public class ProjetNaissance {

    String nom ,prenom, date  ;

    public static ProjetNaissance projetNaissance ;

    public ProjetNaissance() {

        this.projetNaissance=this ;
    }

    boolean isMale ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }
}
