package com.yd.hp.huangxiaoer.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.model.bean.LoginBean;
import com.yd.hp.huangxiaoer.model.bean.UserBean;
import com.yd.hp.huangxiaoer.presenter.IUserPresenter;
import com.yd.hp.huangxiaoer.view.activity.SheZhiActivity;
import com.yd.hp.huangxiaoer.view.activity.XiHuanDeDianActivity;
import com.yd.hp.huangxiaoer.view.activity.YouHuiQuanActivity;
import com.yd.hp.huangxiaoer.view.activity.ZiLiaoActivity;
import com.yd.hp.huangxiaoer.view.interfaces.IUserView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

public class FragmentMine extends Fragment implements IUserView {

    @BindView(R.id.head_portrait)
    SimpleDraweeView head_portrait;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.tel_tv)
    TextView tel_tv;
    @BindView(R.id.user_re)
    RelativeLayout user_re;
    @BindView(R.id.bjzl_img)
    ImageView bjzl_img;
    @BindView(R.id.gerenziliao_re)
    RelativeLayout gerenziliao_re;
    @BindView(R.id.xhdd_img)
    ImageView xhdd_img;
    @BindView(R.id.xihuandedian_re)
    RelativeLayout xihuandedian_re;
    @BindView(R.id.yhq_img)
    ImageView yhq_img;
    @BindView(R.id.youhuiquan_re)
    RelativeLayout youhuiquan_re;
    @BindView(R.id.sz_img)
    ImageView sz_img;
    @BindView(R.id.shezhi_re)
    RelativeLayout shezhi_re;
    private View view;
    private Unbinder unbinder;
    private LoginBean loginBean;
    private static final String TAG = "FragmentMine";
    private UserBean.DataBean data;
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;
    private String uid;

    /**
     * 用户名
     */
    @SuppressLint("CommitPrefEdits")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.mine_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        sp = getContext().getSharedPreferences("user", MODE_PRIVATE);
        edit = sp.edit();
        initData();
        return view;
    }

    private void initData() {
        uid = sp.getString("uid", null);
        IUserPresenter iUserPresenter = new IUserPresenter(this);
        iUserPresenter.getUserData(uid);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(UserBean userBean) {
        data = userBean.getData();
        head_portrait.setImageURI(data.getIcon());
        username.setText(data.getNickname());
        tel_tv.setText(data.getUsername());
        Log.e(TAG, "initView: " + data.getIcon());
    }

    @OnClick({R.id.user_re, R.id.gerenziliao_re, R.id.xihuandedian_re, R.id.youhuiquan_re, R.id.shezhi_re})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.user_re:
                break;
            case R.id.gerenziliao_re:
                Intent intent = new Intent(getContext(),ZiLiaoActivity.class);
                intent.putExtra("uid",data.getUid());
                intent.putExtra("icon",data.getIcon());
                intent.putExtra("nickname",data.getNickname());
                intent.putExtra("username",data.getUsername());
                intent.putExtra("token",data.getToken());
                startActivity(intent);
                break;
            case R.id.xihuandedian_re:
                startActivity(new Intent(getContext(), XiHuanDeDianActivity.class));
                break;
            case R.id.youhuiquan_re:
                startActivity(new Intent(getContext(), YouHuiQuanActivity.class));
                break;
            case R.id.shezhi_re:
                startActivity(new Intent(getContext(), SheZhiActivity.class));
                break;
        }
    }
}
