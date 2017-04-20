package com.example.pando.res;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pando.res.Data1.UI.PicassoClient;

public class DetailActivity extends AppCompatActivity {

    TextView tvowner,tvtelephone,tvlatlong,tvname_seeds,tvprice,tvaddress, tvarea;
    ImageView imageView;
    ImageButton googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        final String owner,telephone,image,latitude,longitude,address,area;

        final Intent i = this.getIntent();

        owner = i.getExtras().getString("OWNER_KEY");
        telephone = i.getExtras().getString("PHONE_KEY");
        image  = i.getExtras().getString("IMAGE_KEY");
        latitude  = i.getExtras().getString("LAT_KEY");
        longitude = i.getExtras().getString("LONGT_KEY");
        address = i.getExtras().getString("ADDRESS_KEY");
        area = i.getExtras().getString("AREA_KEY");

        tvowner = (TextView) findViewById(R.id.tvname);
        tvtelephone = (TextView) findViewById(R.id.tvphone);
//        tvlatlong = (TextView) findViewById(R.id.tvlatlongt);
        tvname_seeds = (TextView) findViewById(R.id.tvseeds);
        tvprice = (TextView) findViewById(R.id.tvprice);
        tvaddress = (TextView) findViewById(R.id.tvaddress);
        imageView = (ImageView) findViewById(R.id.imgdata);
        googleMap = (ImageButton) findViewById(R.id.googleMap);
        tvarea = (TextView) findViewById(R.id.tvarea);

        tvowner.setText("ชื่อ : "+owner);
        tvtelephone.setText("โทรศัพท์ : "+telephone);
//        tvlatlong.setText("ไปยังตำแหน่ง : "+latitude+" ,"+longitude);
        tvaddress.setText("ที่อยู่ : "+ address);
        tvarea.setText(area);
        PicassoClient.downloadImage(this,image,imageView);

        googleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" +"บ้าน "+ owner + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

    }
}
