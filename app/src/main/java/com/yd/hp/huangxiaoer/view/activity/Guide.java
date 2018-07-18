package com.yd.hp.huangxiaoer.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yd.hp.huangxiaoer.R;

import java.util.ArrayList;
import java.util.List;

public class Guide extends Activity {

    private List<View> views;
    private ViewPager view_pager;
    private int currentItem;
    private boolean flag1;
    private ArrayList<Integer> imageIDList;
    private ArrayList<ImageView> imageViews;
    private GuildeAdapter adapter;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        shared = getSharedPreferences("is", MODE_PRIVATE);
        boolean flag = shared.getBoolean("flag", false);
        editor = shared.edit();
        if (flag){
            startActivity(new Intent(Guide.this,SplashActivity.class));
            finish();
        }else{
            //初始化引导数据
            initGuideData();
            //初始化引导页
            initGuideView();
            //初始化分页控件
            initView();
        }


    }

    private void initGuideData() {
        imageIDList = new ArrayList<>();
        imageIDList.add(R.mipmap.one);
        imageIDList.add(R.mipmap.two);
        imageIDList.add(R.mipmap.three);
    }

    private void initGuideView() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < imageIDList.size(); i++) {
            imageViews.add(new ImageView(this));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new GuildeAdapter();
        view_pager.setAdapter(adapter);
        view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                Log.e("TAG", "onPageSelected: 监听改变  ： "+position );
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        view_pager.setOnTouchListener(new View.OnTouchListener() {

            float startX;
            float startY;
            float endX;
            float endY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = motionEvent.getX();
                        endY = motionEvent.getY();
                        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                        Point size = new Point();
                        windowManager.getDefaultDisplay().getSize(size);
                        int width = size.x;
                        if (currentItem == (imageViews.size()-1)&&startX-endX > 0 && startX-endX>=(width/4)){
                            Log.e("TAG", "onTouch: 进入触摸" );
                            goToActivity();
                            //进入………………退出
                            overridePendingTransition(R.anim.slide_in_form_right,R.anim.slide_out_to_left);
                        }
                        break;
                }
                return false;
            }
        });


    }

    private void goToActivity() {
        editor.putBoolean("flag",true);
        editor.commit();
        startActivity(new Intent(Guide.this,SplashActivity.class));
        finish();
    }
    private class GuildeAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return object == view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(imageViews.get(position));
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            imageView.setImageResource(imageIDList.get(position));
            container.addView(imageView);
            return imageView;
        }
    }
}
