package com.firebaseproject.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//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

public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context ctx)
    {
        this.c=ctx;
        helper=new DBHelper(c);
    }

    //OPEN DB
    public DBAdapter openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    //CLOSE
    public void close()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    //INSERT DATA TO DB
    public long add(String studentId,String name,String surname,String fatherName,String nationalId,String dob,String gender)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Students.STUDENTID,studentId);
            cv.put(Students.NAME, name);
            cv.put(Students.SURNAME, surname);
            cv.put(Students.FATHERNAME, fatherName);
            cv.put(Students.NATIONALID, nationalId);
            cv.put(Students.DOB, dob);
            cv.put(Students.GENDER, gender);

            return db.insert(Students.TB_NAME, Students.ROW_ID,cv);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

    //RETRIEVE ALL PLAYERS
    public Cursor getAllPlayers()
    {
        String[] columns={Students.ROW_ID, Students.STUDENTID, Students.NAME, Students.SURNAME, Students.FATHERNAME, Students.NATIONALID, Students.DOB, Students.GENDER};

        return db.query(Students.TB_NAME,columns,null,null,null,null,null);
    }

    //UPDATE
    public long UPDATE(int id,String studentId,String name,String surname,String fatherName,String nationalId,String dob,String gender)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Students.STUDENTID,studentId);
            cv.put(Students.NAME, name);
            cv.put(Students.SURNAME, surname);
            cv.put(Students.FATHERNAME, fatherName);
            cv.put(Students.NATIONALID, nationalId);
            cv.put(Students.DOB, dob);
            cv.put(Students.GENDER, gender);

            return db.update(Students.TB_NAME,cv, Students.ROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

    //DELETE
    public long Delete(int id)
    {
        try
        {

            return db.delete(Students.TB_NAME, Students.ROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

}
