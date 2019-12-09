package com.example.plb.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.activity.OrderActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/10 0010.
 */

public class MyShopFragment extends Fragment implements View.OnClickListener{
    private View view;
    private TextView shopLabel,basicInfo,partnerInfo;
    private ViewPager vp;
    private ShopLabelFragment shopLabelFragment;
    private BasicInfoFragment basicInfoFragment;
    private PartnerInfoFragment partnerInfoFragment;
    private List<Fragment> mfragmentList = new ArrayList<Fragment>();
    private FragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_shop,container,false);
        init();
        vp();
        return view;
    }

    private void init(){
        shopLabel = view.findViewById(R.id.shop_label);
        basicInfo = view.findViewById(R.id.basic_info);
        partnerInfo = view.findViewById(R.id.partner_info);
        shopLabel.setOnClickListener(this);
        basicInfo.setOnClickListener(this);
        partnerInfo.setOnClickListener(this);
        vp = view.findViewById(R.id.vp);
        shopLabelFragment = new ShopLabelFragment();
        basicInfoFragment = new BasicInfoFragment();
        partnerInfoFragment = new PartnerInfoFragment();
        //给FragmentList添加数据
        mfragmentList.add(shopLabelFragment);
        mfragmentList.add(basicInfoFragment);
        mfragmentList.add(partnerInfoFragment);

        adapter = new FragmentAdapter(getFragmentManager(),mfragmentList);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(4); //ViewPager的缓存为4帧
        vp.setCurrentItem(0);  //初始设置ViewPager选中第一帧

        shopLabel.setTextColor(getResources().getColor(R.color.orange));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_label:
                vp.setCurrentItem(0,true);
                break;
            case R.id.basic_info:
                vp.setCurrentItem(1,true);
                break;
            case R.id.partner_info:
                vp.setCurrentItem(2,true);
                break;
            default:
                break;
        }
    }

    public class FragmentAdapter extends FragmentPagerAdapter{

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
    //ViewPager的监听事件
    private void vp(){
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                 changeTextColor(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void changeTextColor(int position){
        if (position == 0){
            shopLabel.setTextColor(getResources().getColor(R.color.orange));
            basicInfo.setTextColor(getResources().getColor(R.color.deep_gray));
            partnerInfo.setTextColor(getResources().getColor(R.color.deep_gray));
        }
        if (position == 1){
            shopLabel.setTextColor(getResources().getColor(R.color.deep_gray));
            basicInfo.setTextColor(getResources().getColor(R.color.orange));
            partnerInfo.setTextColor(getResources().getColor(R.color.deep_gray));

        }
        if (position == 2){
            shopLabel.setTextColor(getResources().getColor(R.color.deep_gray));
            basicInfo.setTextColor(getResources().getColor(R.color.deep_gray));
            partnerInfo.setTextColor(getResources().getColor(R.color.orange));
        }
    }
}
