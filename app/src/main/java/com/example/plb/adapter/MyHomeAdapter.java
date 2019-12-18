package com.example.plb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.bean.HomeBean;
import com.example.plb.fragment.HomeFragment;

import java.util.List;

/**
 * Created by 123 on 2019/12/12.
 */

public class MyHomeAdapter extends RecyclerView.Adapter<MyHomeAdapter.ViewHolder>{
    private List<HomeBean> list;

    public MyHomeAdapter(List<HomeBean> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeBean homeBean=list.get(i);
        viewHolder.imageView.setBackgroundResource(homeBean.getImg_home());
        viewHolder.tv1.setText(homeBean.getText_home());
        viewHolder.tv2.setText(homeBean.getText_home2());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv1,tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img1);
            tv1 = itemView.findViewById(R.id.content1);
            tv2 = itemView.findViewById(R.id.content2);
        }
    }
}
