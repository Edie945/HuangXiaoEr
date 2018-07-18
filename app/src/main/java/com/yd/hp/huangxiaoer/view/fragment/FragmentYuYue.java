package com.yd.hp.huangxiaoer.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.model.bean.YuYueBean;
import com.yd.hp.huangxiaoer.presenter.IYuYuePresenter;
import com.yd.hp.huangxiaoer.view.activity.MapActivity;
import com.yd.hp.huangxiaoer.view.activity.SouSuoActivity;
import com.yd.hp.huangxiaoer.view.adapter.YuYueAdapter;
import com.yd.hp.huangxiaoer.view.interfaces.IYuYueView;
import com.yd.hp.huangxiaoer.view.interfaces.ItemClicked;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentYuYue extends Fragment implements IYuYueView{
    @BindView(R.id.dizhi_tv)
    TextView dizhi_tv;
    @BindView(R.id.dingwei_linear)
    LinearLayout dingwei_linear;
    @BindView(R.id.sousuolan_linear)
    LinearLayout sousuolan_linear;
    @BindView(R.id.fujinshangjia)
    TextView fujinshangjia;
    @BindView(R.id.yuye_rv)
    RecyclerView yuye_rv;
    private View view;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.yuyue_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initData();
        return view;
    }

    private void initData() {
        IYuYuePresenter iYuYuePresenter = new IYuYuePresenter(this);
        iYuYuePresenter.getYuYueData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.dingwei_linear, R.id.sousuolan_linear})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.dingwei_linear:
                startActivity(new Intent(getContext(), MapActivity.class));
                break;
            case R.id.sousuolan_linear:
                startActivity(new Intent(getContext(), SouSuoActivity.class));
                break;
        }
    }
    @Subscribe
    public void getEventBus(StringBuffer dizhi) {
        if (dizhi != null) {
            //这里拿到事件之后吐司一下
            Toast.makeText(getContext(), "当前地址：" + dizhi+"", Toast.LENGTH_SHORT).show();
            dizhi_tv.setText(dizhi);
        }
    }

    @Override
    public void onSuccess(YuYueBean yuYueBean) {
        List<YuYueBean.DataBean> data = yuYueBean.getData();
        YuYueAdapter yuYueAdapter = new YuYueAdapter(data, getContext());
        yuye_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        yuye_rv.setAdapter(yuYueAdapter);
        yuYueAdapter.setItemClicked(new ItemClicked() {
            @Override
            public void onItemClicked(View v, int porsion) {
                Toast.makeText(getContext(),"点击"+porsion+"条目",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
