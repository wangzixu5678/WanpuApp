package com.example.administrator.wanpuapp.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.adapters.MyBannerAdapter;
import com.example.administrator.wanpuapp.adapters.MyHomeNewsAdapter;
import com.example.administrator.wanpuapp.adapters.MyListviewAdapter;
import com.example.administrator.wanpuapp.adapters.MyRecycleViewAdapter;
import com.example.administrator.wanpuapp.customview.MyListView;
import com.example.administrator.wanpuapp.model.HomeModel;
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
public class HomeFragment extends Fragment {
    private static final int BANNERRUN = 998;
    @BindView(R.id.frag_home_listview)
    MyListView mFragHomeListview;
    @BindView(R.id.frag_home_pro_recycleview)
    RecyclerView mFragHomeProRecycleview;
    @BindView(R.id.frag_home_newslist)
    MyListView mFragHomeNewslist;
    private boolean running;
    @BindView(R.id.frag_home_viewpager)
    ViewPager mFragHomeViewpager;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case BANNERRUN:
                    int currentItem = mFragHomeViewpager.getCurrentItem();
                    mFragHomeViewpager.setCurrentItem((currentItem + 1) % mBannner.size());
                    break;
            }
            return true;
        }
    });
    private List<String> mBannner;
    private List<HomeModel.CompanyBean> mCompanys;
    private MyListviewAdapter mMyListviewAdapter;
    private ArrayList<HomeModel.ProduceBean> mProduces;
    private ArrayList<HomeModel.NewBean> mNews;
    private MyRecycleViewAdapter mRecycleViewAdapter;
    private MyHomeNewsAdapter mMyHomeNewsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = null;
        ret = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, ret);
        setNetBanner();
        /**
         * 展示公司信息
         */
        mCompanys = new ArrayList<>();
        mMyListviewAdapter = new MyListviewAdapter(mCompanys, getContext());
        mFragHomeListview.setAdapter(mMyListviewAdapter);
        mMyListviewAdapter.notifyDataSetChanged();
        /**
         * 设置Recycleview
         */
        mProduces = new ArrayList<>();
        mRecycleViewAdapter = new MyRecycleViewAdapter(mProduces, getContext());
        mFragHomeProRecycleview.setAdapter(mRecycleViewAdapter);
        mFragHomeProRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecycleViewAdapter.notifyDataSetChanged();
        /**
         * 展示新闻
         */
        mNews = new ArrayList<>();
        mMyHomeNewsAdapter = new MyHomeNewsAdapter(mNews, getContext());
        mFragHomeNewslist.setAdapter(mMyHomeNewsAdapter);
        mMyHomeNewsAdapter.notifyDataSetChanged();
        return ret;
    }

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    }

    private void setNetBanner() {
        /**
         * 获取主页信息
         */
        NetService netServices = NetUtil.getNetServices();
        Call<HomeModel> call = netServices.getHomeInfo();
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                HomeModel homeModel = response.body();
                mBannner = homeModel.getBannner();
                MyBannerAdapter adapter = new MyBannerAdapter(mBannner, getContext());
                mFragHomeViewpager.setAdapter(adapter);
                setBannerRun();//设置轮滑效果
                //设置Company数据
                mCompanys.clear();
                List<HomeModel.CompanyBean> list = homeModel.getCompany();
                mCompanys.addAll(list);
                mMyListviewAdapter.notifyDataSetChanged();
                //设置Produce数据
                List<HomeModel.ProduceBean> produce = homeModel.getProduce();
                mProduces.clear();
                mProduces.addAll(produce);
                mRecycleViewAdapter.notifyDataSetChanged();
                //设置新闻数据
                List<HomeModel.NewBean> newX = homeModel.getNewX();
                mNews.clear();
                mNews.addAll(newX);
                mMyHomeNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Log.d("HomeFragment", "onFailure:" + "服务器错误");
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
