package com.example.administrator.wanpuapp.utils;

import com.example.administrator.wanpuapp.net.NetService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/12/27.
 */

public final class NetUtil {
    private NetUtil(){

    }
    public static NetService getNetServices(){
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://api.10000pro.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetService service = retrofit.create(NetService.class);
        return service;
    }
}
