package com.example.plb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.plb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/9.
 */

public class SppinnerAdapter extends BaseAdapter {
    private Context ctx;
    private List<String> dataList;
    @Override
    public int getCount() {
        return dataList.size();
    }

    public SppinnerAdapter(Context ctx, List<String> dataList) {
        this.ctx = ctx;
        this.dataList = dataList;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(ctx)
                .inflate(R.layout.sppinner_item,null);
        TextView textView = convertView.findViewById(R.id.sp_txt);
        textView.setText(dataList.get(position));
        return convertView;
    }
}
