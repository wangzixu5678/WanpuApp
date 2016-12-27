package com.example.administrator.wanpuapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.wanpuapp.fragments.resourcefms.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mList;
    public MyFragmentAdapter(FragmentManager fm,List<BaseFragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getFragmentTitle();
    }
}
