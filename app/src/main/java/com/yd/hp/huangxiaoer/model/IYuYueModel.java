package com.yd.hp.huangxiaoer.model;

import android.util.Log;

import com.yd.hp.huangxiaoer.model.bean.YuYueBean;
import com.yd.hp.huangxiaoer.util.RetrofitUtil;
import com.yd.hp.huangxiaoer.util.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IYuYueModel {
    private static final String TAG = "IYuYueModel";
    public void getYuYue(final GetModel getModel){
        RetrofitUtil.getINSTANCE().getApi().getYuYue()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YuYueBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YuYueBean value) {
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

    public interface GetModel {
        void onSuccess(YuYueBean yuYueBean);
    }

}
