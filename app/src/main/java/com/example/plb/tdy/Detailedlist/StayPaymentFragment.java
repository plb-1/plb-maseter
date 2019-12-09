package com.example.plb.tdy.Detailedlist;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
import com.example.plb.adapter.PaymentAdapter;
import com.example.plb.bean.PaymentInformation;
import com.example.plb.tdy.Detailedlist.MyAdapter;
import com.example.plb.tdy.Sqlite.Constant;
import com.example.plb.tdy.Sqlite.MysqliteHelper;
import com.example.plb.tdy.Sqlite.bean.shopinfos;
import com.example.plb.tdy.Sqlite.util.DBManger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待付款
 * A simple {@link Fragment} subclass.
 */
public class StayPaymentFragment extends Fragment {

    Context mContext;

    private ListView PendingpaymentList;
    private ImageView imageView;
    private Button jiesuan,delete;
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


    View view;
    boolean isjiesuan = true;
    boolean isdelete = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tdy_daifukuan, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = view.getContext();
        initViews();
        initSqlite();
        if(selectShop().size()>0){
            imageView.setVisibility(View.GONE);
            setadapter();
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<MyAdapter.getIsSelected().size();i++){
                        if(MyAdapter.getIsSelected().get(i)){
                            String id = mAdapter.shopid.get(i).toString();
                            if( !deletes(id)){
                                isdelete = false;
                            };
                        }
                    }
                    if(isdelete){
                        Toast.makeText(mContext,"删除成功"+selectShop().size(),Toast.LENGTH_SHORT).show();
                        mAdapter.setList(selectShop());
                        mAdapter.myinitDate();
                        mAdapter.notifyDataSetChanged();
                    };
                }
            });
            jiesuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringBuilder sb = new StringBuilder();

                    for(int i=0;i<MyAdapter.getIsSelected().size();i++){
                        if(MyAdapter.getIsSelected().get(i)){
                            sb.append("ItemID="+mAdapter.shopname.get(i)+" . ");

                            int bynums = mAdapter.shopbynum.get(i);
                            String names = mAdapter.shopname.get(i);
                            String byn = String.valueOf(bynums);
                            String id = mAdapter.shopid.get(i).toString();
                            if( updates(id)<2){
                                isjiesuan = false;
                            };
                        }
                    }
                    if(isjiesuan){
                        Toast.makeText(mContext,"结算成功"+selectShop().size(),Toast.LENGTH_SHORT).show();
                        mAdapter = null;
                        mAdapter = new MyAdapter(selectShop(),mContext);
                        PendingpaymentList.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    };
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
                sumprice.setText("数量:"+checkNum);
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
                } else {
                    checkNum--;
                }
                // 用TextView显示
                sumprice.setText("数量:"+checkNum);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initViews() {
        PendingpaymentList = view.findViewById(R.id.PendingpaymentList);
        imageView = view.findViewById(R.id.myshoplistimg);
        jiesuan= view.findViewById(R.id.Settlement);
        delete = view.findViewById(R.id.delete);
        ckdall =view.findViewById(R.id.ckdall);
        sumprice = view.findViewById(R.id.sumprice);
    }

    private void initSqlite(){
        helper = DBManger.getMmysqliteHelper(view.getContext());
        db = helper.getWritableDatabase();
        //Toast.makeText(this,"创建或打开成功",Toast.LENGTH_SHORT).show();
    }

    private List<shopinfos> selectShop(){
        db = helper.getWritableDatabase();
        Cursor query = null;
        query = db.query(Constant.TABLE_NAME, null, Constant.ISPAYMENT + "=?",
                new String[]{"1"}, null, null, Constant.SHOPBUYNUM + " desc");
        list = DBManger.CursorTolsit(query);
        //Toast.makeText(view.getContext(),list.size()+"",Toast.LENGTH_SHORT).show();
        Showtext(list);
        db.close();
        return list;
    }

    public String Showtext(List<shopinfos> lists){
        List<shopinfos> list = lists;
        String show="";
        for (shopinfos p:list){
            //show+="\n"+p.toString();
           /* Log.e("筛选的商品信息",p.getName()+"--"+
                    p.getShopbynum()+"--"+p.getIspayment()+"--"+p.getDeliverystatus());*/
        }
        return show;
    }

    public Boolean deletes(String ID){
        db = helper.getWritableDatabase();
        /**
         * String table, String whereClause, String[] whereArgs
         * 表名           删除条件            删除的条件的占位符
         */
        int delete = db.delete(Constant.TABLE_NAME, "id=?", new String[]{ID});
        db.close();
        if(delete>0){
            return true;
        }
        else {
            Toast.makeText(view.getContext(),"删除失败",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private int updates(String shopid){
        /**
         * String table, ContentValues values, String whereClause, String[] whereArgs
         * 表名    表示建为string类型的hashmap  表示为当前修改的条件  表示修改条件的占位符
         */
        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constant.ISPAYMENT,2);//1 为修改的字段 2为修改后的字段值
        int update = db.update(Constant.TABLE_NAME,cv,
                "id=?",new String[]{shopid});
        cv.put(Constant.DELIVERYSTATUS,2);//1 为修改的字段 2为修改后的字段值
        int update2 = db.update(Constant.TABLE_NAME,cv,
                "id=?",new String[]{shopid});

        return update+update2;
    }

    private void setadapter(){
        // 实例化自定义的MyAdapter
        mAdapter = new MyAdapter(list,view.getContext() );

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
                //Log.e("交易状态", list.get(i).getIspayment()+"--"+list.get(i).getDeliverystatus());
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
            onResume();
        } else if (!isVisibleToUser) {
            onPause();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            if(db!=null){
                mAdapter = null;
                mAdapter = new MyAdapter(selectShop(),view.getContext());
                mAdapter.notifyDataSetChanged();
                PendingpaymentList.setAdapter(mAdapter);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(db!=null){
            mAdapter = null;
            mAdapter = new MyAdapter(selectShop(),view.getContext());
            mAdapter.notifyDataSetChanged();
            PendingpaymentList.setAdapter(mAdapter);
        }
    }
}
