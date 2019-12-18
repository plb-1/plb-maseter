package com.example.plb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.plb.activity.InformationActivity;
import com.example.plb.activity.ServiceCentre;
import com.example.plb.activity.StoreCertificationActivity;
import com.example.plb.activity.LoginPageActivity;
import com.example.plb.activity.OrderActivity;
import com.example.plb.R;
import com.example.plb.activity.SettingActivity;
import com.example.plb.activity.messages;
import com.example.plb.activity.mylistActivity;
import com.example.plb.bean.UserInfo;
import com.example.plb.tdy.Detailedlist.tdy_daifukuan;
import com.example.plb.tdy.Detailedlist.tdy_yifukuan;
import com.example.plb.tdy.Detailedlist.tdy_yiwanchen;
import com.example.plb.tdy.custom.RoundImageView;

/**
 * Created by zhc on 2018/12/27.
 * 个人中心
 */
public class PersonalFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RoundImageView personalHeadPortrait;
    private TextView personalUserName;
    private UserInfo userInfo;

    private String TAG = "PersonalFragment";
    private TextView personalUserId;
    private ImageView personalSetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal,null);
        initUI();
        initEven();
        return view;
    }

    private void initEven() {
        personalSetting.setOnClickListener(this);
    }

    //初始化控件
    private void initUI() {
        personalSetting = view.findViewById(R.id.personal_setting);//设置按钮
        personalHeadPortrait = view.findViewById(R.id.personal_head_portrait);//个人中心头像
        personalUserName = view.findViewById(R.id.personal_user_name);//用户名
        personalUserId = view.findViewById(R.id.personal_user_id);//用户ID

        userInfo = UserInfo.getInstance();
        Log.e(TAG, "initUI: hasUserInfo ---> "+userInfo.hasUserInfo());
        if (userInfo.hasUserInfo()) {
            //设置用户名
            if (!"".equals(userInfo.getUserName())) {
                personalUserName.setText(userInfo.getUserName());
            }else {
                personalUserName.setText(getContext().getResources().getString(R.string.per_user_name));
            }
           //设置ID
            if (userInfo.getUserId()!=0) {
                personalUserId.setText(String.valueOf(userInfo.getUserId()));
            }else {
                personalUserId.setText(getContext().getResources().getString(R.string.per_user_id));
            }
            //头像
            if (!"".equals(userInfo.getUserImg())) {
                Glide.with(this).load(userInfo.getUserImg()).into(personalHeadPortrait);
            }else {
                personalHeadPortrait.setBackground(getContext().getDrawable(R.mipmap.adg));
            }
        }
    }

    /**
     * 设置监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personal_setting://设置--
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
//            case R.id.rl_subscription://登录--
//                startActivity(new Intent(getActivity(), LoginPageActivity.class));
//                break;
//            case R.id.rl_dataManagement://消息--
//                startActivity(new Intent(getActivity(), InformationActivity.class));
//                break;
//            default:
//                break;
        }
    }
}
