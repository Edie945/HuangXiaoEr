package com.yd.hp.huangxiaoer.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yd.hp.huangxiaoer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YouHuiQuanActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_you_hui_quan;
    }


    @OnClick(R.id.back)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
