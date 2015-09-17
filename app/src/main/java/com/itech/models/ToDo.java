package com.itech.models;

/**
 * Created by oSunshine on 06/07/2015.
 */
public class ToDo {

    String date, commentaire;
    boolean done;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ToDo(String date, String commentaire, boolean done) {
        this.date = date;
        this.commentaire = commentaire;
        this.done = done;
    }
}
