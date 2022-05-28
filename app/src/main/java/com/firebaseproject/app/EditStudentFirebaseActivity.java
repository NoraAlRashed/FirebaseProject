package com.firebaseproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditStudentFirebaseActivity extends AppCompatActivity {


    Button save;


    DatabaseReference databaseReference;

    EditText nameTxt,studentIdEditTxt,surnameEditTxt,fatherNameEditTxt,nationalIdEditTxt,dobEditTxt,genderEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_firebase);

        //Top action bar
        ActionBar a = getSupportActionBar();
        a.setTitle("Edit Product");
        a.setDisplayHomeAsUpEnabled(true);


        //init Views
        save =findViewById(R.id.save);

        studentIdEditTxt= (EditText) findViewById(R.id.studentIdEditTxt);

        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        surnameEditTxt= (EditText) findViewById(R.id.surnameEditTxt);
        fatherNameEditTxt= (EditText) findViewById(R.id.fatherNameEditTxt);
        nationalIdEditTxt= (EditText) findViewById(R.id.nationalIdEditTxt);
        dobEditTxt= (EditText) findViewById(R.id.dobEditTxt);
        genderEditTxt= (EditText) findViewById(R.id.genderEditTxt);

        //getting data from intent

        Intent i=getIntent();

        final String studentId=i.getExtras().getString("studentId");

        final String name=i.getExtras().getString("name");
        final String surname=i.getExtras().getString("surname");
        final String fatherName=i.getExtras().getString("fatherName");
        final String nationalId=i.getExtras().getString("nationalId");
        final String dob=i.getExtras().getString("dob");
        final String gender=i.getExtras().getString("gender");



        //set data in fields
        studentIdEditTxt.setText(studentId);
        nameTxt.setText(name);
        surnameEditTxt.setText(surname);
        fatherNameEditTxt.setText(fatherName);
        nationalIdEditTxt.setText(nationalId);
        dobEditTxt.setText(dob);
        genderEditTxt.setText(gender);


        //Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference();



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //check fields and add products
                if(!TextUtils.isEmpty(studentIdEditTxt.getText().toString())&&!TextUtils.isEmpty(nameTxt.getText().toString())&&!TextUtils.isEmpty(nationalIdEditTxt.getText().toString())){

                    //add product to database
                    Products items = new Products(studentIdEditTxt.getText().toString(), nameTxt.getText().toString(),surnameEditTxt.getText().toString(),fatherNameEditTxt.getText().toString(),nationalIdEditTxt.getText().toString(),dobEditTxt.getText().toString(),genderEditTxt.getText().toString());
                    databaseReference.child("students").child(studentIdEditTxt.getText().toString()).setValue(items);

                    Toast.makeText(EditStudentFirebaseActivity.this," Added",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(EditStudentFirebaseActivity.this,"Please Fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}