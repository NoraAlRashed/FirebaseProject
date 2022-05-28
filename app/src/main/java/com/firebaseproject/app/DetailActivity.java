package com.firebaseproject.app;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {

    EditText nameTxt,studentIdEditTxt,surnameEditTxt,fatherNameEditTxt,nationalIdEditTxt,dobEditTxt,genderEditTxt;
    Button updateBtn,deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i=getIntent();


        final String studentId=i.getExtras().getString("studentId");

        final String name=i.getExtras().getString("name");
        final String surname=i.getExtras().getString("surname");
        final String fatherName=i.getExtras().getString("fatherName");
        final String nationalId=i.getExtras().getString("nationalId");
        final String dob=i.getExtras().getString("dob");
        final String gender=i.getExtras().getString("gender");


        final int id=i.getExtras().getInt("ID");

        updateBtn= (Button) findViewById(R.id.updateBtn);
        deleteBtn= (Button) findViewById(R.id.deleteBtn);

        studentIdEditTxt= (EditText) findViewById(R.id.studentIdEditTxt);

        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        surnameEditTxt= (EditText) findViewById(R.id.surnameEditTxt);
        fatherNameEditTxt= (EditText) findViewById(R.id.fatherNameEditTxt);
        nationalIdEditTxt= (EditText) findViewById(R.id.nationalIdEditTxt);
        dobEditTxt= (EditText) findViewById(R.id.dobEditTxt);
        genderEditTxt= (EditText) findViewById(R.id.genderEditTxt);

        //ASSIGN DATA TO THOSE VIEWS
        studentIdEditTxt.setText(studentId);
        nameTxt.setText(name);
        surnameEditTxt.setText(surname);
        fatherNameEditTxt.setText(fatherName);
        nationalIdEditTxt.setText(nationalId);
        dobEditTxt.setText(dob);
        genderEditTxt.setText(gender);

        //update
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(id,studentIdEditTxt.getText().toString(),nameTxt.getText().toString(),surnameEditTxt.getText().toString(),fatherNameEditTxt.getText().toString(),nationalIdEditTxt.getText().toString(),dobEditTxt.getText().toString(),genderEditTxt.getText().toString());
            }
        });

        //DELETE
        //update
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              delete(id);
            }
        });


    }

    private void update(int id,String studentId,String name,String surname,String fatherName,String nationalId,String dob,String gender)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.UPDATE(id,studentId,name,surname,fatherName,nationalId,dob,gender);

        if(result>0)
        {
            studentIdEditTxt.setText(studentId);
            nameTxt.setText(name);
            surnameEditTxt.setText(surname);
            fatherNameEditTxt.setText(fatherName);
            nationalIdEditTxt.setText(nationalId);
            dobEditTxt.setText(dob);
            genderEditTxt.setText(gender);;

            Snackbar.make(nameTxt,"Updated Sucesfully",Snackbar.LENGTH_SHORT).show();
        }else
        {
            Snackbar.make(nameTxt,"Unable to Update",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }

    //DELETE
    private void delete(int id)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.Delete(id);

        if(result>0)
        {
            this.finish();
        }else
        {
            Snackbar.make(nameTxt,"Unable to Update",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }


}
