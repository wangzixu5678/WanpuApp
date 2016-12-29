package com.example.administrator.wanpuapp.fragments.resourcefms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.adapters.MyHomeNewsAdapter;
import com.example.administrator.wanpuapp.adapters.MyMessageNewsAdapter;
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
public class SaveEnvirFragment extends BaseFragment {


    @BindView(R.id.frag_save_listview)
    ListView mFragSaveListview;
    private ArrayList<NewsModel.NewsBean> mList;
    private MyMessageNewsAdapter mAdapter;

    public SaveEnvirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save_envir, container, false);
        ButterKnife.bind(this, view);
        mList = new ArrayList<>();
        mAdapter = new MyMessageNewsAdapter(mList,getContext());
        mFragSaveListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        getNetSave();
        return view;
    }

    private void getNetSave() {
        NetService netServices = NetUtil.getNetServices();
        Call<NewsModel> call = netServices.getNewsInfo();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                List<NewsModel.NewsBean> news3 = newsModel.getNews3();
                mList.clear();
                mList.addAll(news3);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });
    }

    @Override
    public String getFragmentTitle() {
        return "节能环保";
    }
}
