package com.example.administrator.wanpuapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.model.HomeModel;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyListviewAdapter extends BaseAdapter {
    private List<HomeModel.CompanyBean> mList;
    private Context mContext;

    public MyListviewAdapter(List<HomeModel.CompanyBean> list, Context context) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_company,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        HomeModel.CompanyBean companyBean = mList.get(position);
        holder.company_name.setText(companyBean.getName());
        holder.company_des.setText(companyBean.getDes());
        Glide.with(mContext)
                .load(companyBean.getImage())
                .placeholder(R.mipmap.banner)
                .into(holder.company_image);
        Glide.with(mContext)
                .load(companyBean.getLogo())
                .into(holder.company_logo);
        return convertView;
    }

    public static class ViewHolder{
        private ImageView company_logo;
        private ImageView company_image;
        private TextView company_name;
        private TextView company_des;

        public ViewHolder(View convertView){
            company_image = ((ImageView) convertView.findViewById(R.id.company_iamge));
            company_logo = ((ImageView) convertView.findViewById(R.id.company_logo));
            company_name = ((TextView) convertView.findViewById(R.id.company_name));
            company_des = ((TextView) convertView.findViewById(R.id.company_des));
        }
    }
}
