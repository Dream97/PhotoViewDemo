package com.example.dream.photoviewdemo;


import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;


public class MainActivity extends AppCompatActivity {

    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        photoView =  findViewById(R.id.photoview);
        ImageLoader.display(this,photoView,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532892735280&di=3cd4c1ea2419b533d2fad526031eada4&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160315%2Fmp63612250_1458050205554_8.jpeg");
////
////        mPhoto.disenable();
////        mPhoto.setImageURI(Uri.parse("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=f0f0a048fa03738dc14a0a228319b073/08f790529822720e45023d1277cb0a46f31fab0e.jpg"));
////        mPhoto.setImageResource(R.drawable.image);
////        mPhoto.setImageURI(Uri.parse("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=f0f0a048fa03738dc14a0a228319b073/08f790529822720e45023d1277cb0a46f31fab0e.jpg"));
//        // 启用图片缩放功能
//        photoView.enable();
//// 禁用图片缩放功能 (默认为禁用，会跟普通的 ImageView 一样，缩放功能需手动调用 enable()启用)
//        photoView.disenable();
//// 获取图片信息
//        Info info = photoView.getInfo();
//// 从普通的 ImageView 中获取 Info
////        Info info = PhotoView.getImageViewInfo(ImageView);
//// 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照 demo 的使用
//        photoView.animaFrom(info);
//// 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照 demo 的使用
//        photoView.animaTo(info,new Runnable() {
//            @Override
//            public void run() {
//                //动画完成监听
//            }
//        });
//// 获取/设置 动画持续时间
//        photoView.setAnimaDuring(2000);
//        int d = photoView.getAnimaDuring();
//// 获取/设置 最大缩放倍数
//        photoView.setMaxScale(15f);
//        float maxScale = photoView.getMaxScale();
//// 设置动画的插入器
////        photoView.setInterpolator(Interpolator interpolator);
    }
}
