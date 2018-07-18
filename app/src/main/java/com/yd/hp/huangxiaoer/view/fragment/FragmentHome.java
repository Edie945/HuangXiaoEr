package com.yd.hp.huangxiaoer.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.model.bean.HomeBean;
import com.yd.hp.huangxiaoer.presenter.IHomePresenter;
import com.yd.hp.huangxiaoer.view.interfaces.IHomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentHome extends Fragment{
    @BindView(R.id.liebiaocaidan)
    ImageView liebiaocaidan;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<HomeBean.DataBean.BannerBean> banner;
    private View view;
    private Unbinder unbinder;
    String[] strings = {"热销", "招牌", "主食", "小吃", "饮品", "湘菜", "川菜", "豫菜", "东北菜"};
    private ArrayList<Fragment> fragments = new ArrayList<>();;
    private FragmentTab fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.home_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        Log.e("TAG", "onCreateView: "+strings.length );
        initTabLayout();
//        initData();
        return view;
    }

    /*private void initData() {
        IHomePresenter iHomePresenter = new IHomePresenter(this);
        iHomePresenter.getHomeData();
    }*/

    private void initTabLayout() {
        tablayout.setupWithViewPager(vp);

        for (int i = 0; i < strings.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(strings[i]));
            fragments.add(new FragmentTab());
        }
        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return strings[position];
            }
        });
    }

    @OnClick(R.id.liebiaocaidan)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.liebiaocaidan:

                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
