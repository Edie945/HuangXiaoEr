package com.yd.hp.huangxiaoer.presenter;

import com.yd.hp.huangxiaoer.model.IUserModel;
import com.yd.hp.huangxiaoer.model.bean.UserBean;
import com.yd.hp.huangxiaoer.view.interfaces.IUserView;

public class IUserPresenter {
    private IUserView iUserView;
    private final IUserModel iUserModel;

    public IUserPresenter(IUserView iUserView) {
        this.iUserView = iUserView;
        iUserModel = new IUserModel();
    }

    public void getUserData(String uid){
        iUserModel.getUserData(uid, new IUserModel.GetModel() {
            @Override
            public void onSuccess(UserBean userBean) {
                iUserView.onSuccess(userBean);
            }
        });
    }
    public void deach(){
        if (iUserView != null){
            iUserView = null;
        }
    }
}
