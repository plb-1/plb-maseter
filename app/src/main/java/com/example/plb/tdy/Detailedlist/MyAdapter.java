package com.example.plb.tdy.Detailedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.tdy.Sqlite.bean.shopinfos;

public class MyAdapter extends BaseAdapter {
    // 填充数据的list
    private List<shopinfos> list;
    public void setList(List<shopinfos> list) {
        this.list = list;
    }
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    // 上下文
    private Context context;
    // 用来导入布局
    private LayoutInflater inflater = null;

    List<String>shopname = new ArrayList<String>();
    List<Integer>shopprice = new ArrayList<Integer>();
    List<Integer>shopbynum = new ArrayList<Integer>();
    List<Integer>shopid = new ArrayList<Integer>();
    boolean shopstate = false;
    // 构造器
    public MyAdapter(List<shopinfos> addListDate, Context context) {
        this.context = context;
        this.list = addListDate;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < list.size(); i++) {
                getIsSelected().put(i, false);
                if(list.get(i).getIspayment()==2){
                    shopstate = true;
                }
        }
        shopname.clear();
        shopprice.clear();
        shopbynum.clear();
        shopid.clear();
        for(int i=0;i<list.size();i++){
            shopname.add(list.get(i).getShopbynum()+"");
            shopprice.add((list.get(i).getshopprice()*list.get(i).getShopbynum()));
            shopbynum.add(list.get(i).getShopbynum());
            shopid.add(list.get(i).getShopid());
        }
    }


    public void myinitDate(){
        getIsSelected().clear();
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
            if(list.get(i).getIspayment()==2){
                shopstate = true;
            }
        }
        shopname.clear();
        shopprice.clear();
        shopbynum.clear();
        shopid.clear();
        for(int i=0;i<list.size();i++){
            shopname.add(list.get(i).getShopbynum()+"");
            shopprice.add((list.get(i).getshopprice()*list.get(i).getShopbynum()));
            shopbynum.add(list.get(i).getShopbynum());
            shopid.add(list.get(i).getShopid());
        }
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            // 导入布局并赋值给convertview
            convertView = inflater.inflate(R.layout.tdy_shopinf_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.buynum = (TextView) convertView.findViewById(R.id.buynum);
            holder.img =  convertView.findViewById(R.id.shopimg);
            holder.thisprice = (TextView) convertView.findViewById(R.id.thisprice);
            holder.cb = (CheckBox) convertView.findViewById(R.id.ckd);
            holder.thisstate = (TextView) convertView.findViewById(R.id.thisstate);
            holder.Deliverystatus = (TextView) convertView.findViewById(R.id.Deliverystatus);
            holder.shopid = (TextView) convertView.findViewById(R.id.shopid);
            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }
        if(shopstate){
            holder.thisstate.setBackgroundResource(R.drawable.bgcolor_radios2);
            holder.thisstate.setText("以付款");
            if(list.get(0).getDeliverystatus()==2){
                holder.Deliverystatus.setText("以发货");
            }
        }
        // 设置list中TextView的显示
        holder.name.setText(list.get(position).getName());
        holder.img.setImageResource(R.mipmap.example_1);
        holder.thisprice.setText(list.get(position).getshopprice()+"");
        holder.buynum.setText(list.get(position).getShopbynum()+"");
        holder.shopid.setText(list.get(position).getShopid()+"");
        // 根据isSelected来设置checkbox的选中状况
        //Log.e("getIsSelected", getIsSelected().get(position)+"" );
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        MyAdapter.isSelected = isSelected;
    }

    public static class ViewHolder {
        TextView thisstate;
        TextView Deliverystatus;
        TextView name;
        ImageView img;
        TextView thisprice;
        TextView buynum;
        CheckBox cb;
        TextView shopid;
    }
}