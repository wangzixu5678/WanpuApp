package com.example.administrator.wanpuapp.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.adapters.MyBannerAdapter;
import com.example.administrator.wanpuapp.adapters.MyFragmentAdapter;
import com.example.administrator.wanpuapp.fragments.resourcefms.BaseFragment;
import com.example.administrator.wanpuapp.fragments.resourcefms.InformationFragment;
import com.example.administrator.wanpuapp.fragments.resourcefms.LowCFragment;
import com.example.administrator.wanpuapp.fragments.resourcefms.PolicyFragment;
import com.example.administrator.wanpuapp.fragments.resourcefms.SaveEnvirFragment;
import com.example.administrator.wanpuapp.model.NewsModel;
import com.example.administrator.wanpuapp.net.NetService;
import com.example.administrator.wanpuapp.utils.NetUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private static final int BANNERRUN = 998;
    private boolean running;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case BANNERRUN:
                    int currentItem = mFragMessageBannerpager.getCurrentItem();
                    mFragMessageBannerpager.setCurrentItem((currentItem + 1) % mBanner.size());
                    break;
            }
            return true;
        }
    });
    @BindView(R.id.frag_message_tablayout)
    TabLayout mFragMessageTablayout;
    @BindView(R.id.frag_message_viewpager)
    ViewPager mFragMessageViewpager;
    @BindView(R.id.frag_message_bannerpager)
    ViewPager mFragMessageBannerpager;
    private List<String> mBanner;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = null;
        ret = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, ret);
        /**
         * 为Viewpager设置Adapter
         */
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        InformationFragment informationFragment = new InformationFragment();
        LowCFragment lowCFragment = new LowCFragment();
        SaveEnvirFragment saveEnvirFragment = new SaveEnvirFragment();
        PolicyFragment policyFragment = new PolicyFragment();
        fragments.add(informationFragment);
        fragments.add(lowCFragment);
        fragments.add(saveEnvirFragment);
        fragments.add(policyFragment);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), fragments);
        mFragMessageViewpager.setAdapter(adapter);
        mFragMessageTablayout.setupWithViewPager(mFragMessageViewpager);
        /**
         * 获取网络资讯数据
         */
        setNetMessage();
        return ret;
    }

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    }

    private void setNetMessage() {
        NetService netServices = NetUtil.getNetServices();
        Call<NewsModel> call = netServices.getNewsInfo();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                mBanner = newsModel.getBanner();
                MyBannerAdapter bannerAdapter = new MyBannerAdapter(mBanner, getContext());
                mFragMessageBannerpager.setAdapter(bannerAdapter);
                setBannerRun();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.d("MessageFragment", "onFailure: "+"链接服务器失败");

            }
        });
    }



    private void setBannerRun() {
        /**
         * 实现Banner轮滑效果
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                while (running) {
                    mHandler.sendEmptyMessage(BANNERRUN);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        thread.start();
    }

}
