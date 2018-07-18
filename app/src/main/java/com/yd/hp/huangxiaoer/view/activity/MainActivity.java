package com.yd.hp.huangxiaoer.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.view.fragment.FragmentHome;
import com.yd.hp.huangxiaoer.view.fragment.FragmentMine;
import com.yd.hp.huangxiaoer.view.fragment.FragmentYuYue;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.rd_app)
    RadioButton rd_app;
    @BindView(R.id.rd_home)
    RadioButton rd_home;
    @BindView(R.id.rd_mine)
    RadioButton rd_mine;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    private FragmentMine fragmentMine;
    private FragmentYuYue fragmentYuYue;
    private ArrayList<Fragment> arrayList;
    private FragmentManager manager;
    private FragmentHome fragmentHome;

    @Override
    protected void initData() {
        arrayList.add(fragmentYuYue);
        arrayList.add(fragmentMine);
        arrayList.add(fragmentHome);

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame,arrayList.get(2)).commit();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rd_app:
                        manager.beginTransaction().replace(R.id.frame,arrayList.get(0)).commit();
                        break;
                    case R.id.rd_mine:
                        manager.beginTransaction().replace(R.id.frame,arrayList.get(1)).commit();
                        break;
                    case R.id.rd_home:
                        manager.beginTransaction().replace(R.id.frame,arrayList.get(2)).commit();
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        fragmentMine = new FragmentMine();
        fragmentYuYue = new FragmentYuYue();
        fragmentHome = new FragmentHome();
        arrayList = new ArrayList<>();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
}
