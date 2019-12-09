package com.example.plb.activity;

import android.os.Build;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.bean.Tab;
import com.example.plb.fragment.HomeFragment;
import com.example.plb.fragment.PersonalFragment;
import com.example.plb.tdy.Detailedlist.ShopCart;
import com.example.plb.tdy.Detailedlist.ShopCartFragment;
import com.example.plb.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Tab> mTab;
    private LayoutInflater mInflater;
    private FragmentTabHost mTabHost;
    private int mFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到当前界面的装饰视图
      /*  if(Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
           *//* getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //注意要清除 FLAG_TRANSLUCENT_STATUS flag
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.orange));*//*
        }*/
        getWindow().setStatusBarColor(getResources ().getColor ( R.color.colorPrimary ));
        setContentView(R.layout.activity_main);
        initTab();
    }

    private void initTab( ) {
        mTab = new ArrayList<>();

        mTab.add(new Tab(HomeFragment.class,R.string.home,R.drawable.selector_icon_home));
        mTab.add(new Tab(ShopFragment.class,R.string.shop,R.drawable.selector_icon_shop));
        mTab.add(new Tab(ShopCart.class,R.string.shopCart,R.drawable.selector_icon_shopcart));
        mTab.add(new Tab(PersonalFragment.class,R.string.personal,R.drawable.selector_icon_personal));

        mInflater = LayoutInflater.from(this);
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab : mTab) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabHost.addTab(tabSpec,tab.getFragment(),null);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);
    }

    private View buildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = view.findViewById(R.id.icon_tab);
        TextView text = view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getImg());
        text.setText(tab.getTitle());
        return view;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
             Window window = getWindow();
            //设置修改状态栏
             window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
             window.setStatusBarColor(getResources().getColor(R.color.orange));
        }
    }
}
