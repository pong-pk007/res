package com.example.pando.res.Data2.data2_mysql;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pando.res.Data1.MySQL.Connector;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by pongs_000 on 20/4/2560.
 */

public class data2_download extends AsyncTask<Void, Void, String> {
    Context c;
    String urlAddress;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;


    public data2_download(Context c, String urlAddress, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.rv = rv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }


    @Override
    protected void onPreExecute() {
        if (!swipeRefreshLayout.isRefreshing()){

            swipeRefreshLayout.setRefreshing(true);
        }

        super.onPreExecute();
    }



    @Override
    protected String doInBackground(Void... params) {
        return this .dowloadData();
    }



    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        if (jsonData==null){

            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(c, "Unsuccessful ,Unable To retrieve", Toast.LENGTH_SHORT).show();
        }else {
            new data2_parser(c,jsonData,rv,swipeRefreshLayout).execute();

        }
    }

    private String dowloadData(){

        HttpURLConnection con = Connector.connect(urlAddress);
        if (con==null){
            return null;
        }
        try{
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = br.readLine()) != null){

                jsonData.append(line+"\n");
            }

            br.close();
            is.close();
            return jsonData.toString();

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}

