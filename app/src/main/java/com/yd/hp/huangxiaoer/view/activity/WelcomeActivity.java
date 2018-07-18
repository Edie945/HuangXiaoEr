package com.yd.hp.huangxiaoer.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yd.hp.huangxiaoer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.ivGif)
    ImageView ivGif;
    @BindView(R.id.btn_welcome)
    TextView btn_welcome;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_welcome;
    }

    @OnClick(R.id.btn_welcome)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_welcome:
                startActivity(new Intent(WelcomeActivity.this, CGLoginActivity.class));
                finish();
                break;
        }
    }


}
