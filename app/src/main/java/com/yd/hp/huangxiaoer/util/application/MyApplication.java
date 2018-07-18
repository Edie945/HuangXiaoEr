package com.yd.hp.huangxiaoer.util.application;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.zhy.autolayout.config.AutoLayoutConifg;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Fresco初始化
         */
        Fresco.initialize(this);
        /**
         * AutoLayout初始化
         */
        AutoLayoutConifg.getInstance().useDeviceSize();
        /**
         * Mob短信验证初始化
         */
        MobSDK.init(this);
        /**
         * 三方登录初始化
         */
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        /**
         * Bugly初始化
         */
        CrashReport.initCrashReport(getApplicationContext(), "03016b9d6b", true);
        /**
         * Logger
         */
        /*Logger
                .init("mytag")    //LOG TAG默认是PRETTYLOGGER
                .methodCount(3)                 // 决定打印多少行（每一行代表一个方法）默认：2
                .hideThreadInfo()               // 隐藏线程信息 默认：显示
                .logLevel(LogLevel.NONE)        // 是否显示Log 默认：LogLevel.FULL（全部显示）
                .methodOffset(2)                // 默认：0
                .logAdapter(new AndroidLogAdapter()); //可以自己构造适配器默认：AndroidLogAdapter*/
        Logger.addLogAdapter(new AndroidLogAdapter());
        /**
         * 高德地图
         */
        MultiDex.install(this);


        UMConfigure.setLogEnabled(true);
    }

}
