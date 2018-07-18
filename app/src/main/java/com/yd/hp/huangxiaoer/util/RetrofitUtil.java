package com.yd.hp.huangxiaoer.util;

import com.yd.hp.huangxiaoer.util.constant.MyApi;
import com.yd.hp.huangxiaoer.util.constant.RetrofitApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static RetrofitUtil INSTANCE;
    private final Retrofit retrofit;

    public RetrofitUtil(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitUtil getINSTANCE() {
        if (INSTANCE == null){
            synchronized (RetrofitUtil.class){
                if (INSTANCE == null){
                    INSTANCE = new RetrofitUtil();
                }
            }
        }
        return INSTANCE;
    }
    public RetrofitApi getApi(){
        return retrofit.create(RetrofitApi.class);
    }
}
