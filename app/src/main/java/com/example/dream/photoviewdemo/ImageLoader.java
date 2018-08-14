package com.example.dream.photoviewdemo;

import android.content.Context;
import android.widget.ImageView;


public class ImageLoader {
    public static void display(Context context, ImageView imageView,String url){
        GlideApp
                .with(context)
                .load(url)
                .placeholder(R.drawable.timg)
                .into(imageView);
    }

}
