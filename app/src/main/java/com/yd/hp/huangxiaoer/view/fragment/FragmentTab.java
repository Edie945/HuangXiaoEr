package com.yd.hp.huangxiaoer.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.model.bean.HomeBean;
import com.yd.hp.huangxiaoer.presenter.IHomePresenter;
import com.yd.hp.huangxiaoer.view.adapter.HomeDataAdapter;
import com.yd.hp.huangxiaoer.view.interfaces.IHomeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentTab extends Fragment implements IHomeView{
    @BindView(R.id.home_rv)
    RecyclerView home_rv;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.tab_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }
    private void initData() {
        IHomePresenter iHomePresenter = new IHomePresenter(this);
        iHomePresenter.getHomeData();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        List<HomeBean.DataBean.TuijianBean.ListBeanX> list = homeBean.getData().getTuijian().getList();
        HomeDataAdapter homeDataAdapter = new HomeDataAdapter(list,getContext());
        home_rv.setLayoutManager(new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false));
        home_rv.setAdapter(homeDataAdapter);
    }
}
