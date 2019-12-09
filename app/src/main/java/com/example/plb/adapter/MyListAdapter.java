package com.example.plb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.activity.FirmOrder;
import com.example.plb.bean.FirmOrderItem;
import com.example.plb.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/3.
 */

public class MyListAdapter extends BaseAdapter{
    private Context context;
    private List<FirmOrderItem> items;
    private FirmOrder firmOrder;
    private TextView textView;
    TextView money;
   int sum =0,frim_sum=0;
    public MyListAdapter(Context context, List<FirmOrderItem> items,TextView textView) {
        this.context = context;
        this.items = items;
        this.textView=textView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =LayoutInflater.from(context)
                .inflate(R.layout.activity_firm_order,null);
        convertView = LayoutInflater.from(context)
                .inflate(R.layout.listview_item,null);
            ImageView imageView = convertView.findViewById(R.id.firm_img);
             money= convertView.findViewById(R.id.firm_sum);
            CheckBox ck = convertView.findViewById(R.id.firm_ck);
            TextView num = convertView.findViewById(R.id.firm_num);
            TextView txt = convertView.findViewById(R.id.firm_txt);
            TextView txt_sum = convertView.findViewById(R.id.firm_txt_sum);
            Spinner spinner =  convertView.findViewById(R.id.item_spinner);
            spinner.setAdapter(new SppinnerAdapter(context,items.get(position).getSp()));
            spinner.setSelection(0);
            imageView.setImageResource(items.get(position).getFirm_imgs());
            num.setText(items.get(position).getFirm_num()+"");
             sum = items.get(position).getFirm_num() * items.get(position).getFirm_money();
             frim_sum+=sum;
            money.setText(sum+"");
            Log.e("------", textView.getText().toString() );
            textView.setText(frim_sum+"");
            Log.e("------", textView.getText().toString() );
            ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        sum +=12;
                        frim_sum+=12;
                        money.setText(sum+"");
                        textView.setText(frim_sum+"");
                    }else {
                        sum -=12;
                        frim_sum-=12;
                        money.setText(sum+"");
                        textView.setText(frim_sum+"");
                    }
                }
            });

            txt.setText(items.get(position).getFrim_txt());
            txt_sum.setText("共"+items.get(position).getFirm_num()+"件商品 小计:");

        return convertView;
    }
  /*  private List<String> getData() {
        // 数据源
        List<String> dataList = new ArrayList<String>();
        for (int i=0;i<items.size();i++){

        }
        return dataList;
    }*/
}
