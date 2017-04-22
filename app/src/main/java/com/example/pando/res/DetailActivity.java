package com.example.pando.res;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pando.res.Data1.UI.PicassoClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    TextView tvowner,tvtelephone,tvlatlong,tvname_seeds,tvprice,tvaddress, tvarea,tvnumber;
    ImageView imageView;
//    ImageButton googleMap;

    private GoogleMap mMap;
    private UiSettings mUiSettings;
    private Marker marker;


    private Double Latitude = 0.00;
    private Double Longitude = 0.00;
    private ArrayList<String> locationLatLong;
    private String name, length;
    private ArrayList<HashMap<String, String>> location = new ArrayList<HashMap<String, String>>();

    private String owner,telephone,image,latitude,longitude,address,area,nameseed,price,number,data_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //*** Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);


        String nameType = null;

        final Intent i = this.getIntent();
        data_id = i.getExtras().getString("data_id");
        owner = i.getExtras().getString("OWNER_KEY");
        telephone = i.getExtras().getString("PHONE_KEY");
        image  = i.getExtras().getString("IMAGE_KEY");
        latitude  = i.getExtras().getString("LAT_KEY");
        longitude = i.getExtras().getString("LONGT_KEY");
        address = i.getExtras().getString("ADDRESS_KEY");
        area = i.getExtras().getString("AREA_KEY");
        nameseed = i.getExtras().getString("nameseed");
        price = i.getExtras().getString("price");
        number = i.getExtras().getString("number");



        tvowner = (TextView) findViewById(R.id.tvname);
        tvtelephone = (TextView) findViewById(R.id.tvphone);
//        tvlatlong = (TextView) findViewById(R.id.tvlatlongt);
        tvname_seeds = (TextView) findViewById(R.id.tvseeds);
        tvprice = (TextView) findViewById(R.id.tvprice);
        tvaddress = (TextView) findViewById(R.id.tvaddress);
        imageView = (ImageView) findViewById(R.id.imgdata);
//        googleMap = (ImageButton) findViewById(R.id.googleMap);
        tvarea = (TextView) findViewById(R.id.tvarea);
        tvnumber = (TextView) findViewById(R.id.tvnumber);


        if (nameseed.equals("3")){
            tvname_seeds.setText("พันธู์หอม :พื้นบ้าน");
        }else if(nameseed.equals("4")){
            tvname_seeds.setText("พันธู์หอม :ลับแล");
        }else if(nameseed.equals("5")){
            tvname_seeds.setText("พันธู์หอม :น้ำปาด");
        }else {
            tvname_seeds.setText("พันธู์หอม :"+nameseed);

        }


        tvowner.setText("ชื่อ : "+owner);
        tvtelephone.setText("โทรศัพท์ : "+telephone);
//        tvlatlong.setText("ไปยังตำแหน่ง : "+latitude+" ,"+longitude);
        tvaddress.setText("ที่อยู่ : "+ address);
        tvarea.setText("จำนวนไร่ : "+area);
        tvprice.setText("ราคา : "+price);
        tvnumber.setText("ปริมาณหอม : "+number);

        PicassoClient.downloadImage(this,image,imageView);

//        googleMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" +"บ้าน "+ owner + ")";
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
//                intent.putExtra("latitude",latitude);
//                intent.putExtra("longitude",longitude);
//                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//                startActivity(intent);
//            }
//        });


        String server = "http://it56.itsisaket.com/osk/GET_JSON/getnearsp.php?spid="+data_id;
        new getNeaby().execute(server);

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

                String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" +"บ้าน "+ owner + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();

        LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        marker = mMap.addMarker(new MarkerOptions().position(latLng).title("บ้านเลขที่ "+address));
//        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_pin));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        marker.showInfoWindow();
        mMap.setOnInfoWindowClickListener(this);

        for (int i = 0;i < location.size(); i++){
            Latitude = Double.parseDouble(location.get(i).get("latitude").toString());
            Longitude = Double.parseDouble(location.get(i).get("longitude").toString());
            name = location.get(i).get("name").toString();
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(Latitude, Longitude)).title(name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }


    }



    private class getNeaby extends AsyncTask<String, Void, Void> {

        private ProgressDialog pd;
        boolean con = false;

        @Override
        protected void onPreExecute() {

            pd = new ProgressDialog(DetailActivity.this);
            pd.setTitle("กำลังทำงาน");
            pd.setMessage("โหลดข้อมูล...");
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            String jString;
            HashMap<String, String> map;

            try{
                jString = getJsonFromUrl(params[0]);


                if (jString != null) {

                    JSONArray jArray = new JSONArray(jString);
                    for (int i=0; i < jArray.length() ; i++ ) {
                        JSONObject jObj = jArray.getJSONObject(i);
                        map = new HashMap<String, String>();
                        map.put("place_id", jObj.getString("place_id"));
                        map.put("name", jObj.getString("name"));
                        map.put("latitude", jObj.getString("latitude"));
                        map.put("longitude", jObj.getString("longitude"));
                        location.add(map);

                    }// for
                    con = true;
                }// if


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {

//
//            for (int i = 0;i < location.size(); i++){
//                Latitude = Double.parseDouble(location.get(i).get("latitude").toString());
//                Longitude = Double.parseDouble(location.get(i).get("longitude").toString());
//                name = location.get(i).get("name").toString();
//                length = location.get(i).get("length").toString();
//                String[] latLng = {Latitude + "," + Longitude};
//
//            }
            pd.dismiss();

            super.onPostExecute(aVoid);
        }
    }

    private String getJsonFromUrl(String strUrl) throws IOException {

        URL url = new URL(strUrl);
        try {
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            httpCon.setConnectTimeout(6*1000);
            httpCon.connect();

            int responseCode = httpCon.getResponseCode();
            //Log.d(TAG, "The response is: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK){
                //Log.d(TAG, " size: " + httpCon.getContentLength());

                InputStream ins = httpCon.getInputStream();
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(ins,"UTF-8"));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append("\n");
                    //Log.d(TAG, line);
                }
                rd.close();
                return response.toString();
            }

        } catch (Exception ex) {
            //Log.d(TAG, "Problem reading " +  ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
        return null;
    }
}
