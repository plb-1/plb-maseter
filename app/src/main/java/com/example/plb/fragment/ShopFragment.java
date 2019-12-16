package com.example.plb.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;


import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.plb.R;
import com.example.plb.activity.DetailsActivity;
import com.example.plb.adapter.MyAdapter;
import com.example.plb.tdy.custom.ViewPageLinearLayout;
import com.example.plb.tdy.shop.rank1;
import com.example.plb.tdy.shop.rank2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhc on 2018/12/27.
 * 店铺
 */
public class ShopFragment extends Fragment{
    private ViewPager mviewPager;
    private ViewPageLinearLayout mIndicator;

    private List<String> qlist = Arrays.asList("1","2");

    private List<Fragment> mContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter madapter;

    private List<String> mlist = new ArrayList<>();
    private View view;
    private Button button;
    private ListView listView;
    //private ArrayAdapter<String> mAdapter;

    private List<String> datas= Arrays.asList("1号门店","2号门店","3号门店","4号门店","5号门店","6号门店","7号门店",
            "8号门店","9号门店","10号门店","11号门店");

    public static int mPosition;
    //gridview
    private GridView gridView,gridView1,gridView2;
    private List<Map<String,Object>> dataList,dataLists,getDataList;
    private SimpleAdapter simpleAdapter,simpleAdapter1,simpleAdapter2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop,null);
        //跳转到详情页

        listView=view.findViewById(R.id.list_view);
//        mAdapter = new ArrayAdapter(getContext(),R.layout.shopitem, datas);

        final ShapAdaper adapter =new ShapAdaper();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = datas.get(position);
                Toast.makeText(getContext(),data, Toast.LENGTH_LONG).show();
                adapter.setCurrentItem(position);
                adapter.setClick(true);
                adapter.notifyDataSetChanged();

                if(position%2==0){
                    Log.e("偶数","t");
//                    init_c();
//                    simpleAdapter.notifyDataSetChanged();
                    gridView.setAdapter(simpleAdapter2);
//                    inits_c();
//                    simpleAdapter1.notifyDataSetChanged();
                    gridView1.setAdapter(simpleAdapter);
//                    init3_c();
//                    simpleAdapter2.notifyDataSetChanged();
                    gridView2.setAdapter(simpleAdapter1);
                }else{
                    Log.e("奇数","t");
                    gridView.setAdapter(simpleAdapter);
//                    inits_c();
//                    simpleAdapter1.notifyDataSetChanged();
                    gridView1.setAdapter(simpleAdapter1);
//                    init3_c();
//                    simpleAdapter2.notifyDataSetChanged();
                    gridView2.setAdapter(simpleAdapter2);
                }
            }
        });

        init();
        String[] from={"img","text"};
        int[] to={R.id.img,R.id.texts};
        simpleAdapter=new SimpleAdapter(getContext(),dataList,R.layout.gridview_item,from,to);
        gridView=view.findViewById(R.id.gridView);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent ( getActivity (),DetailsActivity.class );
                startActivity (intent);
            }
        });

        inits();
        simpleAdapter1=new SimpleAdapter(getContext(),dataLists,R.layout.gridview_item,from,to);
        gridView1=view.findViewById(R.id.gridView1);
        gridView1.setAdapter(simpleAdapter1);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent ( getActivity (),DetailsActivity.class );
                startActivity (intent);
            }
        });

        init3();
        simpleAdapter2=new SimpleAdapter(getContext(),getDataList,R.layout.gridview_item,from,to);
        gridView2=view.findViewById(R.id.gridView2);
        gridView2.setAdapter(simpleAdapter2);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent ( getActivity (),DetailsActivity.class );
                startActivity (intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public void init(){
        int icno[]={R.mipmap.adg,R.mipmap.bawanniujin,R.mipmap.fangbianmian,R.mipmap.huotui,R.mipmap.aerbeisi,R.mipmap.qiaokeli};
        String name[]={"AD钙","霸王牛筋","方便面","火腿肠","阿尔卑斯","巧克力"};
        dataList=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
    }
    public void init_c(){
        int icno[]={R.mipmap.renshen,R.mipmap.luro,R.mipmap.yanwo,R.mipmap.yiner,R.mipmap.hongzao,R.mipmap.baoyu};
        String name[]={"人参","鹿茸","燕窝","银耳","红枣","鲍鱼"};
        dataList=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
    }

    public void inits(){
        int icno[]={R.mipmap.hetao,R.mipmap.putaogan,R.mipmap.sonzi,R.mipmap.xiaweiyi,R.mipmap.xingen,R.mipmap.kaixinguo};
        String name[]={"核桃","葡萄干","松子","夏威夷果","杏仁","开心果"};
        dataLists=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            dataLists.add(map);
        }
    }
    public void inits_c(){
        int icno[]={R.mipmap.adg,R.mipmap.bawanniujin,R.mipmap.fangbianmian,R.mipmap.huotui,R.mipmap.aerbeisi,R.mipmap.qiaokeli};
        String name[]={"1","2","3","4","6","7"};
        dataLists=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            dataLists.add(map);
        }
        Log.e("inits_c","inits_c");
    }

    public void init3(){
        int icno[]={R.mipmap.renshen,R.mipmap.luro,R.mipmap.yanwo,R.mipmap.yiner,R.mipmap.hongzao,R.mipmap.baoyu};
        String name[]={"人参","鹿茸","燕窝","银耳","红枣","鲍鱼"};
        getDataList=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            getDataList.add(map);
        }
    }
    public void init3_c(){
        int icno[]={R.mipmap.hetao,R.mipmap.putaogan,R.mipmap.sonzi,R.mipmap.xiaweiyi,R.mipmap.xingen,R.mipmap.kaixinguo};
        String name[]={"核桃","葡萄干","松子","夏威夷果","杏仁","开心果"};
        getDataList=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            getDataList.add(map);
        }
    }


    class ShapAdaper extends BaseAdapter{
        private int mCurrentItem=0;
        private boolean isClick=false;

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(getActivity()).inflate(R.layout.shopitem,null);
            }

            TextView textView= (TextView) view.findViewById(R.id.text);
            //LinearLayout parent= (LinearLayout) view.findViewById(R.id.ll_parent);

            textView.setText(datas.get(i));

            if (mCurrentItem==i&&isClick){
                //parent.setBackgroundColor(Color.parseColor("#3F51B5"))
                // 点击改变的颜色;
                textView.setTextColor(Color.parseColor("#FF4081"));
            }else{
                //parent.setBackgroundColor(Color.parseColor("#ffffff"));
                textView.setTextColor(Color.parseColor("#000000"));
            }

            return view;
        }
        public void setCurrentItem(int currentItem){
            this.mCurrentItem=currentItem;
        }

        public void setClick(boolean click){
            this.isClick=click;
        }
    }
}
