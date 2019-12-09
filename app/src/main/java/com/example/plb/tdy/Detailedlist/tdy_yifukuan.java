package com.example.plb.tdy.Detailedlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
import com.example.plb.tdy.Sqlite.Constant;
import com.example.plb.tdy.Sqlite.MysqliteHelper;
import com.example.plb.tdy.Sqlite.bean.shopinfos;
import com.example.plb.tdy.Sqlite.util.DBManger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * Created by hp on 2018/11/29.
 */

public class tdy_yifukuan extends FragmentActivity {

    Context mContext;

    private ListView PendingpaymentList;
    private ImageView imageView;
    private Button jiesuan;
    private CheckBox ckdall;
    private TextView sumprice;
    private int price;
    private int checkNum; // 记录选中的条目数量

    private MysqliteHelper helper;
    private SQLiteDatabase db = null;

    private List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
    private SimpleAdapter simpleAdapter;
    private MyAdapter mAdapter;

    List<shopinfos> list;
    ArrayList<String> list2;
    List<Integer> listItemID = new ArrayList<Integer>();
    List<String> listItemString = new ArrayList<String>();
    boolean isqianshou = true;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tdy_yifukuan);


        mContext = getApplicationContext();
        initViews();
        initSqlite();
        if(selectShop().size()>0){
            imageView.setVisibility(View.GONE);
            setadapter();
            jiesuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuilder sb = new StringBuilder();

                    for(int i=0;i<MyAdapter.getIsSelected().size();i++){
                        if(MyAdapter.getIsSelected().get(i)){
                            sb.append("ItemID="+mAdapter.shopname.get(i)+" . ");

                            int bynums = mAdapter.shopbynum.get(i);
                            String names = mAdapter.shopname.get(i);
                            String id = mAdapter.shopid.get(i).toString();
                            String byn = String.valueOf(bynums);
                            if( updates(id)<2){
                                isqianshou = false;
                            };
                        }
                    }
                    if(isqianshou)Toast.makeText(mContext,"签收成功",Toast.LENGTH_SHORT).show();
                    mAdapter = null;
                    mAdapter = new MyAdapter(selectShop(),mContext);
                    mAdapter.notifyDataSetChanged();
                    PendingpaymentList.setAdapter(mAdapter);
                }
            });
        }

        /* 实例化各个控件 */
        //tv_show = (TextView) findViewById(R.id.tv);
        list2 = new ArrayList<String>();


        // 全选按钮的回调接口
        ckdall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                if(ckdall.isChecked()){
                    price = 0;
                    for (int i = 0; i < list.size(); i++) {
                        MyAdapter.getIsSelected().put(i, true);
                    }
                    // 数量设为list的长度
                    checkNum = list.size();
                    // 刷新listview和TextView的显示
                    dataChanged();
                }else{
                    // 遍历list的长度，将已选的按钮设为未选
                    for (int i = 0; i < list.size(); i++) {
                        if (MyAdapter.getIsSelected().get(i)) {
                            MyAdapter.getIsSelected().put(i, false);
                            checkNum--;// 数量减1
                        }
                    }
                    // 刷新listview和TextView的显示
                    dataChanged();
                }
                sumprice.setText("数量: "+checkNum);
            }
        });


        // 绑定listView的监听器
        PendingpaymentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                MyAdapter.ViewHolder holder = (MyAdapter.ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来
                MyAdapter.getIsSelected().put(arg2, holder.cb.isChecked());
                int UnitPrice =  Integer.parseInt(holder.thisprice.getText().toString());
                int num = Integer.parseInt(holder.buynum.getText().toString());
                // 调整选定条目
                if (holder.cb.isChecked() == true) {
                    checkNum++;
                    price+=(num*UnitPrice);
                } else {
                    checkNum--;
                    price-=(num*UnitPrice);
                }
                // 用TextView显示
                sumprice.setText("金额: "+price);
            }
        });
    }


    private void initViews() {
        PendingpaymentList = findViewById(R.id.PendingpaymentList);
        imageView = findViewById(R.id.myshoplistimg);
        jiesuan= findViewById(R.id.Settlement);
        ckdall = findViewById(R.id.ckdall);
        sumprice = findViewById(R.id.sumprice);
    }

    private void initSqlite(){
        helper = DBManger.getMmysqliteHelper(this);
        db = helper.getWritableDatabase();
        //Toast.makeText(this,"创建或打开成功",Toast.LENGTH_SHORT).show();
    }

    private List<shopinfos> selectShop(){
        db = helper.getWritableDatabase();
        Cursor query = null;
        query = db.query(Constant.TABLE_NAME, null, Constant.ISPAYMENT + "=?",
                new String[]{"2"}, null, null, Constant.SHOPBUYNUM + " desc");
        list = DBManger.CursorTolsit(query);
        Toast.makeText(this,list.size()+"",Toast.LENGTH_SHORT).show();
        Showtext(list);
        db.close();
        return list;
    }

    public String Showtext(List<shopinfos> lists){
        List<shopinfos> list = lists;
        String show="";
        for (shopinfos p:list){
            //show+="\n"+p.toString();
            Log.e("shopinfos",p.getName()+"--"+p.getShopbynum()+"--"+p.getIspayment());
        }
        return show;
    }


    private int updates(String shopid){
        /**
         * String table, ContentValues values, String whereClause, String[] whereArgs
         * 表名    表示建为string类型的hashmap  表示为当前修改的条件  表示修改条件的占位符
         */
        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constant.ISPAYMENT,3);//1 为修改的字段 2为修改后的字段值
        int update = db.update(Constant.TABLE_NAME,cv,
                "id=?",new String[]{shopid});
        cv.put(Constant.DELIVERYSTATUS,3);//1 为修改的字段 2为修改后的字段值
        int update2 = db.update(Constant.TABLE_NAME,cv,
                "id=?",new String[]{shopid});

        return update+update2;
    }

    private void setadapter(){
        // 实例化自定义的MyAdapter
        mAdapter = new MyAdapter(list, this);

        // 绑定Adapter
        PendingpaymentList.setAdapter(mAdapter);
    }

    private List<Map<String,Object>> addListDate(){
        for(int i=0;i<list.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            //只显示未付款未发货的商品
            if(list.get(i).getDeliverystatus()==1&&list.get(i).getIspayment()==1) {
                map.put("shopimg", list.get(i).getShopimg());
                map.put("name", list.get(i).getName());
                map.put("thisprice", list.get(i).getshopprice());
                map.put("buynum", list.get(i).getShopbynum());
                Log.e("交易状态", list.get(i).getIspayment()+"--"+list.get(i).getDeliverystatus());
                datalist.add(map);
            }
        }
        return datalist;
    }


    // 刷新listview和TextView的显示
    private void dataChanged() {
        // 通知listView刷新
        mAdapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        //tv_show.setText("已选中" + checkNum + "项");
    };

}
