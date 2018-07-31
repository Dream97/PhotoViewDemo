package com.example.dream.photoviewdemo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoader {
    public static void display(Context context, ImageView imageView,String url){
//        Glide.with(context)
//                .load(url)
//                .placeholder(R.drawable.loading_spinner)
//                .into(imageView);
        GlideApp
                .with(context)
                .load(url)
                .placeholder(R.drawable.timg)
                .into(imageView);
    }

}
