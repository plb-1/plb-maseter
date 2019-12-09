package com.example.plb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 我的订单
 * Created by asus on 2019/1/16.
 */

public class OrderAdapter extends FragmentPagerAdapter{

    private FragmentManager fragmentManager;
    private List<Fragment> data;

    public OrderAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int arg0) {
        return data.get(arg0);//显示第几个页面
    }

    @Override
    public int getCount() {
        return data.size();//有几个页面
    }

}
