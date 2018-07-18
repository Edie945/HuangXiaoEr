package com.yd.hp.huangxiaoer.util.constant;

import com.yd.hp.huangxiaoer.model.bean.FileBean;
import com.yd.hp.huangxiaoer.model.bean.HomeBean;
import com.yd.hp.huangxiaoer.model.bean.LoginBean;
import com.yd.hp.huangxiaoer.model.bean.NickBean;
import com.yd.hp.huangxiaoer.model.bean.UserBean;
import com.yd.hp.huangxiaoer.model.bean.YuYueBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("user/login")
    Observable<LoginBean> getLogin(@Query("mobile") String mobile,@Query("password") String password);

    @GET("user/getUserInfo")
    Observable<UserBean> getUser(@Query("uid") String uid);

    @GET("user/updateNickName")
    Observable<NickBean> getNickname(@Query("uid") String uid, @Query("nickname") String nickname);

    @GET("home/getHome")
    Observable<HomeBean> getHome();

    @POST("file/upload")
    @Multipart
    Observable<FileBean> getFile(@Query("uid") String uid, @Part MultipartBody.Part file);

    //首页
    @GET("restaurants_offset_0_limit_4")
    Observable<YuYueBean> getYuYue();
    //首页加载更多
    @GET("restaurants_offset_0_limit_4")
    Observable<YuYueBean> getMove();
    /*//餐馆信息
    @GET("restaurant")
    Observable<CanBean> getCan();
    //购物车
    @GET("restaurant-list")
    Observable<ShopBean> getShop();*/
}
