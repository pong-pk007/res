package com.example.pando.res.NameList.name_ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pando.res.Data1.UI.ItemClickListener;
import com.example.pando.res.Data1.UI.MyViewHoder;
import com.example.pando.res.R;

/**
 * Created by pongs_000 on 21/4/2560.
 */

public class NameHoder extends RecyclerView.ViewHolder implements  View.OnClickListener {


    TextView name_seeds;
    ImageView img;
    ItemClickListener itemClickListener;

    public NameHoder(View itemView) {
        super(itemView);




        name_seeds = (TextView)itemView.findViewById(R.id.tvlistname);
        img = (ImageView) itemView.findViewById(R.id.imagenamelist);
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

