package com.example.pando.res.Data1.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pando.res.R;


/**
 * Created by pongs_000 on 28/9/2559.
 */

public class MyViewHoder extends RecyclerView.ViewHolder implements  View.OnClickListener {


    TextView name;
    TextView address;
    ImageView img;
    ItemClickListener itemClickListener;

    public MyViewHoder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        address = (TextView) itemView.findViewById(R.id.address);
        img = (ImageView) itemView.findViewById(R.id.img);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick();
    }


    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

    }
}
