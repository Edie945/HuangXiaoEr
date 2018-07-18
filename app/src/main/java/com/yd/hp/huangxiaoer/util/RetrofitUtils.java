package com.yd.hp.huangxiaoer.util;

import com.yd.hp.huangxiaoer.util.constant.MyApi;
import com.yd.hp.huangxiaoer.util.constant.RetrofitApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils INSTANCE;
    private final Retrofit retrofit;

    public RetrofitUtils (){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.BASE_UEL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitUtils getINSTANCE() {
        if (INSTANCE == null){
            synchronized (RetrofitUtils.class){
                if (INSTANCE == null){
                    INSTANCE = new RetrofitUtils();
                }
            }
        }
        return INSTANCE;
    }
    public RetrofitApi getApi(){
        return retrofit.create(RetrofitApi.class);
    }
}
