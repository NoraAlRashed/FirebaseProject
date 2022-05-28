package com.firebaseproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ViewStudentFirebaseActivity extends AppCompatActivity {

    ImageView image;
    Button edit;
    
    TextView studentIdTxt,nameTxt, surnameTxt,fatherNameTxt,nationalIdTxt,dobTxt,genderTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_firebase);


        //Top action bar

        ActionBar a = getSupportActionBar();
        a.setTitle("Product");
        a.setDisplayHomeAsUpEnabled(true);


        //init Views
        edit =findViewById(R.id.edit);

        studentIdTxt=  findViewById(R.id.studentIdTxt);

        nameTxt=  findViewById(R.id.nameTxt);
        surnameTxt =  findViewById(R.id.surnameTxt);
        fatherNameTxt =  findViewById(R.id.fatherNameTxt);
        nationalIdTxt =  findViewById(R.id.nationalIdTxt);
        dobTxt =  findViewById(R.id.dobTxt);
        genderTxt =  findViewById(R.id.genderTxt);

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
        studentIdTxt.setText(studentId);
        nameTxt.setText(name);
        surnameTxt.setText(surname);
        fatherNameTxt.setText(fatherName);
        nationalIdTxt.setText(nationalId);
        dobTxt.setText(dob);
        genderTxt.setText(gender);


        //edit button
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent to move to next activity
                Intent intent = new Intent(getApplicationContext(), EditStudentFirebaseActivity.class);
                intent.putExtra("studentId",studentId);
                intent.putExtra("name",name);
                intent.putExtra("surname",surname);
                intent.putExtra("fatherName",fatherName);
                intent.putExtra("nationalId",nationalId);
                intent.putExtra("dob",dob);
                intent.putExtra("gender",gender);
                startActivity(intent);
                finish();

            }
        });



    }
}