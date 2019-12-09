package com.example.plb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.plb.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCentre extends AppCompatActivity {

    ListView listView;
    SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
    private int listicon[] = {R.mipmap.b1, R.mipmap.b2, R.mipmap.b3,R.mipmap.b4,R.mipmap.b5,R.mipmap.b6};
    private String listtxt1[] = {"自营和非自营","物流含义","隐私政策","自营售后政策","支付方式","购物流程"};
    private String listtxt2[] = {"订单拒收退款","订单提前配送","用户注册协议","自营售后运费","如何退款","必购码是什么"};
    private String listtxt3[] ={ "价格保护款项","特色配送服务","提升专享值","退款为何变少","白条还款违约","活动未享优惠"};
    private String listtxt4[] ={ "预售订单修改","物流是啥","专享值介绍","售后退款时间","优惠券使用","满返满赠含义"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicecentre);
        initview();
        setSimpleAdapter();
    }

    private void initview(){
        listView = findViewById(R.id.listview1);
    }

    private void setSimpleAdapter(){
        simpleAdapter = new SimpleAdapter(this,getDatalist(),
                R.layout.servercenter_item,
                new String[]{"img1","txtv1","txtv2","txtv3","txtv4"},
                new int[]{R.id.icon,R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4});
        listView.setAdapter(simpleAdapter);
    }

    private List<Map<String,Object>> getDatalist(){
        for(int i = 0;i<listicon.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img1",listicon[i]);
            map.put("txtv1",listtxt1[i]);
            map.put("txtv2",listtxt2[i]);
            map.put("txtv3",listtxt3[i]);
            map.put("txtv4",listtxt4[i]);
            datalist.add(map);
        }
        return datalist;
    }
}
