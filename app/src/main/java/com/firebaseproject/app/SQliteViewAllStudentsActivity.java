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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddStudentSQActivity.class);
                startActivity(intent);
            }
        });

        //recycler
        rv= (RecyclerView) findViewById(R.id.myRecycler);

        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        adapter=new MyAdapter(this, students);

        retrieve();

    }

    private void showDialog()
    {
        Dialog d=new Dialog(this);

        //NO TITLE
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);


        //layout of dialog
        d.setContentView(R.layout.custom_layout);

        studentIdEditTxt= (EditText) d.findViewById(R.id.studentIdEditTxt);

        nameTxt= (EditText) d.findViewById(R.id.nameEditTxt);
        surnameEditTxt= (EditText) d.findViewById(R.id.surnameEditTxt);
        fatherNameEditTxt= (EditText) d.findViewById(R.id.fatherNameEditTxt);
        nationalIdEditTxt= (EditText) d.findViewById(R.id.nationalIdEditTxt);
        dobEditTxt= (EditText) d.findViewById(R.id.dobEditTxt);
        genderEditTxt= (EditText) d.findViewById(R.id.genderEditTxt);

        Button savebtn= (Button) d.findViewById(R.id.saveBtn);
        Button retrieveBtn= (Button) d.findViewById(R.id.retrieveBtn);

        //ONCLICK LISTENERS
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               save(studentIdEditTxt.getText().toString(), nameTxt.getText().toString(),surnameEditTxt.getText().toString(),fatherNameEditTxt.getText().toString(),nationalIdEditTxt.getText().toString(),dobEditTxt.getText().toString(),genderEditTxt.getText().toString());
            }
        });

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieve();
            }
        });

        //SHOW DIALOG
        d.show();
    }

    //SAVE
    private void save(String studentId,String name,String surname,String fatherName,String nationalId,String dob,String gender) {
        DBAdapter db=new DBAdapter(this);
        //OPEN
        db.openDB();

        //INSERT
        long result=db.add(studentId,name,surname,fatherName,nationalId,dob,gender);

        if(result>0)
        {
            studentIdEditTxt.setText("");
            nameTxt.setText("");
            surnameEditTxt.setText("");
            fatherNameEditTxt.setText("");
            nationalIdEditTxt.setText("");
            dobEditTxt.setText("");
            genderEditTxt.setText("");


        }else
        {
            Snackbar.make(nameTxt,"Unable To Insert",Snackbar.LENGTH_SHORT).show();
        }

        //CLOSE
        db.close();

        //refresh
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
