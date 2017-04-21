package com.example.pando.res.NameList.name_ui;

import android.content.Context;
import android.widget.ImageView;

import com.example.pando.res.R;
import com.example.pando.res.urlWebServer;
import com.squareup.picasso.Picasso;

/**
 * Created by pongs_000 on 21/4/2560.
 */

public class NamePicasso {
    private static String Server = new urlWebServer().name();

    public static void downloadImage(Context c, String imageUrl, ImageView imgres){
        if (imageUrl != null && imageUrl.length() > 0){

            Picasso.with(c).load(Server+"osk/image/"+imageUrl).placeholder(R.drawable.placeholder_200x200).resize(200, 200).centerCrop().into(imgres);

        }else {
            Picasso.with(c).load(R.drawable.placeholder_200x200).into(imgres);
        }

    }
}
