[TOC]

##  一. 前言

使用photoView实现图片的放大缩小，再使用viewPager实现图片的左右滑动
![这里写图片描述](https://img-blog.csdn.net/20180807013815918?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MjYxMjE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

## 二. 添加依赖

-  build.gradle (app)
```java
    //photoView
    implementation 'com.github.chrisbanes:PhotoView:2.0.0'
    //glide
    implementation 'com.github.bumptech.glide:glide:4.7.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
```

- build.gradle (project)
	

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

```

- manifests 添加网络权限

```
    <uses-permission android:name="android.permission.INTERNET"/>
```

## 三. XML文件

- activity_main

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:background="#000"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </android.support.v4.view.ViewPager>

        <LinearLayout
            android:id="@+id/points"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:gravity="center"
            android:layout_alignBottom="@+id/viewpager"
            android:layout_marginBottom="30dp"
            android:padding="5dp"></LinearLayout>
</RelativeLayout>
```

## 四. 使用Glide 

这里使用Glide来加载网络图片,Gilde自从4.0.0版本开始要通过生成GlideApp类来使用各种Api,那么就开始动手实践吧
### 1.生成GlideApp

- 首先创建MyAppGlideModule类
	

```java

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
}
```

- build ->Rebuild Project

### 2. 封装ImageLoading类

因为我这里只是一个测试Demo,所以这是封装了一种方法,Glide的 功能很强大，如加载gif图, 实现模糊 等等操作
```java
public class ImageLoader {
    public static void display(Context context, ImageView imageView,String url){
        GlideApp
	            .with(context)
                .load(url)
                .placeholder(R.drawable.timg)
                .into(imageView);
    }

}

```

## 五. Activity层操作
在这一层需要初始化Viewpager和设置白点指示器, 并和适配器绑定数据

```java
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
```

## 六. 适配器配置
这里把需要浏览的图片地址数组传递过去，然后通过Gilde加载网络图片, 并以PhotoView代替ImageView实现放大缩小功能

```java

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
```
## Demo地址
https://github.com/Dream97/PhotoViewDemo.git

