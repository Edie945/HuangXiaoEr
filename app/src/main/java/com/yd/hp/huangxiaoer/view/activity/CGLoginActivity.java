package com.yd.hp.huangxiaoer.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.model.bean.LoginBean;
import com.yd.hp.huangxiaoer.presenter.ILoginPresenter;
import com.yd.hp.huangxiaoer.view.interfaces.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CGLoginActivity extends AppCompatActivity implements ILoginView{

    @BindView(R.id.mobile_tv)
    TextView mobile_tv;
    @BindView(R.id.mobile_ed)
    EditText mobile_ed;
    @BindView(R.id.mobile_ll)
    RelativeLayout mobile_ll;
    @BindView(R.id.pass_tv)
    TextView pass_tv;
    @BindView(R.id.pass_ed)
    EditText pass_ed;
    @BindView(R.id.xiaoyanjing)
    TextView xiaoyanjing;
    @BindView(R.id.pass_ll)
    RelativeLayout pass_ll;
    @BindView(R.id.cg_login_btn)
    TextView cg_login_btn;
    @BindView(R.id.qiehuan_tv)
    TextView qiehuan_tv;
    @BindView(R.id.qita_tv)
    TextView qita_tv;
    @BindView(R.id.weixin_login_btn)
    TextView weixin_login_btn;
    @BindView(R.id.qq_login_btn)
    TextView qq_login_btn;
    private String password;
    private String mobile;
    private static final String TAG = "ChengGuiLoginActiviy";
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private ILoginPresenter iLoginPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cglogin);

        initData();
        sp = getSharedPreferences("user", MODE_PRIVATE);
        edit = sp.edit();
//        edit.putBoolean("yesorno",true);
        String uid = sp.getString("uid", null);
        if (uid != null){
//            Toast.makeText(CGLoginActivity.this,"uidXML不为空",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
//            Toast.makeText(CGLoginActivity.this,"uidXML为空",Toast.LENGTH_SHORT).show();
            initView();
        }
    }

    protected void initData() {
        iLoginPresenter = new ILoginPresenter(this);
    }

    protected void initView() {

       /* boolean isfer = shared.getBoolean("isfer", true);
        SharedPreferences.Editor editor = shared.edit();*/
        ButterKnife.bind(this);
    }


    @OnClick({R.id.mobile_tv, R.id.mobile_ll, R.id.pass_tv, R.id.xiaoyanjing, R.id.pass_ll, R.id.cg_login_btn, R.id.qiehuan_tv, R.id.qita_tv, R.id.weixin_login_btn, R.id.qq_login_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mobile_tv:
                break;
            case R.id.mobile_ll:
                break;
            case R.id.pass_tv:
                break;
            case R.id.xiaoyanjing:
                break;
            case R.id.pass_ll:
                break;
            case R.id.cg_login_btn:
                initLogin();
                break;
            case R.id.qiehuan_tv:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
            case R.id.qita_tv:
                break;
            case R.id.weixin_login_btn:
                break;
            case R.id.qq_login_btn:
                break;
        }
    }

    private void initLogin() {
        mobile = mobile_ed.getText().toString();
        password = pass_ed.getText().toString();

        iLoginPresenter.getLoginData(mobile,password);
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
      if (loginBean.getCode().equals("0")){
          edit.putString("uid",loginBean.getData().getUid()+"");
          edit.commit();
          startActivity(new Intent(this,MainActivity.class));
          finish();
      }

    }
}
