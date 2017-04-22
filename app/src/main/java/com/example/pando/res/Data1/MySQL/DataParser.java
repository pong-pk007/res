package com.example.pando.res.Data1.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pando.res.Data1.Object.DataOBJ;
import com.example.pando.res.Data1.UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 28/9/2559.
 */

public class DataParser extends AsyncTask<Void,Void,Boolean> {
        Context c;
    String jsonData;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<DataOBJ> Datas = new ArrayList<>();

    public DataParser(Context c, String jsonData, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
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
            rv.setAdapter(new CustomAdapter(c,Datas));

        }else {

            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){

        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            Datas.clear();
            DataOBJ dataOBJ;

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

                dataOBJ = new DataOBJ();

                dataOBJ.setData_id(data_id);
                dataOBJ.setOwner(owner);
                dataOBJ.setTelephone(telephone);
                dataOBJ.setImage(image);
                dataOBJ.setAddress(address);
                dataOBJ.setLatitude(lat);
                dataOBJ.setLongitude(longt);
                dataOBJ.setArea(area);
                dataOBJ.setName_seeds(name_seeds);
                dataOBJ.setPrice(price);
                dataOBJ.setNumber(number);

                Datas.add(dataOBJ);

            }
            return true;

        }catch (JSONException e){
        e.printStackTrace();

        }
        return false;
    }
}
