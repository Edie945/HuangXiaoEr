package com.yd.hp.huangxiaoer.view.interfaces;

import com.yd.hp.huangxiaoer.model.bean.FileBean;
import com.yd.hp.huangxiaoer.model.bean.NickBean;

public interface INcikView {
    void onSuccess(NickBean nickBean);
    void onSuccess(FileBean fileBean);
}
