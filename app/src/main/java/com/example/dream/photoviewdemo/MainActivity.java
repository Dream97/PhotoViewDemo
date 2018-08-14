package com.example.dream.photoviewdemo;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    private String[] imglist = {
            "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=82f1640b19ce36d3bd0485300af23a24/fcfaaf51f3deb48f5510390ffc1f3a292cf578e2.jpg",
            "https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=ff6ed7cfa718972bbc3a06cad6cc7b9d/267f9e2f07082838304837cfb499a9014d08f1a0.jpg",
            "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a537b312164c510fb1c4e41a50582528/b8389b504fc2d562a746bd37eb1190ef77c66c99.jpg",
            "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=56577be956afa40f23c6c8dd9b66038c/562c11dfa9ec8a1346809b3bfb03918fa1ecc057.jpg",
            "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a537b312164c510fb1c4e41a50582528/b8389b504fc2d562a746bd37eb1190ef77c66c99.jpg",
            "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=4a51c9cd7e8b4710d12ffbccf3ccc3b2/b64543a98226cffceee78e5eb5014a90f703ea09.jpg"
    };
    private LinearLayout points;
    private int prePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initData();
    }

    private void initData() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                position = position % imglist.length;
                //把前一个白变为黑
                points.getChildAt(prePosition).setBackgroundResource(R.drawable.point_back);
                //把当前白点变为黑点
                points.getChildAt(position).setBackgroundResource(R.drawable.point_white);
                //记录下当前位置(当前位置变白后，赋值给前一个点)
                prePosition = position;
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        myViewPagerAdapter = new MyViewPagerAdapter(this,imglist);
        viewPager.setAdapter(myViewPagerAdapter);
        points = findViewById(R.id.points);
        for(int i = 0;i<imglist.length;i++) {
            //白点
            //根据viewPager的数量，添加白点指示器
            ImageView view = new ImageView(this);
            view.setBackgroundResource(R.drawable.point_back);
            //给点设置宽高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            //给控件设置边距
            params.leftMargin = 10;
            //给view设置参数
            view.setLayoutParams(params);
            //将图片添加到线性布局中
            points.addView(view);
        }

        points.getChildAt(0).setBackgroundResource(R.drawable.point_white);
        viewPager.setCurrentItem(0);
    }
}
