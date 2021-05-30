package com.example.petcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    private Context context;
    public static final String DATABASE_NAME = "patientsData.db";
    public static final String TABLE_PATIENT_DETAILS ="patient_library";
    public static final String TABLE_CONSULTATION ="consultation";
    public static final String TABLE_INCOME="income";
    public static final String COLUMN_DAILY_INCOME="daily_charges";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_OWNER = "owner";
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_ADDRESS= "address";
    public static final String COLUMN_PET_NAME= "pet";
    public static final String COLUMN_CONSID="cons_id";
    public static final String COLUMN_PRESCRIPTION="prescription";
    public static final String COLUMN_DIAGNOSIS="diagnosis";
    public static final String COLUMN_DATE="date";
    public static final String COLUMNS_AGE="age";
    public static final String COLUMN_WEIGHT="weight";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "CREATE TABLE " + TABLE_PATIENT_DETAILS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_OWNER + " TEXT, " + COLUMN_TELEPHONE + " INTEGER, " + COLUMN_ADDRESS + " TEXT ,"+ COLUMN_PET_NAME +" TEXT );");
        db.execSQL( "CREATE TABLE " + TABLE_CONSULTATION + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CONSID + " INTEGER, " + COLUMN_PRESCRIPTION + " TEXT, " + COLUMN_DIAGNOSIS + " TEXT ,"+ COLUMN_DATE +" TEXT,"+COLUMN_PET_NAME+" TEXT ,"+COLUMNS_AGE+" TEXT, "+COLUMN_DAILY_INCOME+" INTEGER,"+COLUMN_WEIGHT+" INTEGER,"+COLUMN_OWNER+" TEXT, " +COLUMN_TELEPHONE+" TEXT);");
        db.execSQL( "CREATE TABLE " + TABLE_INCOME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DATE + " TEXT, "+COLUMN_DAILY_INCOME+" INTEGER  );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONSULTATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        onCreate(db);
    }

    public int getId() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_PATIENT_DETAILS);

        return (int)count;
    }

    boolean addPatient(String owner, String telephone, String address, String petName){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_OWNER, owner);
        cv.put(COLUMN_TELEPHONE, telephone);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_PET_NAME,petName);

//if the query returns a -1 it means the movie adding is unsuccessful
        if (db.insert(TABLE_PATIENT_DETAILS, null, cv)==-1) {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            return false;

        } else {
            Toast.makeText(context,"Successfully Added",Toast.LENGTH_SHORT).show();
            return true;

        }

    }

    Cursor idChecker(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_PATIENT_DETAILS+" WHERE " + COLUMN_ID + " = " + id, null);
        return cursor;
    }

    boolean addConsultation(String consId, String prescription, String diagnosis, String petName,String petAge,String date ,String charges,String weight,String owner,String telephone){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CONSID, consId);
        cv.put(COLUMN_PRESCRIPTION, prescription);
        cv.put(COLUMN_DIAGNOSIS, diagnosis);
        cv.put(COLUMN_PET_NAME,petName);
        cv.put(COLUMNS_AGE,petAge);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_DAILY_INCOME,charges);
        cv.put(COLUMN_WEIGHT,weight);
        cv.put(COLUMN_OWNER,owner);
        cv.put(COLUMN_TELEPHONE,telephone);

//if the query returns a -1 it means the movie adding is unsuccessful
        if (db.insert(TABLE_CONSULTATION, null, cv)==-1) {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            return false;

        } else {
            Toast.makeText(context,"Successfully Added",Toast.LENGTH_SHORT).show();
            return true;

        }

    }
    boolean addIncome(String date, String charges){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_DAILY_INCOME, charges);
        System.out.println(charges);
//if the query returns a -1 it means the movie adding is unsuccessful
        if (db.insert(TABLE_INCOME, null, cv)==-1) {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            return false;

        } else {
            Toast.makeText(context,"Successfully Added",Toast.LENGTH_SHORT).show();
            return true;

        }

    }

    Cursor incomeData(){
        String query = "SELECT * FROM " + TABLE_INCOME+ ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    Cursor consultationData(String columnName){
        String query = "SELECT * FROM " + TABLE_CONSULTATION+ ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
