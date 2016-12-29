package com.example.administrator.wanpuapp.fragments.resourcefms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.adapters.MyMessageNewsAdapter;
import com.example.administrator.wanpuapp.customview.MyListView;
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
public class PolicyFragment extends BaseFragment {


    @BindView(R.id.fragment_policy_listview)
    MyListView mFragmentPolicyListview;
    private MyMessageNewsAdapter mAdapter;
    private ArrayList<NewsModel.NewsBean> mList;

    public PolicyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_policy, container, false);
        ButterKnife.bind(this, view);
        mList = new ArrayList<>();
        mAdapter = new MyMessageNewsAdapter(mList, getContext());
        mFragmentPolicyListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        NetService netServices = NetUtil.getNetServices();
        Call<NewsModel> call = netServices.getNewsInfo();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel body = response.body();
                List<NewsModel.NewsBean> news4 = body.getNews4();
                mList.clear();
                mList.addAll(news4);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    public String getFragmentTitle() {
        return "政策法规";
    }
}
