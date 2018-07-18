package com.yd.hp.huangxiaoer.presenter;

import com.yd.hp.huangxiaoer.model.IHomeModel;
import com.yd.hp.huangxiaoer.model.bean.HomeBean;
import com.yd.hp.huangxiaoer.view.interfaces.IHomeView;

public class IHomePresenter {
    private IHomeView iHomeView;
    private final IHomeModel iHomeModel;

    public IHomePresenter(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        iHomeModel = new IHomeModel();
    }

    public void getHomeData(){
        iHomeModel.getHomeData(new IHomeModel.GetModel() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                iHomeView.onSuccess(homeBean);
            }
        });
    }

}
