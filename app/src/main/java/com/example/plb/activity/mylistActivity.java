package com.example.plb.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.plb.R;
import com.example.plb.tdy.Detailedlist.MyAdapter3;
import com.example.plb.tdy.Sqlite.Constant;
import com.example.plb.tdy.Sqlite.MysqliteHelper;
import com.example.plb.tdy.Sqlite.bean.shopinfos;
import com.example.plb.tdy.Sqlite.util.DBManger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mylistActivity extends AppCompatActivity {

    private ListView mylist_listview;
    SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();

    private MysqliteHelper helper;
    private SQLiteDatabase db = null;

    List<shopinfos> list = new ArrayList<>();

    private String listtxt1[] = {"送你一张10元美食红包券",
            "送你一张11元美食红包券",
            "送你一张12元美食红包券",
            "送你一张13元美食红包券"};
    private String listtxt2[] = {"听说红包与低价美食更配哦",
            "听说红包与低价美食更配哦",
            "听说红包与低价美食更配哦",
            "听说红包与低价美食更配哦"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);
        mylist_listview = findViewById(R.id.mylist_listview);
        initSqlite();
        selectShop();
        setSimpleAdapter();
    }

    private void setSimpleAdapter(){
        simpleAdapter = new SimpleAdapter(this,getDatalist(),
                R.layout.tdy_mylistactivity_item,
                new String[]{"shopnum","shopprice"},
                new int[]{R.id.shopnum,R.id.shopprice});
        mylist_listview.setAdapter(simpleAdapter);
    }

    private List<Map<String,Object>> getDatalist(){
        for(int i = 0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("shopnum","共"+list.get(i).getShopbynum()+"件商品");
            map.put("shopprice","实付金额"+(list.get(i).getshopprice()*list.get(i).getShopbynum())+"元");
            datalist.add(map);
        }
        return datalist;
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
                new String[]{"3"}, null, null, Constant.SHOPBUYNUM + " desc");
        list = DBManger.CursorTolsit(query);
        Toast.makeText(this,list.size()+"",Toast.LENGTH_SHORT).show();
        db.close();
        return list;
    }
}
