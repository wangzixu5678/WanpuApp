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
public class InformationFragment extends BaseFragment {


    @BindView(R.id.frag_information_listview)
    ListView mFragInformationListview;
    private ArrayList<NewsModel.NewsBean> mList;
    private MyMessageNewsAdapter mNewsAdapter;

    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        ButterKnife.bind(this, view);

        /**
         * 初始化ListviewAdapter
         */
        mList = new ArrayList<>();
        mNewsAdapter = new MyMessageNewsAdapter(mList, getContext());
        mFragInformationListview.setAdapter(mNewsAdapter);
        mNewsAdapter.notifyDataSetChanged();
        setInfromationNews();
        return view;
    }

    private void setInfromationNews() {
        NetService netServices = NetUtil.getNetServices();
        Call<NewsModel> newsInfo = netServices.getNewsInfo();
        newsInfo.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                List<NewsModel.NewsBean> news1 = newsModel.getNews1();
                mList.clear();
                mList.addAll(news1);
                mNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });
    }

    @Override
    public String getFragmentTitle() {
        return "行业资讯";
    }
}
