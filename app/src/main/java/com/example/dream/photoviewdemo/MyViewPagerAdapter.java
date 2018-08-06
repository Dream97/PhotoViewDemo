package com.example.dream.photoviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;


public class MyViewPagerAdapter extends PagerAdapter{
    private String[] imgList;
    private Context context;
    public MyViewPagerAdapter(Context context,String[] imgList){
        this.imgList = imgList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgList.length;
    }

    //指定复用的判断逻辑，固定写法：view == object
    @Override
    public boolean isViewFromObject(View view, Object object) {
        //当创建新的条目，又反回来，判断view是否可以被复用(即是否存在)
        return view == object;
    }

    //返回要显示的条目内容
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //container  容器  相当于用来存放imageView
        //从集合中获得图片
//        int newPosition = position % 5; //数组中总共有5张图片，超过数组长度时，取摸，防止下标越界
//        View view = LayoutInflater.from(context).inflate(R.layout.myimage,container);

        PhotoView photoView = new PhotoView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
//        photoView.setLayoutParams(new La);
        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        ImageLoader.display(context,photoView,imgList[position]);

        //把图片添加到container中
//        container.addView(imageView);
        //把图片返回给框架，用来缓存
        ImageView imageView = new ImageView(context);
//        imageView.setImageResource(R.drawable.timg);
        ImageLoader.display(context,imageView,imgList[position]);
        container.addView(imageView);
        return imageView;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //object:刚才创建的对象，即要销毁的对象
        container.removeView((View) object);
    }
}
