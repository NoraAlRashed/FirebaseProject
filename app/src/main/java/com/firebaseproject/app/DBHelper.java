package com.firebaseproject.app;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Students.DB_NAME, null, Students.DB_VERSION);
    }

    //WHEN TB IS CREATED
    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(Students.CREATE_TB);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    //UPGRADE TB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS"+ Students.TB_NAME);
        onCreate(db);
    }
}
