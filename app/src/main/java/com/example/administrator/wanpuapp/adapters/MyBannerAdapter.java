package com.example.administrator.wanpuapp.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.wanpuapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyBannerAdapter extends PagerAdapter {
    private List<String> mList;
    private Context mContext;

    public MyBannerAdapter(List<String> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View ret = null;
        if (mList.size()!=0) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            String url = mList.get(position % mList.size());
            Glide.with(mContext)
                    .load(url)
                    .placeholder(R.mipmap.banner)
                    .error(R.mipmap.banner)
                    .into(imageView);
            container.addView(imageView);
            ret = imageView;
        }
        return ret;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }
}
