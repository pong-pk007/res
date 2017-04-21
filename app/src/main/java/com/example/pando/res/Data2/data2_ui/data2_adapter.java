package com.example.pando.res.Data2.data2_ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pando.res.Data1.UI.ItemClickListener;
import com.example.pando.res.Data1.UI.PicassoClient;
import com.example.pando.res.Data2.data2_object.data2_object;
import com.example.pando.res.DetailActivity;
import com.example.pando.res.R;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 20/4/2560.
 */

public class data2_adapter extends RecyclerView.Adapter<data2_hoder> {

    Context c;
    ArrayList<data2_object> Datas;

    public data2_adapter(Context c, ArrayList<data2_object> Datas){
        this.c = c;
        this.Datas = Datas;
    }

    @Override
    public data2_hoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.itemlist,parent,false);
        return new data2_hoder(v);
    }


    @Override
    public void onBindViewHolder(data2_hoder holder, int position) {

        final data2_object DT = Datas.get(position);

        holder.name.setText(DT.getOwner2());
        holder.address.setText(DT.getAddress2());
        data2Picasso.downloadImage(c,DT.getImage2(),holder.img);


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                OpenDetailRestaurantActivity(DT.getOwner2(),DT.getTelephone2(),DT.getImage2(),DT.getLatitude2(),DT.getLongitude2(),DT.getAddress2(),DT.getArea2(),DT.getName_seeds2(),DT.getPrice2(),DT.getNumber2());
            }
        });
    }


    @Override
    public int getItemCount() {
        return Datas.size();
    }



//    public void setFilter (ArrayList<spData> restaurantDatas){
//        restaurantDatas = new ArrayList<>();
//        restaurantDatas.addAll(restaurantDatas);
//        notifyDataSetChanged();
//
//    }

//    int sp_id;
//    String place,owner,telephone,image,address,lat,longt;

    private void OpenDetailRestaurantActivity( String owner,String telephone,String image,String latitude,String longitude,String address,String area, String nameseed, String price , String number){

        Intent i = new Intent(c, DetailActivity.class);

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