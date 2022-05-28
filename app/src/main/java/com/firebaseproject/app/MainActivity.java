package com.firebaseproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    FirebaseAuth auth;


    private CardView addStudentFirebase, addStudentSqlite, viewStudentFirebase, viewStudentSqlite,deleteStudentFirebase,weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //init Views
        addStudentFirebase = (CardView)findViewById(R.id.addStudentFirebase);
        addStudentSqlite = (CardView) findViewById(R.id.addStudentSqlite);
        viewStudentFirebase = (CardView) findViewById(R.id.viewStudentFirebase);
        viewStudentSqlite = (CardView) findViewById(R.id.viewStudentSqlite);
        deleteStudentFirebase = (CardView) findViewById(R.id.deleteStudentFirebase);
        weather = (CardView) findViewById(R.id.weather);

        //add click listener
        addStudentFirebase.setOnClickListener(this);
        addStudentSqlite.setOnClickListener(this);
        viewStudentFirebase.setOnClickListener(this);
        viewStudentSqlite.setOnClickListener(this);
        deleteStudentFirebase.setOnClickListener(this);
        weather.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            // on clicking buttons move to respective screen
            case R.id.addStudentFirebase:  i = new Intent(this, AddStudentFirebaseActivity.class); startActivity(i); break;
            case R.id.addStudentSqlite:  i = new Intent(this, AddStudentSQActivity.class);startActivity(i); break;
            case R.id.viewStudentFirebase:  i = new Intent(this, AllStudentsFirebaseActivity.class);startActivity(i); break;
            case R.id.viewStudentSqlite:  i = new Intent(this, SQliteViewAllStudentsActivity.class);startActivity(i); break;
            case R.id.deleteStudentFirebase:  i = new Intent(this, DeleteStudentFirebaseActivity.class);startActivity(i); break;
            case R.id.weather:  i = new Intent(this, WeatherActivity.class);startActivity(i); break;

            default: break;
        }
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu

        return true;
    }


}
