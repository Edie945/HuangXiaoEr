package com.yd.hp.huangxiaoer.presenter;

import com.yd.hp.huangxiaoer.model.INickModel;
import com.yd.hp.huangxiaoer.model.bean.FileBean;
import com.yd.hp.huangxiaoer.model.bean.NickBean;
import com.yd.hp.huangxiaoer.view.interfaces.INcikView;
import com.yd.hp.huangxiaoer.view.interfaces.IUserView;

import okhttp3.MultipartBody;

public class INickPresenter {
    private INcikView iNcikView;
    private final INickModel iNickModel;

    public INickPresenter(INcikView iUserView) {
        this.iNcikView = iUserView;
        iNickModel = new INickModel();
    }

    public void getData(String uid, String s) {
        iNickModel.getDataFrom(uid, s, new INickModel.GetModel() {
            @Override
            public void onSuccess(NickBean nickBean) {
                iNcikView.onSuccess(nickBean);
            }

            @Override
            public void onSuccess(FileBean fileBean) {

            }
        });
    }
    public void deach(){
        if (iNcikView != null){
            iNcikView = null;
        }
    }

    public void getDataFrom(String uid, MultipartBody.Part part) {
        iNickModel.getData(uid, part, new INickModel.GetModel() {
            @Override
            public void onSuccess(NickBean nickBean) {

            }

            @Override
            public void onSuccess(FileBean fileBean) {
                iNcikView.onSuccess(fileBean);
            }
        });
    }
}
