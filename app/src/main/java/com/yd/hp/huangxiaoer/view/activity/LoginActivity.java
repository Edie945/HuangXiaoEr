package com.yd.hp.huangxiaoer.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yd.hp.huangxiaoer.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static cn.smssdk.SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE;
import static cn.smssdk.SMSSDK.getSupportedCountries;
import static cn.smssdk.SMSSDK.getVerificationCode;
import static cn.smssdk.SMSSDK.submitVerificationCode;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginSMS ";
    @BindView(R.id.mobile_tv)
    TextView mobile_tv;
    @BindView(R.id.mobile_ll)
    RelativeLayout mobile_ll;
    @BindView(R.id.pass_tv)
    TextView pass_tv;
    @BindView(R.id.yanzhengma_btn)
    Button yanzhengma_btn;
    @BindView(R.id.pass_ll)
    RelativeLayout pass_ll;
    @BindView(R.id.sms_ed)
    EditText sms_ed;
    @BindView(R.id.mobile_ed)
    EditText mobile_ed;
    @BindView(R.id.yanzhengma_login_btn)
    TextView yanzhengma_login_btn;
    @BindView(R.id.qiehuan_tv)
    TextView qiehuan_tv;
    @BindView(R.id.qita_tv)
    TextView qita_tv;
    @BindView(R.id.weixin_login_btn)
    TextView weixin_login_btn;
    @BindView(R.id.qq_login_btn)
    TextView qq_login_btn;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int result = msg.arg2;
            int event = msg.arg1;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                Log.d(TAG, "RESULT_COMPLETE");
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Log.d(TAG, "提交验证码成功/验证码一致-EVENT_SUBMIT_VERIFICATION_CODE" + data.toString());
                    Toast.makeText(LoginActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    Log.d(TAG, "获取/发送验证码成功-EVENT_GET_VERIFICATION_CODE" + data.toString());
                    Toast.makeText(LoginActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_SHORT).show();
                    Message message = new Message();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    Log.d(TAG, "EVENT_GET_SUPPORTED_COUNTRIES=" + data.toString());
                }
            } else {
                Toast.makeText(LoginActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                ((Throwable) data).printStackTrace();
                Log.d(TAG, ((Throwable) data).getMessage());
            }
        }
    };
    private EventHandler eh;
    private String mobile;

    @Override
    protected void initData() {
        eh = new EventHandler() {
            @Override
            public void afterEvent(int i, int i1, Object o) {
                Message msg = new Message();
                msg.arg1 = i;
                msg.arg2 = i1;
                msg.obj = o;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.yanzhengma_btn, R.id.yanzhengma_login_btn, R.id.qiehuan_tv, R.id.weixin_login_btn, R.id.qq_login_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.yanzhengma_btn:
                initFaSong();
                break;
            case R.id.yanzhengma_login_btn:
                initYanZheng();
                break;
            case R.id.qiehuan_tv:
                Toast.makeText(this,"切换登录方式",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,CGLoginActivity.class));
                finish();
                break;
            case R.id.weixin_login_btn:
                Toast.makeText(this,"微信登录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.qq_login_btn:
                Toast.makeText(this,"QQ登录",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initFaSong() {
        mobile = mobile_ed.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)){
            Toast.makeText(this,"请输入您的手机号码",Toast.LENGTH_SHORT).show();
            return;
        }
        if (isMobileNO(mobile)){
            timeCountDown();
            getSupportedCountries();
            getVerificationCode("86", mobile);
        }else {
            Toast.makeText(this,"手机号码格式错误，请检查",Toast.LENGTH_SHORT).show();
        }
    }

    private void initYanZheng() {
        String sms_code = sms_ed.getText().toString().trim();
        if (TextUtils.isEmpty(sms_code)){
            Toast.makeText(this,"输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }
        Log.e(TAG, "mobile : " + mobile);
        submitVerificationCode("86", mobile, sms_code);
    }

    private void timeCountDown() {
        final int count = 30;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1) //设置循环31次
                //进行数据转换
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        Log.d(TAG, "along=" + aLong);
                        //aLong是原来的值0,1,2
                        //返回倒计时的值30,29,28
                        return count - aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG, "accept");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe=");

                    }

                    @Override
                    public void onNext(Long aLong) {
                        yanzhengma_btn.setEnabled(false);//在发送数据的时候设置为不能点击
                        yanzhengma_btn.setTextColor(Color.WHITE);
                        yanzhengma_btn.setBackgroundColor(Color.GRAY);//背景色设为灰色
                        Log.d(TAG, "onNext: " + aLong);
                        yanzhengma_btn.setText( aLong + "S");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError=" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted: ");
                        yanzhengma_btn.setText("获取验证码");//数据发送完后设置为原来的文字
                        yanzhengma_btn.setTextColor(Color.BLACK);
                        yanzhengma_btn.setEnabled(true);
                        yanzhengma_btn.setBackgroundColor(Color.parseColor("#FFD200"));//数据发送完后设置为原来背景色
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    private boolean isMobileNO(String phone) {
       /*
    总结起来就是第一位必定为1，第二位必定为345789，其他位置的可以为0-9
    */
        String telRegex = "[1][345789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phone))
            return false;
        else
            return phone.matches(telRegex);
    }
}
