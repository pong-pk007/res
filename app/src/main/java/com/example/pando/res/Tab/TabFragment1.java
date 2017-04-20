package com.example.pando.res.Tab;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pando.res.ListActivity;
import com.example.pando.res.Data1.MySQL.Downloader;
import com.example.pando.res.R;
import com.example.pando.res.urlWebServer;


public class TabFragment1 extends Fragment {

    private static String Server = new urlWebServer().name();
    private TextView more;
    private ListView listView = null;
    private TextView txt;
    private AlertDialog.Builder builder;
    AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);

        listView = new ListView(getActivity());


        String[] item = {"กันทรารมย์","ยางชุม","วังหิน"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.dialog_item, R.id.txtitem, item);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup)view;
                txt = (TextView) vg.findViewById(R.id.txtitem);
                Toast.makeText(getActivity(),"เลือกอำเภอ "+txt.getText().toString(),Toast.LENGTH_LONG).show();
                dialog.dismiss();
                Intent i = new Intent(getActivity(), ListActivity.class);
                i.putExtra("district",txt.getText().toString());
                startActivity(i);
            }
        });


        more = (TextView) rootView.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogListView(rootView);
            }
        });

        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rvdata);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(llm);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.sw_data);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.blue,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);

        //setSize() Method Sets The Size Of Loader
        swipeRefreshLayout.setSize(SwipeRefreshLayout.MEASURED_SIZE_MASK);
        //Below Method Will set background color of Loader
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.yellow);

        new Downloader(getActivity(), Server+"osk/GET_JSON/getspdata2.php",rv,swipeRefreshLayout).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Downloader(getActivity(), Server+"osk/GET_JSON/getspdata2.php",rv,swipeRefreshLayout).execute();
            }
        });

        return rootView;

    }

    public void showDialogListView(View view){

        builder=new AlertDialog.Builder(getActivity());

        builder.setCancelable(true);
        builder.setTitle("เลือกพื้นที่");

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setView(listView);
        dialog=builder.create();
        dialog.show();

    }
}