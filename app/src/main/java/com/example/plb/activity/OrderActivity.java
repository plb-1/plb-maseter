package com.example.plb.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.adapter.OrderAdapter;
import com.example.plb.tdy.Detailedlist.HasCompleteFragment;
import com.example.plb.tdy.Detailedlist.HasPaymentFragment;
import com.example.plb.tdy.Detailedlist.StayPaymentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单
 */
public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_stayPayment;
    private TextView tv_hasPayment;
    private TextView tv_hasComplete;
    private ViewPager vp_viewPager;
    private List<Fragment> list;
    private OrderAdapter adapter;
    private ImageView iv_exit;

    HasPaymentFragment hasPaymentFragment =  new HasPaymentFragment();
    HasCompleteFragment hasCompleteFragment =  new HasCompleteFragment();
    StayPaymentFragment stayPaymentFragment =  new StayPaymentFragment();

    private int CurrentItem = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initUI();

        //获取初始显示的页数
        Bundle bundle = this.getIntent().getExtras();
        CurrentItem = bundle.getInt("current");

        //设置菜单栏的点击事件
        tv_stayPayment.setOnClickListener(this);
        tv_hasPayment.setOnClickListener(this);
        tv_hasComplete.setOnClickListener(this);
        iv_exit.setOnClickListener(this);
        vp_viewPager.addOnPageChangeListener(new MyPagerChangeListener());
        //把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(stayPaymentFragment);
        list.add(hasPaymentFragment);
        list.add(hasCompleteFragment);
        adapter = new OrderAdapter(getSupportFragmentManager(), list);
        vp_viewPager.setAdapter(adapter);
        vp_viewPager.setCurrentItem(CurrentItem);  //初始化显示第一个页面
        if(CurrentItem==0){
            tv_stayPayment.setTextColor(Color.parseColor("#ffffff"));  //选中第一个颜色的字体
            tv_stayPayment.setBackgroundColor(Color.parseColor("#ed6a1a"));  //选中第一个颜色的背景
        }else if(CurrentItem==1){
            tv_hasPayment.setTextColor(Color.parseColor("#ffffff"));  //选中第一个颜色的字体
            tv_hasPayment.setBackgroundColor(Color.parseColor("#ed6a1a"));  //选中第一个颜色的背景
        }else{
            tv_hasComplete.setTextColor(Color.parseColor("#ffffff"));  //选中第一个颜色的字体
            tv_hasComplete.setBackgroundColor(Color.parseColor("#ed6a1a"));  //选中第一个颜色的背景
        }
    }


    /**
     * 初始化控件
     */
    private void initUI() {
        tv_stayPayment = findViewById(R.id.tv_stayPayment);
        tv_hasPayment = findViewById(R.id.tv_hasPayment);
        tv_hasComplete = findViewById(R.id.tv_hasComplete);
        vp_viewPager = findViewById(R.id.vp_viewPager);
        iv_exit = findViewById(R.id.iv_exit);
    }

    /**
     * 点击Text动态切换Fragment
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_stayPayment:
                vp_viewPager.setCurrentItem(0);
                changeTextColorAndBackgroundColor(0);
                break;
            case R.id.tv_hasPayment:
                vp_viewPager.setCurrentItem(1);
                changeTextColorAndBackgroundColor(1);
                break;
            case R.id.tv_hasComplete:
                vp_viewPager.setCurrentItem(2);
                changeTextColorAndBackgroundColor(2);
                break;
            case R.id.iv_exit:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 设置一个ViewPager的侦听事件，当左右滑动ViewPager时菜单栏被选中状态跟着改变
     */
    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    changeTextColorAndBackgroundColor(0);
                    break;
                case 1:
                    changeTextColorAndBackgroundColor(1);
                    break;
                case 2:
                    changeTextColorAndBackgroundColor(2);
                    break;
            }
        }
    }

    /**
     * 根据position改变选中状态的文本和背景颜色
     * @param position
     */
    private void changeTextColorAndBackgroundColor(int position) {
        if (position == 0) {
            tv_stayPayment.setTextColor(Color.parseColor("#ffffff"));
            tv_stayPayment.setBackgroundColor(Color.parseColor("#ed6a1a"));
            tv_hasPayment.setTextColor(Color.parseColor("#000000"));
            tv_hasPayment.setBackgroundColor(Color.parseColor("#bdb8b4"));
            tv_hasComplete.setTextColor(Color.parseColor("#000000"));
            tv_hasComplete.setBackgroundColor(Color.parseColor("#bdb8b4"));
        } else if (position == 1) {
            tv_stayPayment.setTextColor(Color.parseColor("#000000"));
            tv_stayPayment.setBackgroundColor(Color.parseColor("#bdb8b4"));
            tv_hasPayment.setTextColor(Color.parseColor("#ffffff"));
            tv_hasPayment.setBackgroundColor(Color.parseColor("#ed6a1a"));
            tv_hasComplete.setTextColor(Color.parseColor("#000000"));
            tv_hasComplete.setBackgroundColor(Color.parseColor("#bdb8b4"));
        } else if (position == 2) {
            tv_stayPayment.setTextColor(Color.parseColor("#000000"));
            tv_stayPayment.setBackgroundColor(Color.parseColor("#bdb8b4"));
            tv_hasPayment.setTextColor(Color.parseColor("#000000"));
            tv_hasPayment.setBackgroundColor(Color.parseColor("#bdb8b4"));
            tv_hasComplete.setTextColor(Color.parseColor("#ffffff"));
            tv_hasComplete.setBackgroundColor(Color.parseColor("#ed6a1a"));
        }
    }
}
