package com.yd.hp.huangxiaoer.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yd.hp.huangxiaoer.R;
import com.yd.hp.huangxiaoer.model.bean.FileBean;
import com.yd.hp.huangxiaoer.model.bean.NickBean;
import com.yd.hp.huangxiaoer.model.bean.UserBean;
import com.yd.hp.huangxiaoer.presenter.INickPresenter;
import com.yd.hp.huangxiaoer.view.interfaces.INcikView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ZiLiaoActivity extends BaseActivity implements INcikView{


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.circleImageView)
    SimpleDraweeView circleImageView;
    @BindView(R.id.touxiang_re)
    RelativeLayout touxiang_re;
    @BindView(R.id.username_xg)
    TextView username_xg;
    @BindView(R.id.username_re)
    RelativeLayout username_re;
    @BindView(R.id.phone_img)
    ImageView phone_img;
    @BindView(R.id.tel_mobile)
    TextView tel_mobile;
    @BindView(R.id.mobile_re)
    RelativeLayout mobile_re;
    //本地相册图片选择
    private String[] mCustomItem = {"本地相册", "相机拍照"};
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private File Defaltefile;
    private Uri tempUri;
    private String uid;
    private String icon;
    private String nickname;
    private String username;
    private INickPresenter iNickPresenter;
    private File file;
    private static final String TAG = "ZiLiaoActivity";

    @Override
    protected void initData() {
        iNickPresenter = new INickPresenter(this);
    }

    @Override
    protected void initView() {
        //隐层App标题栏
//        getSupportActionBar().hide();
        ButterKnife.bind(this);

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        icon = intent.getStringExtra("icon");
        Log.e(TAG, "initView: "+icon );
        nickname = intent.getStringExtra("nickname");
        username = intent.getStringExtra("username");
        circleImageView.setImageURI(Uri.parse(icon));
//        tou1.setImageURI(Uri.parse(icon));
        username_xg.setText(nickname);
        tel_mobile.setText(username);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_zi_liao;
    }

    @OnClick({R.id.touxiang_re, R.id.username_re, R.id.mobile_re, R.id.back})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.touxiang_re:
                showSingleChoiceDialog();
                break;
            case R.id.username_re:
                final EditText editText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("修改后昵称")
                        .setView(editText)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                nickname = editText.getText().toString();
                                Log.e("uid--====","*-*-*-*-*-*-"+uid+"-*-*-*-"+nickname);
                                iNickPresenter.getData(uid,nickname);
                            }
                        }).create();
                dialog.show();
                break;
            case R.id.mobile_re:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取相机返回值
        if (resultCode == -1) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        Bitmap parcelable = extras.getParcelable("data");
                        saveFile(parcelable, "head.jpg");
                        Log.e("图片路径", file.getName().trim());
                        RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), file != null ? file : Defaltefile);
                        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), filebody);

                        iNickPresenter.getDataFrom(uid,part);
                    }
                    break;
            }
        }
    }
    //判断sd卡是否存在
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();
    }
    //将图片保存到本地，参数一个是bitmap,一个是文件名字
    public void saveFile(Bitmap bm, String fileName) {
        try {
            String path = getSDPath() + "/revoeye/";
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            file = new File(path + fileName);
            BufferedOutputStream bos;
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    public void showSingleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择：");
        builder.setItems(mCustomItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Defaltefile = new File(Environment.getExternalStorageDirectory() + "/revoeye/", "image.jpg");
                        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onSuccess(NickBean nickBean) {
        if ("0".equals(nickBean.getCode())){
            username_xg.setText(nickname);
        }
    }

    @Override
    public void onSuccess(FileBean fileBean) {
        if ("0".equals(fileBean.getCode())){
            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
