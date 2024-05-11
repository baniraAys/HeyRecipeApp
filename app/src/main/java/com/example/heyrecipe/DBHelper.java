package com.example.heyrecipe;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{
        public static final String DBname = "register.db";
        private Context context;
    public DBHelper(@Nullable Context context ) {
        super(context, DBname, null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE user_recipe(id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_name TEXT, recipe_ingredients TEXT, recipe_steps TEXT);");
        Log.i("CHECK","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user_recipe");
        Log.i("CHECK","Table Dropped");
    }

    public Boolean insertData(String username, String password){
        Log.i("CHECK","User Created");
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Boolean checkUser(String username){
        Log.i("CHECK","User Checked");
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUser(String user, String passw){
        Log.i("CHECK","User Check if existing");
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password = ?", new String[]{user,passw});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
