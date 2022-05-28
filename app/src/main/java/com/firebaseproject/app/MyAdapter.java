package com.firebaseproject.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Student> students;

    public MyAdapter(Context ctx,ArrayList<Student> students)
    {
        //ASSIGN THEM LOCALLY
        this.c=ctx;
        this.students =students;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJ FROM XML
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);

        //holder
        MyHolder holder=new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.nameTxt.setText(students.get(position).getName());
        holder.surnameTxt.setText(students.get(position).getSurname());
        holder.fatherNameTxt.setText(students.get(position).getFatherName());
        holder.nationalIdTxt.setText(students.get(position).getNationalId());
        holder.dobTxt.setText(students.get(position).getDob());
        holder.genderTxt.setText(students.get(position).getGender());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY

                //PASS DATA

                //CREATE INTENT
                Intent i=new Intent(c, DetailActivity.class);

                //LOAD DATA
                i.putExtra("studentId", students.get(pos).getStudentId());
                i.putExtra("name", students.get(pos).getName());
                i.putExtra("surname", students.get(pos).getSurname());
                i.putExtra("fatherName", students.get(pos).getFatherName());
                i.putExtra("nationalId", students.get(pos).getNationalId());
                i.putExtra("dob", students.get(pos).getDob());
                i.putExtra("gender", students.get(pos).getGender());

                i.putExtra("ID", students.get(pos).getId());

                //START ACTIVITY
                c.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
