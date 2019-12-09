package com.example.plb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.plb.R;
import com.example.plb.bean.PaymentInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2019/1/12.
 */

public class PaymentAdapter extends BaseAdapter {

    List<PaymentInformation> data = new ArrayList<>();

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_payment_head, parent, false);
        }
        return null;
    }

}
