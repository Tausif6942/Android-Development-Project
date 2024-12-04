package com.example.first;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBase extends SQLiteOpenHelper {

    private Context context;

    private final static String DBNAME="MyDatabase.db";
    private final static int DBVERSION=15;
    private final static String TABLE_NAME="Animal";
    private final static String ID="id";
    private final static String NAME="name";
    private final static String DESC="description";
    private final static String IMAGE="image";
    public DBase(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
                String query = "create table "+ TABLE_NAME +
                        " (" + ID + " integer primary key autoincrement, " +
                        NAME + " text, " +
                        DESC + " text, " +
                        IMAGE + " integer);";
                db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertAnimal(String name, String description, int image) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("image", image);
        long id = database.insert("Animal", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }
    Cursor readAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY RANDOM()";
        Cursor cursor=null;
        if(db!=null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


}
