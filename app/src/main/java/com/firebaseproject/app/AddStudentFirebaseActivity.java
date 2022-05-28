package com.firebaseproject.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class AddStudentFirebaseActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    Button  additemtodatabase;
    DatabaseReference databaseReference;
    ProgressDialog pd;
    Context context;
    FirebaseStorage storage;
    FirebaseAuth auth;


    EditText nameTxt,studentIdEditTxt,surnameEditTxt,fatherNameEditTxt,nationalIdEditTxt,dobEditTxt,genderEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_firebase);
        firebaseAuth = FirebaseAuth.getInstance();


        //Top action bar
        ActionBar a = getSupportActionBar();
        a.setTitle("Add Product");
        a.setDisplayHomeAsUpEnabled(true);

        //Firebase Storage referenece initialization
        FirebaseApp.initializeApp(context);


        //Firebase authentication
        auth = FirebaseAuth.getInstance();
        //Progress bar
        pd = new ProgressDialog(this);
        pd.setTitle("Saving Data");
        pd.setMessage("Please Wait...");

        //Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();


        studentIdEditTxt= (EditText) findViewById(R.id.studentIdEditTxt);

        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        surnameEditTxt= (EditText) findViewById(R.id.surnameEditTxt);
        fatherNameEditTxt= (EditText) findViewById(R.id.fatherNameEditTxt);
        nationalIdEditTxt= (EditText) findViewById(R.id.nationalIdEditTxt);
        dobEditTxt= (EditText) findViewById(R.id.dobEditTxt);
        genderEditTxt= (EditText) findViewById(R.id.genderEditTxt);

        //init views
        additemtodatabase = findViewById(R.id.additembuttontodatabase);





        //add product to database
        additemtodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
    }




    public  void addProduct(){
        // all fields must be filled
        if(!TextUtils.isEmpty(studentIdEditTxt.getText().toString())&&!TextUtils.isEmpty(nameTxt.getText().toString())&&!TextUtils.isEmpty(nationalIdEditTxt.getText().toString())){


            //upload product to database
            Products items = new Products(studentIdEditTxt.getText().toString(), nameTxt.getText().toString(),surnameEditTxt.getText().toString(),fatherNameEditTxt.getText().toString(),nationalIdEditTxt.getText().toString(),dobEditTxt.getText().toString(),genderEditTxt.getText().toString());
            databaseReference.child("students").child(studentIdEditTxt.getText().toString()).setValue(items);
            studentIdEditTxt.setText("");
            nameTxt.setText("");
            surnameEditTxt.setText("");
            fatherNameEditTxt.setText("");
            nationalIdEditTxt.setText("");
            dobEditTxt.setText("");
            genderEditTxt.setText("");

            Toast.makeText(AddStudentFirebaseActivity.this,"Added",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(AddStudentFirebaseActivity.this,"Please Fill all the fields",Toast.LENGTH_SHORT).show();
        }
    }
















    // logout



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu
        return true;
    }


}
