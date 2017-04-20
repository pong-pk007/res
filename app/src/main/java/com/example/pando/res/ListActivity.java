package com.example.pando.res;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pando.res.Data2.data2_mysql.data2_download;


public class ListActivity extends AppCompatActivity {

    private static String Server = new urlWebServer().name();
    private String district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i = getIntent();
        district = i.getExtras().getString("district");

        final RecyclerView rv = (RecyclerView) findViewById(R.id.rvlist);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swlist);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.blue,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);

        //setSize() Method Sets The Size Of Loader
        swipeRefreshLayout.setSize(SwipeRefreshLayout.MEASURED_SIZE_MASK);
        //Below Method Will set background color of Loader
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.yellow);

        new data2_download(ListActivity.this, Server+"osk/GET_JSON/getspdata.php?district="+district,rv,swipeRefreshLayout).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new data2_download(ListActivity.this, Server+"osk/GET_JSON/getspdata.php?district="+district,rv,swipeRefreshLayout).execute();
            }
        });

    }
}
