
package com.example.plb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.plb.R;
import com.example.plb.bean.ShopStore_home;

import java.util.ArrayList;
import java.util.List;

public class ShopStoreAdapter_home extends BaseAdapter {

    private final Context context;
    private final List<ShopStore_home> datas;


    public ShopStoreAdapter_home(Context context,ShopStore_home datas){
        this.context=context;
        this.datas= (List<ShopStore_home>) datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView=View.inflate(context, R.layout.item_shop_home,null);
            viewHolder = new ViewHolder();
            viewHolder.shop_photo=convertView.findViewById(R.id.shop_photo);
            viewHolder.iv_time=convertView.findViewById(R.id.shizhong);
            viewHolder.iv_laba=convertView.findViewById(R.id.laba);
            viewHolder.iv1=convertView.findViewById(R.id.shop_image1_home);
            viewHolder.iv2=convertView.findViewById(R.id.shop_image2_home);
            viewHolder.iv3=convertView.findViewById(R.id.shop_image3_home);
            viewHolder.iv4=convertView.findViewById(R.id.shop_image4_home);
            viewHolder.shop_name=convertView.findViewById(R.id.shop_name_home);
            viewHolder.shop_optime=convertView.findViewById(R.id.shop_optime_home);
            viewHolder.shop_ovtime=convertView.findViewById(R.id.shop_ovtime_home);
            viewHolder.shop_nf=convertView.findViewById(R.id.shop_nf_home);
            viewHolder.price1=convertView.findViewById(R.id.shop_price1_home);
            viewHolder.price2=convertView.findViewById(R.id.shop_price2_home);
            viewHolder.price3=convertView.findViewById(R.id.shop_price3_home);
            viewHolder.price4=convertView.findViewById(R.id.shop_price4_home);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //根据位置得到数据
        ShopStore_home shopStore_home= datas.get(position);
        Glide.with(context).load(shopStore_home.getRecommendedshop().getStore().get(position).getLogoUrl()).into(viewHolder.shop_photo);
        viewHolder.shop_name.setText(shopStore_home.getRecommendedshop().getStore().get(position).getStoreName());
        viewHolder.shop_optime.setText(shopStore_home.getRecommendedshop().getStore().get(position).getOpenTime()+"--");
        viewHolder.shop_ovtime.setText(shopStore_home.getRecommendedshop().getStore().get(position).getCloseTime());
        viewHolder.shop_nf.setText(shopStore_home.getRecommendedshop().getStore().get(position).getSlogan());
        return convertView;
    }
    static class ViewHolder{
        ImageView shop_photo,iv_time,iv_laba,iv1,iv2,iv3,iv4;
        TextView shop_name,shop_optime,shop_ovtime,shop_nf,price1,price2,price3,price4;
    }
}
