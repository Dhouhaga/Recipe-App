package com.example.yumyum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Recipes.db";
    private static final int DATABASEVERSION = 1;

    private static final String TABLE_NAME = "my_recipes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CATEGORY = "categ";
    private static final String COLUMN_INGREDIENT = "ingred";
    private static final String COLUMN_DESCRIPTION = "des";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASEVERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_CATEGORY + " TEXT, " +
                        COLUMN_INGREDIENT + " TEXT, "+
                        COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRecipe(String name, String ing, String descr, String catg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CATEGORY,catg);
        cv.put(COLUMN_DESCRIPTION,descr);
        cv.put(COLUMN_INGREDIENT,ing);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Saved !", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteRecipe(String row_id){
        SQLiteDatabase db = this.getReadableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,null,null);
        db.close();
        if(result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
        }
    }
   void updateData(String row_id, String name, String cat, String ing, String des){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CATEGORY,cat);
        cv.put(COLUMN_INGREDIENT,ing);
        cv.put(COLUMN_DESCRIPTION, des);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Update Saved !", Toast.LENGTH_SHORT).show();
        }
    }

    }
