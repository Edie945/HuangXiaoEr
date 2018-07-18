package com.yd.hp.huangxiaoer.presenter;

import com.yd.hp.huangxiaoer.model.IYuYueModel;
import com.yd.hp.huangxiaoer.model.bean.YuYueBean;
import com.yd.hp.huangxiaoer.view.interfaces.IYuYueView;

public class IYuYuePresenter {

    private IYuYueView iYuYueView;
    private final IYuYueModel iYuYueModel;

    public IYuYuePresenter(IYuYueView iYuYueView) {
        this.iYuYueView = iYuYueView;
        iYuYueModel = new IYuYueModel();
    }

    public void getYuYueData(){
        iYuYueModel.getYuYue(new IYuYueModel.GetModel() {
            @Override
            public void onSuccess(YuYueBean yuYueBean) {
                iYuYueView.onSuccess(yuYueBean);
            }
        });
    }

}
