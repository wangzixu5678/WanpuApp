package com.example.administrator.wanpuapp.fragments.resourcefms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wanpuapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveEnvirFragment extends BaseFragment {


    public SaveEnvirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_envir, container, false);
    }

    @Override
    public String getFragmentTitle() {
        return "节能环保";
    }
}
