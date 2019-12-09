package com.example.plb.tdy.Detailedlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
import com.example.plb.tdy.Sqlite.Constant;
import com.example.plb.tdy.Sqlite.MysqliteHelper;
import com.example.plb.tdy.Sqlite.bean.GoodsBean;
import com.example.plb.tdy.Sqlite.bean.StoreBean;
import com.example.plb.tdy.Sqlite.bean.shopinfos;
import com.example.plb.tdy.Sqlite.util.DBManger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhc on 2018/12/27.
 * 购物车
 */
public class ShopCart extends Fragment implements View.OnClickListener {


    //定义父列表项List数据集合
    List<Map<String, Object>> parentMapList = new ArrayList<Map<String, Object>>();
    //定义子列表项List数据集合
    List<List<Map<String, Object>>> childMapList_list = new ArrayList<List<Map<String, Object>>>();

    MyBaseExpandableListAdapter myBaseExpandableListAdapter;
    CheckBox id_cb_select_all;
    LinearLayout id_ll_normal_all_state;
    LinearLayout id_ll_editing_all_state;
    ExpandableListView expandableListView;
    RelativeLayout id_rl_cart_is_empty;
    RelativeLayout id_rl_foot;

    TextView id_tv_edit_all;
    View view;
    Context context;
    boolean isjisuan = true,isdelete = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tdy_shopcart, null);
        context = getContext();

        initCartData();

        expandableListView = (ExpandableListView) view.findViewById(R.id.id_elv_listview);

        myBaseExpandableListAdapter = new MyBaseExpandableListAdapter(context, parentMapList, childMapList_list);
        expandableListView.setAdapter(myBaseExpandableListAdapter);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(context, "click：" + position, Toast.LENGTH_SHORT).show();
            }
        });


        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }


        ImageView id_iv_back = (ImageView) view.findViewById(R.id.id_iv_back);
        id_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "click :back", Toast.LENGTH_SHORT).show();
            }
        });

        List Shopname = new ArrayList();
        List Shopbynum = new ArrayList();
        List Shopid = new ArrayList();

        id_ll_normal_all_state = (LinearLayout) view.findViewById(R.id.id_ll_normal_all_state);
        id_ll_editing_all_state = (LinearLayout) view.findViewById(R.id.id_ll_editing_all_state);
        id_rl_cart_is_empty = (RelativeLayout) view.findViewById(R.id.id_rl_cart_is_empty);
        TextView id_tv_save_star_all = (TextView) view.findViewById(R.id.id_tv_save_star_all);
        id_tv_save_star_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "收藏多选商品", Toast.LENGTH_SHORT).show();
            }
        });
        TextView id_tv_delete_all = (TextView) view.findViewById(R.id.id_tv_delete_all);
        id_tv_delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<Shopid.size();i++){
                    if(!deletes(Shopid.get(i).toString())){
                        isdelete = false;
                    }
                }
                if(isdelete){
                    myBaseExpandableListAdapter.removeGoods();
                    Toast.makeText(context, "删除", Toast.LENGTH_SHORT).show();
                }

                // Toast.makeText(MainActivity.this, "删除多选商品", Toast.LENGTH_SHORT).show();
            }
        });

        id_tv_edit_all = (TextView) view.findViewById(R.id.id_tv_edit_all);

        id_tv_edit_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof TextView) {
                    TextView tv = (TextView) v;
                    if (MyBaseExpandableListAdapter.EDITING.equals(tv.getText())) {
                        myBaseExpandableListAdapter.setupEditingAll(true);
                        tv.setText(MyBaseExpandableListAdapter.FINISH_EDITING);
                        changeFootShowDeleteView(true);//这边类似的功能 后期待使用观察者模式
                    } else {
                        myBaseExpandableListAdapter.setupEditingAll(false);
                        tv.setText(MyBaseExpandableListAdapter.EDITING);
                        changeFootShowDeleteView(false);//这边类似的功能 后期待使用观察者模式
                    }

                }
            }
        });

        id_cb_select_all = (CheckBox)view. findViewById(R.id.id_cb_select_all);
        id_cb_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) v;
                    myBaseExpandableListAdapter.setupAllChecked(checkBox.isChecked());
                }
            }
        });


        /*---------结算---------*/
        final TextView id_tv_totalPrice = (TextView) view.findViewById(R.id.id_tv_totalPrice);

        final TextView id_tv_totalCount_jiesuan = (TextView) view.findViewById(R.id.id_tv_totalCount_jiesuan);
        id_tv_totalCount_jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<Shopid.size();i++){
                    if(updates(Shopid.get(i).toString())<2){
                        isjisuan = false;
                    }
                }
                if(isjisuan)
                Toast.makeText(context,"结算成功",Toast.LENGTH_SHORT).show();
                myBaseExpandableListAdapter.removeGoods();
                myBaseExpandableListAdapter.notifyDataSetChanged();
                //Toast.makeText(context,Shopname.size()+"--"+Shopbynum.size(),Toast.LENGTH_SHORT).show();
            }
        });



        //---------------选中商品接口回调---------------
        myBaseExpandableListAdapter.setOnGoodsCheckedChangeListener(new MyBaseExpandableListAdapter.OnGoodsCheckedChangeListener() {
            @Override
            public void onGoodsCheckedChange(int totalCount, double totalPrice, List shopname, List shopbynum,List shopid) {
                id_tv_totalPrice.setText("合计"+totalPrice);
                Shopbynum.clear();

                Shopname.clear();

                Shopid.clear();

                Shopbynum.addAll(shopbynum);

                Shopname.addAll(shopname);

                Shopid.addAll(shopid);

                id_tv_totalCount_jiesuan.setText(String.format(getString(R.string.jiesuan), totalCount+""));
                //Toast.makeText(context,shopname.size()+"--"+shopbynum.size(),Toast.LENGTH_SHORT).show();
            }
        });




        myBaseExpandableListAdapter.setOnAllCheckedBoxNeedChangeListener(new MyBaseExpandableListAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
                id_cb_select_all.setChecked(allParentIsChecked);
            }
        });



        myBaseExpandableListAdapter.setOnEditingTvChangeListener(new MyBaseExpandableListAdapter.OnEditingTvChangeListener() {
            @Override
            public void onEditingTvChange(boolean allIsEditing) {

                changeFootShowDeleteView(allIsEditing);//这边类似的功能 后期待使用观察者模式

            }
        });

        myBaseExpandableListAdapter.setOnCheckHasGoodsListener(new MyBaseExpandableListAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                setupViewsShow(isHasGoods);
            }
        });


        /**====include进来方式可能会导致view覆盖listview的最后一个item 解决*/
        //在onCreate方法中一般没办法直接调用view.getHeight方法来获取到控件的高度
        id_rl_foot = (RelativeLayout)view.findViewById(R.id.id_rl_foot);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        id_rl_foot.measure(w, h);
        int r_width = id_rl_foot.getMeasuredWidth();
        int r_height = id_rl_foot.getMeasuredHeight();
        Log.i("MeasureSpec", "MeasureSpec r_width = " + r_width);
        Log.i("MeasureSpec", "MeasureSpec r_height = " + r_height);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int top = dp2px(view.getContext(), 48);
        lp.setMargins(0, top, 0, r_height);//48

        expandableListView.setLayoutParams(lp);
        /**==========*/


        if (parentMapList != null && parentMapList.size() > 0) {
            setupViewsShow(true);
        } else {
            setupViewsShow(false);
        }
        return view;
    }


    private void setupViewsShow(boolean isHasGoods) {
        if (isHasGoods) {
            expandableListView.setVisibility(View.VISIBLE);
            id_rl_cart_is_empty.setVisibility(View.GONE);
            id_rl_foot.setVisibility(View.VISIBLE);
            id_tv_edit_all.setVisibility(View.VISIBLE);
        } else {
            expandableListView.setVisibility(View.GONE);
            id_rl_cart_is_empty.setVisibility(View.VISIBLE);
            id_rl_foot.setVisibility(View.GONE);
            id_tv_edit_all.setVisibility(View.GONE);
        }
    }

    public Boolean deletes(String ID){
        db = helper.getWritableDatabase();
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

    public void changeFootShowDeleteView(boolean showDeleteView) {

        if (showDeleteView) {
            id_tv_edit_all.setText(MyBaseExpandableListAdapter.FINISH_EDITING);

            id_ll_normal_all_state.setVisibility(View.INVISIBLE);
            id_ll_editing_all_state.setVisibility(View.VISIBLE);
        } else {
            id_tv_edit_all.setText(MyBaseExpandableListAdapter.EDITING);

            id_ll_normal_all_state.setVisibility(View.VISIBLE);
            id_ll_editing_all_state.setVisibility(View.INVISIBLE);
        }
    }

    public int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private MysqliteHelper helper;
    private SQLiteDatabase db = null;
    List<shopinfos> list;
    private void initCartData() {
        initSqlite();
        selectShop();
        int size = list.size();
        for (int i = 0; i < 1; i++) {
            String store="五二市场";
            if(i%2==0){
                store="五一市场";
            }
            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();

            parentMap.put("parentName", new StoreBean("" + i, store, false, false));
            if(parentMapList.size()<=0&&list.size()>0)
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            for (int j = 0; j < size; j++) {
                Map<String, Object> childMap = new HashMap<String, Object>();
                    GoodsBean goodsBean = new GoodsBean(list.get(j).getShopid(), list.get(j).getName(), R.mipmap.example_1, "品牌 食品", list.get(j).getshopprice(),  list.get(j).getshopprice(),list.get(j).getShopbynum(), GoodsBean.STATUS_VALID, false, false);
                    childMap.put("childName", goodsBean);
                    childMapList.add(childMap);
                   // Toast.makeText(context,"f0",Toast.LENGTH_SHORT).show();
            }
            childMapList_list.add(childMapList);
        }
    }



    private void initSqlite(){
        helper = DBManger.getMmysqliteHelper(context);
        db = helper.getWritableDatabase();
        //Toast.makeText(this,"创建或打开成功",Toast.LENGTH_SHORT).show();
    }




    private List<shopinfos> selectShop(){
        db = helper.getWritableDatabase();
        Cursor query = null;
        query = db.query(Constant.TABLE_NAME, null, Constant.ISPAYMENT + "=?",
                new String[]{"1"}, null, null, Constant.SHOPBUYNUM + " desc");
        list = DBManger.CursorTolsit(query);
        db.close();
        return list;
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


    @Override
    public void onClick(View v) {

    }


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
        finsh();
    }
    public void onPause(){
        super.onPause();
        finsh();
    }
    public void finsh(){
        parentMapList.clear();
        childMapList_list.clear();
        initCartData();
        myBaseExpandableListAdapter.notifyDataSetChanged();
        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }
    }
}
