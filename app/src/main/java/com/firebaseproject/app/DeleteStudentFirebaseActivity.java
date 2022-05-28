package com.firebaseproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteStudentFirebaseActivity extends AppCompatActivity {


    EditText id;
    Button  deletebtn;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student_firebase);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        // init Views
        id = findViewById(R.id.studentid);
        deletebtn= findViewById(R.id.deleteItemToTheDatabasebtn);




        //delete button
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

    }

    public void delete()
    {
        // get product code
        String studentid = id.getText().toString();

        //get current user email

        // check code is not null
        if(!TextUtils.isEmpty(studentid)){

            databaseReference.child("students").child(studentid).removeValue();

            id.setText("");
            Toast.makeText(DeleteStudentFirebaseActivity.this,"Student is Deleted",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(DeleteStudentFirebaseActivity.this,"Please id",Toast.LENGTH_SHORT).show();
        }
    }
}