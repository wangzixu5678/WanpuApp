package com.example.administrator.wanpuapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.model.HomeModel;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.R.attr.format;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyHomeNewsAdapter extends BaseAdapter {
    private List<HomeModel.NewBean> mList;
    private Context mContext;

    public MyHomeNewsAdapter(List<HomeModel.NewBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        HomeModel.NewBean newBean = mList.get(position);
        holder.news_title.setText(newBean.getTitle());
        /**
         * 时间格式的转换
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder builder = new StringBuilder(String.valueOf(newBean.getCreate_time()));
        builder.append("000");
        String string = builder.toString();
        Date date = new Date(Long.parseLong(string));
        String formatDate = format.format(date);
        holder.news_time.setText(formatDate);

        Glide.with(mContext)
                .load(newBean.getImg())
                .placeholder(R.mipmap.banner)
                .into(holder.news_image);

        return convertView;
    }

    public static class ViewHolder{
        private ImageView news_image;
        private TextView news_title;
        private TextView news_time;

        public ViewHolder(View convertView){
            news_image = ((ImageView) convertView.findViewById(R.id.news_image));
            news_title = ((TextView) convertView.findViewById(R.id.news_title));
            news_time = ((TextView) convertView.findViewById(R.id.news_time));
        }
    }
}
