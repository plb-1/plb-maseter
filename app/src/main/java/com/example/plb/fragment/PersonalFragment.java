package com.example.plb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.plb.activity.InformationActivity;
import com.example.plb.activity.ServiceCentre;
import com.example.plb.activity.StoreCertificationActivity;
import com.example.plb.activity.LoginPageActivity;
import com.example.plb.activity.OrderActivity;
import com.example.plb.R;
import com.example.plb.activity.SettingActivity;
import com.example.plb.activity.messages;
import com.example.plb.activity.mylistActivity;
import com.example.plb.tdy.Detailedlist.tdy_daifukuan;
import com.example.plb.tdy.Detailedlist.tdy_yifukuan;
import com.example.plb.tdy.Detailedlist.tdy_yiwanchen;

/**
 * Created by zhc on 2018/12/27.
 * 个人中心
 */
public class PersonalFragment extends Fragment implements View.OnClickListener{

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal,null);
        init();
        return view;
    }

    //初始化控件
    private void init() {
        ImageButton rl_myOrder = view.findViewById(R.id.rl_myOrder);
        ImageButton rl_setting = view.findViewById(R.id.rl_setting);
        ImageView rl_subscription = view.findViewById(R.id.rl_subscription);
        ImageButton rl_collection = view.findViewById(R.id.rl_collection);
        ImageButton rl_dataManagement = view.findViewById(R.id.rl_dataManagement);

        ImageButton id_Pending_payment = view.findViewById(R.id.id_Pending_payment);
        ImageButton id_Bypayment = view.findViewById(R.id.id_Bypayment);
        ImageButton ordertocomplete = view.findViewById(R.id.ordertocomplete);
        ImageButton mylist = view.findViewById(R.id.mylist);
        ImageButton servicecentre = view.findViewById(R.id.servicecentre);
        ImageButton messages = view.findViewById(R.id.messages);

        rl_myOrder.setOnClickListener(this);
        rl_setting.setOnClickListener(this);
        rl_subscription.setOnClickListener(this);
        rl_collection.setOnClickListener(this);
        rl_dataManagement.setOnClickListener(this);
        mylist.setOnClickListener(this);
        servicecentre.setOnClickListener(this);
        messages.setOnClickListener(this);

        id_Pending_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("current",0);
                startActivity(intent);
            }
        });

        id_Bypayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("current",1);
                startActivity(intent);
            }
        });
        ordertocomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(), OrderActivity.class);
//                intent.putExtra("current",1);
//                startActivity(intent);
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("current",2);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_myOrder://我的订单
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("current",0);
                startActivity(intent);
                break;
            case R.id.rl_setting://设置--
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.rl_subscription://登录--
                startActivity(new Intent(getActivity(), LoginPageActivity.class));
                break;
            case R.id.rl_collection://收藏--
                startActivity(new Intent(getActivity(), StoreCertificationActivity.class));
                break;
            case R.id.rl_dataManagement://消息--
                startActivity(new Intent(getActivity(), InformationActivity.class));
                break;
            case R.id.mylist:/*我的清单*/
                startActivity(new Intent(getActivity(), mylistActivity.class));
                break;
            case R.id.servicecentre:/*服务中心*/
                startActivity(new Intent(getActivity(), ServiceCentre.class));
                break;
            case R.id.messages:/*消息*/
                startActivity(new Intent(getActivity(), messages.class));
                break;
            default:
                break;
        }
    }
}
