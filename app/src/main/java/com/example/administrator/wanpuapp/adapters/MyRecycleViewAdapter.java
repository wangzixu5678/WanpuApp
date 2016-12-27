package com.example.administrator.wanpuapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wanpuapp.R;
import com.example.administrator.wanpuapp.model.HomeModel;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter {

    private List<HomeModel.ProduceBean> mList;
    private static Context mContext;

    public MyRecycleViewAdapter(List<HomeModel.ProduceBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_produce,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.bindView(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
        public void bindView(HomeModel.ProduceBean produceBean){
            ImageView produce_image = (ImageView) itemView.findViewById(R.id.produce_image);
            TextView produce_name = (TextView) itemView.findViewById(R.id.produce_name);
            produce_name.setText(produceBean.getGoods());
            Glide.with(mContext).load(produceBean.getSmall_img()).into(produce_image);
        }
    }
}
