package com.example.pando.res.Data2.data2_mysql;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pando.res.Data2.data2_object.data2_object;
import com.example.pando.res.Data2.data2_ui.data2_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 20/4/2560.
 */

public class data2_parser extends AsyncTask<Void,Void,Boolean> {
    Context c;
    String jsonData;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<data2_object> Datas = new ArrayList<>();

    public data2_parser(Context c, String jsonData, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        swipeRefreshLayout.setRefreshing(false);

        if (isParsed){
            //bind
            rv.setAdapter(new data2_adapter(c, Datas));

        }else {

            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){

        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            Datas.clear();
            data2_object dataOBJ;

            for (int i=0; i<ja.length(); i++){

                jo=ja.getJSONObject(i);
                String data_id = jo.getString("data_sp_id");
                String owner = jo.getString("Name");
                String telephone = jo.getString("telephone");
                String image = jo.getString("image");
                String address = jo.getString("address");
                String lat = jo.getString("latitude");
                String longt = jo.getString("longitude");
                String area = jo.getString("area");
                String name_seeds = jo.getString("name_seeds");
                String price = jo.getString("price");
                String number = jo.getString("number");

                dataOBJ = new data2_object();

                dataOBJ.setData_id2(data_id);
                dataOBJ.setOwner2(owner);
                dataOBJ.setTelephone2(telephone);
                dataOBJ.setImage2(image);
                dataOBJ.setAddress2(address);
                dataOBJ.setLatitude2(lat);
                dataOBJ.setLongitude2(longt);
                dataOBJ.setArea2(area);
                dataOBJ.setName_seeds2(name_seeds);
                dataOBJ.setPrice2(price);
                dataOBJ.setNumber2(number);

                Datas.add(dataOBJ);

            }
            return true;

        }catch (JSONException e){
            e.printStackTrace();

        }
        return false;
    }
}
