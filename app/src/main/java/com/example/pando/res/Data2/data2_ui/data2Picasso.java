package com.example.pando.res.Data2.data2_ui;

import android.content.Context;
import android.widget.ImageView;

import com.example.pando.res.R;
import com.example.pando.res.urlWebServer;
import com.squareup.picasso.Picasso;

/**
 * Created by pongs_000 on 20/4/2560.
 */

public class data2Picasso {
    private static String Server = new urlWebServer().name();

    public static void downloadImage(Context c, String imageUrl, ImageView imgres){
        if (imageUrl != null && imageUrl.length() > 0){

            Picasso.with(c).load(Server+"osk/image/"+imageUrl).placeholder(R.drawable.placeholder_200x200).resize(400, 180).centerCrop().into(imgres);

        }else {
            Picasso.with(c).load(R.drawable.placeholder_200x200).into(imgres);
        }

    }
}