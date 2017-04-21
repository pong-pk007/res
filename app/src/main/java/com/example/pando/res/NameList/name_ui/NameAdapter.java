package com.example.pando.res.NameList.name_ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pando.res.Data1.UI.ItemClickListener;
import com.example.pando.res.DetailActivity;
import com.example.pando.res.Main2Activity;
import com.example.pando.res.NameList.name_object.NameObject;
import com.example.pando.res.R;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 21/4/2560.
 */

public class NameAdapter extends RecyclerView.Adapter<NameHoder>{
    Context c;
    ArrayList<NameObject> Datas;

    public NameAdapter (Context c,ArrayList<NameObject> Datas){

        this.c = c;
        this.Datas = Datas;


    }


    @Override
    public NameHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.itemlistname, parent, false);
        return new NameHoder(v);
    }

    @Override
    public void onBindViewHolder(NameHoder holder, int position) {
            final NameObject datas = Datas.get(position);
        holder.name_seeds.setText("พันธ์ู "+datas.getName_seeds());
            NamePicasso.downloadImage(c,datas.getImageName(),holder.img);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                OpenDetailRestaurantActivity(datas.getName_seeds(),datas.getDescription(),datas.getImageName());
            }
        });


    }

    @Override
    public int getItemCount() {
        return Datas.size();
    }


    private void OpenDetailRestaurantActivity(String name, String des, String img){

        Intent i = new Intent(c, Main2Activity.class);

        i.putExtra("name",name);
        i.putExtra("des",des);
        i.putExtra("img",img);

        c.startActivity(i);
    }






}
