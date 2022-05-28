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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    FirebaseAuth auth;


    private CardView addStudentFirebase, addStudentSqlite, viewStudentFirebase, viewStudentSqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        //init Views
        addStudentFirebase = (CardView)findViewById(R.id.addStudentFirebase);
        addStudentSqlite = (CardView) findViewById(R.id.addStudentSqlite);
        viewStudentFirebase = (CardView) findViewById(R.id.viewStudentFirebase);
        viewStudentSqlite = (CardView) findViewById(R.id.viewStudentSqlite);

        //add click listener
        addStudentFirebase.setOnClickListener(this);
        addStudentSqlite.setOnClickListener(this);
        viewStudentFirebase.setOnClickListener(this);
        viewStudentSqlite.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            // on clicking buttons move to respective screen
            case R.id.addStudentFirebase:  i = new Intent(this, AddProductActivity.class); startActivity(i); break;
            case R.id.addStudentSqlite:  i = new Intent(this, DeleteProductsActivity.class);startActivity(i); break;
            case R.id.viewStudentFirebase:  i = new Intent(this, AllProductsActivity.class);startActivity(i); break;
            case R.id.viewStudentSqlite:  i = new Intent(this, SQliteViewAllStudentsActivity.class);startActivity(i); break;
            default: break;
        }
    }





    // logout
    private void Logout()
    {
        auth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        Toast.makeText(MainActivity.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // on clicking logout. Logout the user

        switch (item.getItemId()){
            case  R.id.logoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
