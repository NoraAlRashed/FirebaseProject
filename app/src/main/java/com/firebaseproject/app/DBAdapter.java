package com.firebaseproject.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


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
            cv.put(Contants.STUDENTID,studentId);
            cv.put(Contants.NAME, name);
            cv.put(Contants.SURNAME, surname);
            cv.put(Contants.FATHERNAME, fatherName);
            cv.put(Contants.NATIONALID, nationalId);
            cv.put(Contants.DOB, dob);
            cv.put(Contants.GENDER, gender);

            return db.insert(Contants.TB_NAME,Contants.ROW_ID,cv);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

    //RETRIEVE ALL PLAYERS
    public Cursor getAllPlayers()
    {
        String[] columns={Contants.ROW_ID,Contants.STUDENTID,Contants.NAME,Contants.SURNAME,Contants.FATHERNAME,Contants.NATIONALID,Contants.DOB,Contants.GENDER};

        return db.query(Contants.TB_NAME,columns,null,null,null,null,null);
    }

    //UPDATE
    public long UPDATE(int id,String studentId,String name,String surname,String fatherName,String nationalId,String dob,String gender)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Contants.STUDENTID,studentId);
            cv.put(Contants.NAME, name);
            cv.put(Contants.SURNAME, surname);
            cv.put(Contants.FATHERNAME, fatherName);
            cv.put(Contants.NATIONALID, nationalId);
            cv.put(Contants.DOB, dob);
            cv.put(Contants.GENDER, gender);

            return db.update(Contants.TB_NAME,cv,Contants.ROW_ID+" =?",new String[]{String.valueOf(id)});

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

            return db.delete(Contants.TB_NAME,Contants.ROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return 0;
    }

}
