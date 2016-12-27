package com.example.administrator.wanpuapp.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.adapters.MyFragmentAdapter;
import com.example.administrator.wanpuapp.fragments.resourcefms.BaseFragment;
import com.example.administrator.wanpuapp.fragments.resourcefms.InformationFragment;
import com.example.administrator.wanpuapp.fragments.resourcefms.LowCFragment;
import com.example.administrator.wanpuapp.fragments.resourcefms.PolicyFragment;
import com.example.administrator.wanpuapp.fragments.resourcefms.SaveEnvirFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    @BindView(R.id.frag_message_tablayout)
    TabLayout mFragMessageTablayout;
    @BindView(R.id.frag_message_viewpager)
    ViewPager mFragMessageViewpager;

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
        return ret;
    }

}
