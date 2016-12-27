package com.example.administrator.wanpuapp.net;

import com.example.administrator.wanpuapp.model.HomeModel;

import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
