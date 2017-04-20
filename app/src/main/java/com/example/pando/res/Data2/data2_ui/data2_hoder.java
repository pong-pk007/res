package com.example.pando.res.Data2.data2_ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pando.res.Data1.UI.ItemClickListener;
import com.example.pando.res.R;

/**
 * Created by pongs_000 on 20/4/2560.
 */

public class data2_hoder extends RecyclerView.ViewHolder implements  View.OnClickListener {


    TextView name;
    TextView address;
    ImageView img;
    ItemClickListener itemClickListener;

    public data2_hoder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.namelist);
        address = (TextView) itemView.findViewById(R.id.addresslist);
        img = (ImageView) itemView.findViewById(R.id.imglist);
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
