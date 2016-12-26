package com.example.administrator.wanpuapp.net;

import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface NetService {
    @GET("/api/user/sendsms")
    Call<String> getSmsResult(@Query("phone")String phoneNumber);
}
