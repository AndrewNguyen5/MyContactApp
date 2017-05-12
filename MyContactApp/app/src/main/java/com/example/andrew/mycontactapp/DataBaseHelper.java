package com.example.andrew.mycontactapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andrew on 5/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="Contacts.db";
    public static final String TABLE_NAME="contact_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db){

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}