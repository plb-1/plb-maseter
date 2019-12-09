package com.example.plb.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.bean.ShopMarket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/1/13.
 */

public class MyGridView extends GridView {
    private Context mContext;
    private List<ShopMarket> marketList = new ArrayList<>();
    private MyGridAdapter myGridAdapter;

    public MyGridView(Context context, AttributeSet attrs) {
        super(context,attrs);
        mContext = context;

        init();
        initData();
    }

    private void init(){
        myGridAdapter = new MyGridAdapter();
        this.setAdapter(myGridAdapter);
    }

    private void initData(){
        //测试
        for (int i=0;i<8;i++){
            marketList.add(new ShopMarket("五一市场"));
            marketList.add(new ShopMarket("六一市场"));
            marketList.add(new ShopMarket("七一市场"));
        }
    }

    class MyGridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return marketList.size();
        }

        @Override
        public Object getItem(int position) {
            return marketList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ShopMarket holder;
            if (convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_right_menu_market,null);

                holder = marketList.get(position);
                holder.mMarketView = convertView.findViewById(R.id.item_right_menu_market_text);
                holder.mMarketView.setText(holder.getMarket());

                convertView.setTag(holder);
            }else {
                holder = (ShopMarket) convertView.getTag();
            }

            return convertView;
        }
    }
}
