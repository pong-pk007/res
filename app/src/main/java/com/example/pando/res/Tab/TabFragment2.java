package com.example.pando.res.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pando.res.NameList.name_mysql.NameDown;
import com.example.pando.res.R;
import com.example.pando.res.urlWebServer;

public class TabFragment2 extends Fragment {

    private static String Server = new urlWebServer().name();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_name, container, false);


        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.rvlistname);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swnamelist);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.blue,       //This method will rotate
                R.color.red,        //colors given to it when
                R.color.yellow,     //loader continues to
                R.color.green);

        //setSize() Method Sets The Size Of Loader
        swipeRefreshLayout.setSize(SwipeRefreshLayout.MEASURED_SIZE_MASK);
        //Below Method Will set background color of Loader
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.yellow);

        new NameDown(getActivity(), Server+"osk/GET_JSON/getname.php",rv,swipeRefreshLayout).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new NameDown(getActivity(), Server+"osk/GET_JSON/getname.php",rv,swipeRefreshLayout).execute();
            }
        });

//        view.findViewById(R.id.tel1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String phonnum = "0972088271";
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                callIntent.setData(Uri.parse("tel:" + phonnum));
//
//                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                }
//                startActivity(callIntent);
//            }
//        });
//
//        view.findViewById(R.id.email1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = "aru.napat20@gmail.com";
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
////emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
//
//                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
//            }
//        });
//
//        view.findViewById(R.id.tel2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String phonnum = "0827510359";
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                callIntent.setData(Uri.parse("tel:" + phonnum));
//
//                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                }
//                startActivity(callIntent);
//            }
//        });
//
//        view.findViewById(R.id.email2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = "aru.napat20@gmail.com";
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
////emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
//
//                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
//            }
//        });
//
//
//        //btn zone
//
//        view.findViewById(R.id.call1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String phonnum = "0972088271";
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                callIntent.setData(Uri.parse("tel:" + phonnum));
//
//                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                }
//                startActivity(callIntent);
//            }
//        });
//
//        view.findViewById(R.id.mail1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = "jintana.chuelong@gmail.com";
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
////emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
//
//                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
//            }
//        });
//
//        view.findViewById(R.id.call2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String phonnum = "0827510359";
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                callIntent.setData(Uri.parse("tel:" + phonnum));
//
//                if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                }
//                startActivity(callIntent);
//            }
//        });
//
//        view.findViewById(R.id.mail2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = "jintana.chuelong@gmail.com";
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
////emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
//
//                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
//            }
//        });
//
//
//
//
    return view;

    }
}