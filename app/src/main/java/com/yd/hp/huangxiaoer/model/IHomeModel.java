package com.yd.hp.huangxiaoer.model;

import android.util.Log;

import com.yd.hp.huangxiaoer.model.bean.HomeBean;
import com.yd.hp.huangxiaoer.util.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IHomeModel {
    private static final String TAG = "IHomeModel";
    public void getHomeData(final GetModel getModel){
        RetrofitUtils.getINSTANCE().getApi()
                .getHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean value) {
                        getModel.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e );
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface GetModel{
        void onSuccess(HomeBean homeBean);
    }

}
