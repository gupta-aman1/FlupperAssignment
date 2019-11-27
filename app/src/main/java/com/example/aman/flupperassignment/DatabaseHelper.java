package com.example.aman.flupperassignment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME= "Product.db";
    public static final String TABLE_NAME= "product_table";
    public static final String COL_1= "ID";
    public static final String COL_2= "NAME";
    public static final String COL_3= "DESCRIPTION";
    public static final String COL_4= "PRICE";
    public static final String COL_5= "COLORS";
    public static final String COL_6= "IMAGE";
    public static final String COL_7= "SPRICE";

// When constructor is called then Database will be created

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

// table will be created when onCreate method will be called
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION TEXT,PRICE TEXT,COLORS TEXT,IMAGE BLOB,SPRICE TEXT)");
    }

//  TO drop table if already exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String description,String price,String colors,byte[] image,String sprice)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,description);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5,colors);
        contentValues.put(COL_6,image);
        contentValues.put(COL_7,sprice);
       long result= db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllProduct()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db =this.getWritableDatabase();
      return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public boolean updateData(String id,String name,String description,String price,String colors,byte[] image,String sprice)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,description);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5,colors);
        contentValues.put(COL_6,image);
        contentValues.put(COL_7,sprice);
        db.update(TABLE_NAME, contentValues, "id = ?",new String[] { id });
        return true;
    }
}
