package com.firebaseproject.app;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class AddStudentSQActivity extends AppCompatActivity {


    EditText nameTxt,studentIdEditTxt,surnameEditTxt,fatherNameEditTxt,nationalIdEditTxt,dobEditTxt,genderEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_sqactivity);

        init();
    }

    private void init()
    {



        //layout of dialog

        studentIdEditTxt= (EditText) findViewById(R.id.studentIdEditTxt);

        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        surnameEditTxt= (EditText) findViewById(R.id.surnameEditTxt);
        fatherNameEditTxt= (EditText) findViewById(R.id.fatherNameEditTxt);
        nationalIdEditTxt= (EditText) findViewById(R.id.nationalIdEditTxt);
        dobEditTxt= (EditText) findViewById(R.id.dobEditTxt);
        genderEditTxt= (EditText) findViewById(R.id.genderEditTxt);

        Button savebtn= (Button) findViewById(R.id.saveBtn);

        //ONCLICK LISTENERS
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(studentIdEditTxt.getText().toString(), nameTxt.getText().toString(),surnameEditTxt.getText().toString(),fatherNameEditTxt.getText().toString(),nationalIdEditTxt.getText().toString(),dobEditTxt.getText().toString(),genderEditTxt.getText().toString());
            }
        });


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
        //retrieve();

    }

//    //RETRIEVE
//    private void retrieve()
//    {
//        DBAdapter db=new DBAdapter(this);
//
//        //OPEN
//        db.openDB();
//
//        students.clear();
//
//        //SELECT
//        Cursor c=db.getAllPlayers();
//
//        //LOOP THRU THE DATA ADDING TO ARRAYLIST
//        while (c.moveToNext())
//        {
//            int id=c.getInt(0);
//            String studentId=c.getString(1);
//            String name=c.getString(2);
//            String surname=c.getString(3);
//            String fatherName=c.getString(4);
//            String nationalId=c.getString(5);
//            String dob=c.getString(6);
//            String gender=c.getString(7);
//
//            //CREATE PLAYER
//            Student p=new Student(studentId,name,surname,fatherName,nationalId,dob,gender,id);
//
//            //ADD TO PLAYERS
//            students.add(p);
//        }
//
//        //SET ADAPTER TO RV
//        if(!(students.size()<1))
//        {
//            rv.setAdapter(adapter);
//        }
//
//    }

}