package com.yd.hp.huangxiaoer.model;

import android.util.Log;

import com.yd.hp.huangxiaoer.model.bean.UserBean;
import com.yd.hp.huangxiaoer.util.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IUserModel {
    private static final String TAG = "IUserModel";
    public void getUserData(String uid, final GetModel getModel){
        RetrofitUtils.getINSTANCE().getApi()
                .getUser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean value) {
                        Log.e(TAG, "onNext: "+value.getData().getIcon() );
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
        void onSuccess(UserBean userBean);
    }

}
