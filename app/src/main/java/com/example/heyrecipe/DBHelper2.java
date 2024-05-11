package com.example.heyrecipe;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {
    private Context context;
    private static final String DBname = "register.db";
    private static final String TABLE_NAME = "user_recipe";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "recipe_name";
    private static final String COLUMN_INGRE = "recipe_ingredients";
    private static final String COLUMN_STEPS = "recipe_steps";

    //private String TABLE_CREATE_RECIPE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_NAME + " TEXT, " + COLUMN_INGRE + " TEXT, "+ COLUMN_STEPS + " TEXT);";
    DBHelper2(@Nullable Context context) {
        super(context, DBname, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user_recipe(id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_name TEXT, recipe_ingredients TEXT, recipe_steps TEXT);");
        Log.i("CHECK","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user_recipe");
        onCreate(sqLiteDatabase);
        Log.i("CHECK","Table Dropped");
    }

    public Boolean addRecipe(String name, String ingre, String recipe){
        Log.i("CHECK","Add Recipe method");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_INGRE, ingre);
        cv.put(COLUMN_STEPS, recipe);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result== -1){
            return false;
        }else{
            return true;
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Boolean updateData(String row_id, String name, String ingre, String steps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_INGRE, ingre);
        cv.put(COLUMN_STEPS, steps);


        long result = db.update(TABLE_NAME, cv, " id = ?", new String[]{row_id});
        Log.i("WORKING","UPDATE " + TABLE_NAME + " SET " + cv + "\n WHERE id = " + row_id);
        Log.i("WORKING", "RESULT: " + result);

        if(result == -1)
            return false;
        else
            return true;

    }
    public Boolean deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id = ?", new String[]{row_id});

        if (result == -1)
            return false;
        else
            return true;
    }
}
