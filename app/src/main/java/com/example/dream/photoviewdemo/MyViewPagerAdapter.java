package com.example.dream.photoviewdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

        PhotoView photoView = new PhotoView(context);

        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        ImageLoader.display(context,photoView,imgList[position]);

        //把图片添加到container中
        container.addView(photoView);
        //把图片返回给框架，用来缓存
        return photoView;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //object:刚才创建的对象，即要销毁的对象
        container.removeView((View) object);
    }
}
