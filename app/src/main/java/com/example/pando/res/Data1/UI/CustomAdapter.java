package com.example.pando.res.Data1.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pando.res.DetailActivity;
import com.example.pando.res.Data1.Object.DataOBJ;
import com.example.pando.res.R;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 28/9/2559.
 */

public class CustomAdapter extends RecyclerView.Adapter<MyViewHoder> {

    Context c;
    ArrayList<DataOBJ> Datas;

    public CustomAdapter(Context c, ArrayList<DataOBJ> Datas){
        this.c = c;
        this.Datas = Datas;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.itemrv,parent,false);
        return new MyViewHoder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {

        final DataOBJ DT = Datas.get(position);

        holder.name.setText(DT.getOwner());
        holder.address.setText(DT.getAddress());
        PicassoClient.downloadImage(c,DT.getImage(),holder.img);



        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                OpenDetailRestaurantActivity(DT.getOwner(),DT.getTelephone(),DT.getImage(),DT.getLatitude(),DT.getLongitude(),DT.getAddress(),DT.getArea(),DT.getName_seeds(),DT.getPrice(),DT.getNumber(),DT.getData_id());
            }
        });
    }


    @Override
    public int getItemCount() {
        return 3;
    }



//    public void setFilter (ArrayList<spData> restaurantDatas){
//        restaurantDatas = new ArrayList<>();
//        restaurantDatas.addAll(restaurantDatas);
//        notifyDataSetChanged();
//
//    }

//    int sp_id;
//    String place,owner,telephone,image,address,lat,longt;

    private void OpenDetailRestaurantActivity(String owner,String telephone,String image,String latitude,String longitude,String address,String area,String nameseed, String price , String number, String data_id){

        Intent i = new Intent(c, DetailActivity.class);

        i.putExtra("data_id",data_id);
        i.putExtra("ADDRESS_KEY",address);
        i.putExtra("OWNER_KEY",owner);
        i.putExtra("PHONE_KEY",telephone);
        i.putExtra("IMAGE_KEY",image);
        i.putExtra("LAT_KEY" ,latitude);
        i.putExtra("LONGT_KEY", longitude);
        i.putExtra("AREA_KEY",area);
        i.putExtra("nameseed",nameseed);
        i.putExtra("price",price);
        i.putExtra("number",number);

        c.startActivity(i);
    }

}
