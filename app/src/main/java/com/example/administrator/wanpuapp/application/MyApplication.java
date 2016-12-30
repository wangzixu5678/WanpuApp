package com.example.administrator.wanpuapp.application;

import android.app.Application;

import com.example.administrator.wanpuapp.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

/**
 * Created by Administrator on 2016/12/26.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventBusBuilder builder = EventBus.builder();
        builder.installDefaultEventBus();
        SharedPreferencesUtil.init(getApplicationContext(),"wanpuhuanbao",MODE_PRIVATE);
    }
}
