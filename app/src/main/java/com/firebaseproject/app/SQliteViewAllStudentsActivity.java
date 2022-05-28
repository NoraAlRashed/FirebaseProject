package com.firebaseproject.app;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SQliteViewAllStudentsActivity extends AppCompatActivity {


    EditText nameTxt,studentIdEditTxt,surnameEditTxt,fatherNameEditTxt,nationalIdEditTxt,dobEditTxt,genderEditTxt;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Student> students =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_all_students);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddStudentSQActivity.class);
                startActivity(intent);
            }
        });


        rv= (RecyclerView) findViewById(R.id.recyclerViews);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));



        adapter=new MyAdapter(this, students);

        retrieve();

    }




    //RETRIEVE
    private void retrieve()
    {
        DBAdapter db=new DBAdapter(this);

        //OPEN
        db.openDB();

        students.clear();

        //SELECT
        Cursor c=db.getAllPlayers();

        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String studentId=c.getString(1);
            String name=c.getString(2);
            String surname=c.getString(3);
            String fatherName=c.getString(4);
            String nationalId=c.getString(5);
            String dob=c.getString(6);
            String gender=c.getString(7);

            //CREATE PLAYER
            Student p=new Student(studentId,name,surname,fatherName,nationalId,dob,gender,id);

            //ADD TO PLAYERS
            students.add(p);
        }

        //SET ADAPTER TO RV
        if(!(students.size()<1))
        {
            rv.setAdapter(adapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieve();
    }
}
