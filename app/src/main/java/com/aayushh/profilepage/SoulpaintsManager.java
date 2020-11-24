package com.aayushh.profilepage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SoulpaintsManager {
    private SoulpaintsData soulpaintsData;
    private Context context;
    private SQLiteDatabase database;

    public SoulpaintsManager(Context c){
        this.context = c;
    }

    public SoulpaintsManager open() throws SQLException {
        soulpaintsData = new SoulpaintsData(context);
        database = soulpaintsData.getWritableDatabase();
        return this;
    }

    public void close() {
        soulpaintsData.close();
    }

    public void insert(String name,String location, String description , String price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SoulpaintsData.paintingName, name);
        contentValues.put(SoulpaintsData.location, location);
        contentValues.put(SoulpaintsData.description, description);
        contentValues.put(SoulpaintsData.price, price);
        database.insert(SoulpaintsData.tableName, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[] {SoulpaintsData._ID, SoulpaintsData.paintingName, SoulpaintsData.location, SoulpaintsData.description, SoulpaintsData.price};
        Cursor cursor = database.query(SoulpaintsData.tableName, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
