package com.yd.hp.huangxiaoer.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.yd.hp.huangxiaoer.util.Tools;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
//        initNoBar();
        initGengXin();
        initView();
        initData();

    }

    private void initGengXin() {
        // 获取本版本号，是否更新
        int vision = Tools.getVersion(this);
        if (vision == 1){
            AllenVersionChecker
                    .getInstance()
                    .requestVersion()
                    .setRequestUrl("https://www.baidu.com")
                    .request(new RequestVersionListener() {
                        @Nullable
                        @Override
                        public UIData onRequestVersionSuccess(String result) {
                            return crateUIData();
                        }

                        @Override
                        public void onRequestVersionFailure(String message) {
                        }
                    })
                    .excuteMission(this);
        }

    }

    private void initNoBar() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int initLayout();

    private UIData crateUIData() {
        UIData uiData = UIData.create();
        uiData.setTitle("啊,那个啥改更新了……");
        uiData.setDownloadUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk");
        uiData.setContent("额(⊙o⊙)…，您是更呢？还是更呢？还是更呢？？？");
        return uiData;
    }
}
