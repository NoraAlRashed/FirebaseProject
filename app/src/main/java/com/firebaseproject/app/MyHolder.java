package com.firebaseproject.app;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt, surnameTxt,fatherNameTxt,nationalIdTxt,dobTxt,genderTxt;
    ItemClickListener itemClickListener;


    public MyHolder(View itemView) {
        super(itemView);

        //ASSIGN
        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        surnameTxt = (TextView) itemView.findViewById(R.id.surnameTxt);
        fatherNameTxt = (TextView) itemView.findViewById(R.id.fatherNameTxt);
        nationalIdTxt = (TextView) itemView.findViewById(R.id.nationalIdTxt);
        dobTxt = (TextView) itemView.findViewById(R.id.dobTxt);
        genderTxt = (TextView) itemView.findViewById(R.id.genderTxt);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}
