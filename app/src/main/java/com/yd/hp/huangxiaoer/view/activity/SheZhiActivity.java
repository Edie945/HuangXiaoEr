package com.yd.hp.huangxiaoer.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.util.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheZhiActivity extends BaseActivity {

    @BindView(R.id.del_num)
    TextView del_num;
    @BindView(R.id.del_tu)
    RelativeLayout del_tu;
    @BindView(R.id.dowmn)
    ImageView dowmn;
    @BindView(R.id.putong)
    TextView putong;
    @BindView(R.id.switch_img)
    ImageView switch_img;
    @BindView(R.id.dowmn1)
    ImageView dowmn1;
    @BindView(R.id.congbu)
    TextView congbu;
    @BindView(R.id.spread)
    ImageView spread;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.guanyu_app)
    RelativeLayout guanyu_app;
    private boolean flag;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    dialog.dismiss();
                    del_num.setText("0.0KB");
                    break;
                case 0x02:
                    dialog.dismiss();
                    break;
            }

        }
    };
    private ProgressDialog dialog;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_she_zhi;
    }

    @OnClick({R.id.del_num, R.id.del_tu, R.id.dowmn, R.id.putong, R.id.switch_img, R.id.dowmn1, R.id.congbu, R.id.spread, R.id.back, R.id.guanyu_app})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.guanyu_app:
                startActivity(new Intent(SheZhiActivity.this,GuanYuAppActivity.class));
                break;
            case R.id.back:
                finish();
                break;
            case R.id.del_num:
                break;
            case R.id.del_tu:
                createLoadingDialog();
                break;
            case R.id.dowmn:
                break;
            case R.id.putong:
                break;
            case R.id.switch_img:
                initSwitch();
                break;
            case R.id.dowmn1:
                break;
            case R.id.congbu:
                break;
            case R.id.spread:
                break;
        }
    }

    private void initSwitch() {
        if(flag){
            switch_img.setImageResource(R.mipmap.switch1);
        }else{
            switch_img.setImageResource(R.mipmap.switch2);
        }
        flag = !flag;
    }

    private void createLoadingDialog() {
        Message msg = new Message();
        dialog = new ProgressDialog(this);
        dialog.setMessage("清理中……");
        dialog.setCancelable(true);// 不可以用“返回键”取消

        dialog.show();
        try {
            DataCleanManager.cleanInternalCache(getApplicationContext());
            msg.what = 0x01;
        } catch (Exception e) {
            e.printStackTrace();
            msg.what = 0x02;
        }
        handler.sendMessageDelayed(msg, 1000);
    }
}
