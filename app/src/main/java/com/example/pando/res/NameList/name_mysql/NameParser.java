package com.example.pando.res.NameList.name_mysql;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pando.res.Data1.UI.CustomAdapter;
import com.example.pando.res.NameList.name_object.NameObject;
import com.example.pando.res.NameList.name_ui.NameAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pongs_000 on 21/4/2560.
 */

public class NameParser extends AsyncTask<Void,Void,Boolean>{
    Context c;
    String jsonData;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<NameObject> Datas = new ArrayList<>();

    public NameParser(Context c, String jsonData, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
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
            rv.setAdapter(new NameAdapter(c,Datas));

        }else {

            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){

        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            Datas.clear();
            NameObject dataOBJ;

            for (int i=0; i<ja.length(); i++){

                jo=ja.getJSONObject(i);
                String name_seeds = jo.getString("name_seeds");
                String image = jo.getString("image");
                String description = jo.getString("description");

                dataOBJ = new NameObject();
                dataOBJ.setName_seeds(name_seeds);
                dataOBJ.setImageName(image);
                dataOBJ.setDescription(description);
                Datas.add(dataOBJ);
            }
            return true;

        }catch (JSONException e){
            e.printStackTrace();

        }
        return false;
    }
}
