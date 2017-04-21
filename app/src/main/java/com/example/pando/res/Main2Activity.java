package com.example.pando.res;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pando.res.Data1.UI.PicassoClient;

public class Main2Activity extends AppCompatActivity {

    TextView tvname,tvdes;
    ImageView ivdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        tvname = (TextView) findViewById(R.id.namedetail);
        tvdes = (TextView) findViewById(R.id.desdetail);
        ivdetail = (ImageView) findViewById(R.id.imgnamedetail);

        String name = i.getExtras().getString("name");
        String des = i.getExtras().getString("des");
        String img = i.getExtras().getString("img");

        tvname.setText("พันธ์ู "+name);
        tvdes.setText("คำอธิบาย \n");
        tvdes.setText(des);
        PicassoClient.downloadImage(this,img,ivdetail);


    }
}
