package com.yd.hp.huangxiaoer.presenter;

import com.yd.hp.huangxiaoer.model.ILoginModel;
import com.yd.hp.huangxiaoer.model.bean.LoginBean;
import com.yd.hp.huangxiaoer.view.interfaces.ILoginView;

public class ILoginPresenter {
    private ILoginView iLoginView;
    private final ILoginModel iLoginModel;

    public ILoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLoginModel = new ILoginModel();
    }
    public void getLoginData(String mobile,String password){
        iLoginModel.getLoginData(mobile, password, new ILoginModel.GetModel() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                iLoginView.onSuccess(loginBean);
            }
        });
    }
}
