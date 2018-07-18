package com.yd.hp.huangxiaoer.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.yd.hp.huangxiaoer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.ivGif)
    ImageView ivGif;
    @BindView(R.id.btn_welcome)
    TextView btn_welcome;
    private SharedPreferences mSp;
    private Handler mHandler = new Handler() {
    };

    @Override
    protected void initData() {
        initGif();
//        initShardPreferences();
    }

//    private void initShardPreferences() {
////        SharedPreferences shared = getSharedPreferences("is", MODE_PRIVATE);
////        boolean isfer = shared.getBoolean("isfer", true);
////        SharedPreferences.Editor editor = shared.edit();
////        if (isfer) {
////            //第一次进入跳转
////            startActivity(new Intent(SplashActivity.this, Guide.class));
////            finish();
////            editor.putBoolean("isfer", false);
////            editor.commit();
////        } else {
//            //第二次进入跳转
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    startActivity(new Intent(SplashActivity.this, CGLoginActivity.class));
//                    finish();
//                }
//            }, 3000);
////        }
//    }

    private void initGif() {
        Glide.with(SplashActivity.this).load(R.mipmap.app_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(new GlideDrawableImageViewTarget(ivGif, 1));
    }

    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_splash;
    }

    @OnClick(R.id.btn_welcome)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_welcome:
                startActivity(new Intent(SplashActivity.this, CGLoginActivity.class));
                finish();
                break;
        }
    }
}
