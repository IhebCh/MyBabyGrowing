package com.itech.DataBasesHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.itech.models.Appointement;
import com.itech.models.BabyName;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class DataBaseSQLiteHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "bemyappdb";
    Context context ;
    private ArrayList<BabyName> babyNames_tous;
    private ArrayList<Appointement> rendezvous_tous;



    public DataBaseSQLiteHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context ;
    }
    // Au moment du lancement
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_babyname = "create table " +
                "babyname(id integer primary key,nombebe text,checked integer, genre text)";
        String create_table_rendezvous = "create table " +
                "rendezvous(id integer primary key,nomrendezvous text, date text, heure text)";

        db.execSQL(create_table_babyname);
        db.execSQL(create_table_rendezvous);
        ajouterDesNomsBebes(db);

    }
    // en cas de changement de version et la BDD existe déjà
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS babyname");
        onCreate(db);
    }
   public void ajouterDesNomsBebes(SQLiteDatabase db) {
       // SQLiteDatabase db = this.getWritableDatabase();
       initData_babynames();

       for(BabyName babyname : babyNames_tous){
           ContentValues contentValues = new ContentValues();
           contentValues.put("nombebe", babyname.getName());
           contentValues.put("checked", babyname.isChecked());
           contentValues.put("genre", babyname.getGenre());
           db.insert("babyname",null,contentValues);
           //db.close();
       }

   }

    public void ajouterDesRendezvous(SQLiteDatabase db) {
        // SQLiteDatabase db = this.getWritableDatabase();
        init_data_rendezvous();
        for(Appointement rendezvous : rendezvous_tous){
            ContentValues contentValues = new ContentValues();
            contentValues.put("nomrendezvous", rendezvous.getNom());
            contentValues.put("date", rendezvous.getDate());
            contentValues.put("heure", rendezvous.getHeure());
            db.insert("rendezvous",null,contentValues);
            //db.close();
        }

    }

    public  ArrayList<BabyName> getBabyNamesByGenre(String genre) {
        ArrayList<BabyName> babyNameslist = new ArrayList<BabyName>();
        String query ="select * from babyname where lower(genre)=?";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{genre.toLowerCase()});
        if(cursor.moveToFirst()) {
            do {
                BabyName babyName = new BabyName();
                babyName.setId(cursor.getInt(0));
                babyName.setName(cursor.getString(1));
                if(cursor.getInt(2)==1){
                    babyName.setChecked(true);
                }
                else{
                    babyName.setChecked(false);
                }


                babyName.setGenre(cursor.getString(3));
                babyNameslist.add(babyName);
            }while(cursor.moveToNext());
        }
        db.close();
        return babyNameslist ;
    }

    public  ArrayList<Appointement> getAllAppointement() {
        ArrayList<Appointement> rendezvouslist = new ArrayList<Appointement>();
        String query ="select * from rendezvous";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null) ;
        if(cursor.moveToFirst()) {
            do {
                Appointement rendezvous = new Appointement();
                rendezvous.setId(cursor.getInt(0));
                rendezvous.setNom(cursor.getString(1));
                rendezvous.setDate(cursor.getString(2));
                rendezvous.setDate(cursor.getString(3));
                rendezvous.setDate(cursor.getString(4));
                rendezvouslist.add(rendezvous);
            }while(cursor.moveToNext());
        }
        db.close();
        return rendezvouslist ;
    }


    public void initData_babynames(){
        BabyName[] babyName_tous_table = new BabyName[]
       { new BabyName("Iheb", false,"boy"),
        new BabyName("Anis", false,"boy"),
        new BabyName("Said", false,"boy"),


        new BabyName("Abderahman", false,"boy"),
        new BabyName("Amine", false,"boy"),
        new BabyName("Imad", false,"boy"),
        new BabyName("Ishak", false,"boy"),
        new BabyName("Younes", false,"boy"),

       new BabyName("Hiba", false,"girl"),
       new BabyName("Lina", false,"girl"),
       new BabyName("Nawal", false,"girl"),
       new BabyName("Khadija", false,"girl")

                };
        babyNames_tous =  new ArrayList<>(Arrays.asList(babyName_tous_table)) ;
    }

    public void init_data_rendezvous() {
        Appointement[] rendezvous_tous = new Appointement[]
                {new Appointement("rendez vous 1","20 / 06 / 2015 ", "08:00", " rendez vous pour ... "),
                        new Appointement("rendez vous 2","12 / 07 / 2015 ", "09:00", " rendez vous pour ... "),
                        new Appointement("rendez vous 3","10 / 08 / 2015 ", "10:00", " rendez vous pour ..."),

                } ;
    }
    public void ajouterRendezVous(String nom , String date , String heure , String commentaire){

        SQLiteDatabase db = this.getWritableDatabase() ;

        ContentValues contentValues = new ContentValues();
        contentValues.put("nomrendezvous", nom);
        contentValues.put("date", date);
        contentValues.put("heure", heure);
        contentValues.put("commentaire", commentaire);
        db.insert("rendezvous",null,contentValues);
    }
    public byte[] getImageByte(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] imageByte = stream.toByteArray();
        return imageByte;
    }
}
