package com.example.administrator.wanpuapp.fragments.resourcefms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.wanpuapp.R;

import com.example.administrator.wanpuapp.adapters.LowcNewsAdapter;
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
public class LowCFragment extends BaseFragment {


    @BindView(R.id.frag_lowc_listview)
    ListView mFragLowcListview;
    private List<NewsModel.NewsBean> mList;
    private LowcNewsAdapter mAdapter;

    public LowCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ret = null;
        ret = inflater.inflate(R.layout.fragment_low_c, container, false);
        ButterKnife.bind(this, ret);
        mList = new ArrayList<>();
        mAdapter = new LowcNewsAdapter(mList, getContext());
        mFragLowcListview.setAdapter(mAdapter);
        setNetLowc();
        return ret;
    }

    private void setNetLowc() {
        NetService netServices = NetUtil.getNetServices();
        Call<NewsModel> call = netServices.getNewsInfo();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                List<NewsModel.NewsBean> news2 = newsModel.getNews2();
                mList.clear();
                mList.addAll(news2);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.d("LowcFragment", "onFailure: "+"连接服务器失败");

            }
        });
    }

    @Override
    public String getFragmentTitle() {
        return "低碳生活";
    }
}
