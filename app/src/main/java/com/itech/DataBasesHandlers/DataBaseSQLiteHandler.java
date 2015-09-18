package com.itech.DataBasesHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.itech.models.BabyName;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class DataBaseSQLiteHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "bemyappdb";
    Context context ;
    private ArrayList<BabyName> babyNames_tous;



    public DataBaseSQLiteHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context ;
    }
    // Au moment du lancement
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_babyname = "create table " +
                "babyname(id integer primary key,nombebe text,checked integer, genre text)";
        db.execSQL(create_table_babyname);
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
       initData();
       for(BabyName babyname : babyNames_tous){
           ContentValues contentValues = new ContentValues();
           contentValues.put("nombebe", babyname.getName());
           contentValues.put("checked", babyname.isChecked());
           contentValues.put("genre", babyname.getGenre());
           db.insert("babyname",null,contentValues);
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


    public void initData(){
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

    public byte[] getImageByte(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] imageByte = stream.toByteArray();
        return imageByte;
    }
}
