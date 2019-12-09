package com.example.plb.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.plb.R;
import com.example.plb.adapter.OrderAdapter;
import com.example.plb.tdy.message_Fragment1;
import com.example.plb.tdy.message_Fragment2;

import java.util.ArrayList;
import java.util.List;

public class messages extends AppCompatActivity implements View.OnClickListener{

    private ViewPager vp_viewPager;
    private ImageView imageView1,imageView2;
    private List<Fragment> list;
    private int CurrentItem = 0;
    private OrderAdapter adapter;

    private message_Fragment1 message_fragment1 = new message_Fragment1();
    private message_Fragment2 message_fragment2 = new message_Fragment2();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        initView();
        initFragment();
    }

    private void initView(){
        vp_viewPager = findViewById(R.id.vp_viewPager);
        imageView1 = findViewById(R.id.message1);
        imageView2 = findViewById(R.id.message2);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
    }

    private void initFragment(){
        //把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(message_fragment1);
        list.add(message_fragment2);
        adapter = new OrderAdapter(getSupportFragmentManager(), list);
        vp_viewPager.setAdapter(adapter);
        vp_viewPager.setCurrentItem(CurrentItem);  //初始化显示第一个页面
    }

    /**
     * 点击Text动态切换Fragment
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message1:
                vp_viewPager.setCurrentItem(0);
                break;
            case R.id.message2:
                vp_viewPager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }
}
