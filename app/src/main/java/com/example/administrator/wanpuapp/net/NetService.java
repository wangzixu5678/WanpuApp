package com.example.administrator.wanpuapp.net;

import com.example.administrator.wanpuapp.model.HomeModel;
import com.example.administrator.wanpuapp.model.NewsModel;

import java.io.FileInputStream;
import java.math.BigInteger;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface NetService {

    //发送手机号获取验证码
    @GET("/api/user/sendsms")
    Call<String> getSmsResult(@Query("phone")String phoneNumber);

    //获取主页信息
    @GET("/api/index")
    Call<HomeModel> getHomeInfo();

    //获取资讯信息
    @GET("/api/index/news")
    Call<NewsModel> getNewsInfo();

    //修改公司名称
    @GET("/api/user/changecompany")
    Call<String> changeCompanyName(@Query("id")String id,@Query("name")String name);

    //修改绑定手机
    @GET("/api/user/changephone")
    Call<String> changePhoneNumber(@Query("id")int id,@Query("phone")String phone);

    //向服务器上传头像
    @Multipart
    @POST("/api/user/changelogo")
    Call<String> upLoadPic(@Query("id")String id,@Part MultipartBody.Part image);

}
