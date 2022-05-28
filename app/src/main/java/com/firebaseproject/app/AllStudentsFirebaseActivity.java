package com.firebaseproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AllStudentsFirebaseActivity extends AppCompatActivity {
    public static EditText resultsearcheview;
    private FirebaseAuth firebaseAuth;
    ImageView searchbtn;
    RecyclerView mrecyclerview;
    DatabaseReference mdatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students_firebase);

        //Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();



        //database reference
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("students");

        //init View
        resultsearcheview = findViewById(R.id.searchfield);
        searchbtn = findViewById(R.id.searchbtnn);
        mrecyclerview = findViewById(R.id.recyclerViews);
        //add layout manager to recyclerview
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(manager);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));





        //search button in search bar to search specific product code.
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchtext = resultsearcheview.getText().toString();
                firebasesearch(searchtext);
            }
        });

        getAllProducts();
    }

    public void getAllProducts(){

        //query
        Query firebaseSearchQuery = mdatabaseReference.orderByChild("studentId");



        //firebase recyclerview
        FirebaseRecyclerOptions<StudentsFirebase> options =
                new FirebaseRecyclerOptions.Builder<StudentsFirebase>()
                        .setQuery(firebaseSearchQuery, StudentsFirebase.class)
                        .build();

        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<StudentsFirebase, UsersViewHolder>(options) {
            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                // attach layout to RecyclerView
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);

                return new UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(UsersViewHolder holder, int position, StudentsFirebase model) {

                holder.nameTxt.setText(model.getName());
                holder.surnameTxt.setText(model.getSurname());
                holder.fatherNameTxt.setText(model.getFatherName());
                holder.nationalIdTxt.setText(model.getNationalId());
                holder.dobTxt.setText(model.getDob());
                holder.genderTxt.setText(model.getGender());

                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i=new Intent(AllStudentsFirebaseActivity.this, ViewStudentFirebaseActivity.class);

                        //LOAD DATA
                        i.putExtra("studentId",model.getStudentId());
                        i.putExtra("name",model.getName());
                        i.putExtra("surname",model.getSurname());
                        i.putExtra("fatherName",model.getFatherName());
                        i.putExtra("nationalId",model.getNationalId());
                        i.putExtra("dob",model.getDob());
                        i.putExtra("gender",model.getGender());

                        //START ACTIVITY
                        startActivity(i);


                    }
                });

            }
        };


        firebaseRecyclerAdapter.startListening();
        // add adapter to recyclerview

        mrecyclerview.setAdapter(firebaseRecyclerAdapter);
    }

    public void firebasesearch(String searchtext){
        //query to search product by bar code
        Query firebaseSearchQuery = mdatabaseReference.orderByChild("studentId").startAt(searchtext).endAt(searchtext+"\uf8ff");



        FirebaseRecyclerOptions<StudentsFirebase> options =
                new FirebaseRecyclerOptions.Builder<StudentsFirebase>()
                        .setQuery(firebaseSearchQuery, StudentsFirebase.class)
                        .build();

        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<StudentsFirebase, UsersViewHolder>(options) {
            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);

                return new UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(UsersViewHolder holder, int position, StudentsFirebase model) {


                holder.nameTxt.setText(model.getName());
                holder.surnameTxt.setText(model.getSurname());
                holder.fatherNameTxt.setText(model.getFatherName());
                holder.nationalIdTxt.setText(model.getNationalId());
                holder.dobTxt.setText(model.getDob());
                holder.genderTxt.setText(model.getGender());

                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(AllStudentsFirebaseActivity.this, ViewStudentFirebaseActivity.class);

                        //LOAD DATA
                        i.putExtra("studentId",model.getStudentId());
                        i.putExtra("name",model.getName());
                        i.putExtra("surname",model.getSurname());
                        i.putExtra("fatherName",model.getFatherName());
                        i.putExtra("nationalId",model.getNationalId());
                        i.putExtra("dob",model.getDob());
                        i.putExtra("gender",model.getGender());

                        //START ACTIVITY
                        startActivity(i);

                    }
                });

            }
        };


        firebaseRecyclerAdapter.startListening();
        // add adapter to recyclerview
        mrecyclerview.setAdapter(firebaseRecyclerAdapter);
    }


    //View Holder class
    public static class UsersViewHolder extends RecyclerView.ViewHolder{
        View mView;
        CardView card;
        TextView nameTxt, surnameTxt,fatherNameTxt,nationalIdTxt,dobTxt,genderTxt;

            public UsersViewHolder(View itemView){
                super(itemView);

                mView =itemView;
                card=  itemView.findViewById(R.id.card);

                nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
                surnameTxt = (TextView) itemView.findViewById(R.id.surnameTxt);
                fatherNameTxt = (TextView) itemView.findViewById(R.id.fatherNameTxt);
                nationalIdTxt = (TextView) itemView.findViewById(R.id.nationalIdTxt);
                dobTxt = (TextView) itemView.findViewById(R.id.dobTxt);
                genderTxt = (TextView) itemView.findViewById(R.id.genderTxt);


            }



    }
}
