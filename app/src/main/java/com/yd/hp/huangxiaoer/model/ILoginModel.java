package com.yd.hp.huangxiaoer.model;

import android.util.Log;

import com.yd.hp.huangxiaoer.model.bean.LoginBean;
import com.yd.hp.huangxiaoer.util.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ILoginModel {

    public void getLoginData(String mobile, String password, final GetModel getModel){
        RetrofitUtils.getINSTANCE().getApi()
                .getLogin(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
                        getModel.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: "+ e );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface GetModel{
        void onSuccess(LoginBean loginBean);
    }

}
