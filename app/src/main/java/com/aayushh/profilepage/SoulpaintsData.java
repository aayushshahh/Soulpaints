package com.aayushh.profilepage;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import androidx.annotation.Nullable;
//import android.database.Cursor;


public class SoulpaintsData extends SQLiteOpenHelper{

    public static final String tableName = "Items";
    public static final String _ID = "_id";
    public static final String paintingName = "paintingName";
    public static final String location = "location";
    public static final String description = "description";
    public static final String price = "price";

    static final String DB_NAME = "Soulpaints";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + tableName + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + paintingName + " TEXT NOT NULL, " +location + " TEXT, " + description + " TEXT, " +  price  + " TEXT);";


    public SoulpaintsData(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }
}



