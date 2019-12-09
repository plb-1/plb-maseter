package com.example.plb.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.adapter.MyListAdapter;
import com.example.plb.adapter.TradeAdapter;
import com.example.plb.bean.FirmOrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    Button quxiao,queren;
    ImageView back;
    ListView listView;
    private List<FirmOrderItem> items;
    private List<String> str;
    private TradeAdapter myListAdapter;
    TextView sum_moeny;
    int sum=0,firm_num,firm_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_order_details);
        initView();
        initData();
    }
    private void initView(){
        back=findViewById(R.id.details_back);
        back.setOnClickListener(this);
        listView= findViewById(R.id.details_listview);
        queren = findViewById(R.id.details_queren);
        queren.setOnClickListener(this);
        quxiao = findViewById(R.id.details_quxiao);
        quxiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back:
                finish();
                break;
            case R.id.details_queren:
                break;
            case R.id.details_quxiao:
                break;
        }
    }
    private void initData(){
        items = new ArrayList<>();
        str=new ArrayList<>();
        str.add("香草味");
        str.add("巧克力味");
        firm_num = 2;
        firm_money = 39;
        items.add(new FirmOrderItem(R.mipmap.example_1,"安慕希牛奶",firm_num,str,firm_money));
        items.add(new FirmOrderItem(R.mipmap.example_1,"安慕希牛奶",firm_num,str,firm_money));
        myListAdapter = new TradeAdapter(this,items);
        listView.setAdapter(myListAdapter);
    }

}
