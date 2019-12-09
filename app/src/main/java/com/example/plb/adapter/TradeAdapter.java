package com.example.plb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.bean.FirmOrderItem;

import java.util.List;

/**
 * Created by Administrator on 2019/1/12.
 */

public class TradeAdapter extends BaseAdapter {
    private Context context;
    private List<FirmOrderItem> items;
    TextView money;
    int sum = 0;

    public TradeAdapter(Context context, List<FirmOrderItem> items) {
        this.context = context;
        this.items = items;
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
        money.setText(sum+"");
        txt.setText(items.get(position).getFrim_txt());
        txt_sum.setText("共"+items.get(position).getFirm_num()+"件商品 小计:");
        return convertView;
    }
}
