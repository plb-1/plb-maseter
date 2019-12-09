package com.example.plb.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
import com.example.plb.adapter.MyListAdapter;
import com.example.plb.bean.FirmOrderItem;
import com.example.plb.bean.MoneyBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//确认订单
public class FirmOrder extends AppCompatActivity implements View.OnClickListener{
    ImageView back;
    ListView listView;
    private List<FirmOrderItem> items;
    private List<String> str;
    private MyListAdapter myListAdapter;
    TextView sum_moeny;
    Button btn;
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
        setContentView(R.layout.activity_firm_order);

        initView();
        initData();

    }
    private void initView(){
        back=findViewById(R.id.order_back);
        back.setOnClickListener(this);
        listView= findViewById(R.id.firm_order_listview);
        sum_moeny = findViewById(R.id.firm_order_money);
        btn = findViewById(R.id.firm_order_settlement);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_back:
                finish();
                break;
            case R.id.firm_order_settlement:
                Intent intent = new Intent(this,PaymentActivity.class);
                MoneyBean moneyBean = new MoneyBean();
                moneyBean.setMoney(Integer.parseInt(sum_moeny.getText().toString()));
                intent.putExtra("money",moneyBean);
                startActivity(intent);
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
        myListAdapter = new MyListAdapter(this,items,sum_moeny);
        listView.setAdapter(myListAdapter);
    }

    public FirmOrder() {
    }

    public FirmOrder(int i) {
        this.sum = i;
    }
}
