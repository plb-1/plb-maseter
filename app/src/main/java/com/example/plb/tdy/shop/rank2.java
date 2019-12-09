package com.example.plb.tdy.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.plb.R;
import com.example.plb.activity.DetailsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/2.
 */

public class rank2 extends Fragment{
    private View view;
    private Button button;
    private ListView listView;
    private ArrayAdapter<String> mAdapter;
    private List<String> datas= Arrays.asList("1号门店","2号门店","3号门店","4号门店","5号门店","6号门店","7号门店",
            "8号门店","9号门店","10号门店","11号门店");

    public static int mPosition;
    //gridview
    private GridView gridView,gridView1,gridView2;
    private List<Map<String,Object>> dataList,dataLists,getDataList;
    private SimpleAdapter simpleAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rank2,null);
        //跳转到详情页
        button = view.findViewById ( R.id.btntest );
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity (),DetailsActivity.class );
                startActivity ( intent );
            }
        } );


        init();
        String[] from={"img","text"};
        int[] to={R.id.img,R.id.texts};
        simpleAdapter=new SimpleAdapter(getContext(),dataList,R.layout.gridview_item,from,to);
        gridView=view.findViewById(R.id.gridView);
        gridView.setAdapter(simpleAdapter);
        inits();
        simpleAdapter=new SimpleAdapter(getContext(),dataLists,R.layout.gridview_item,from,to);
        gridView1=view.findViewById(R.id.gridView1);
        gridView1.setAdapter(simpleAdapter);

        init3();
        simpleAdapter=new SimpleAdapter(getContext(),getDataList,R.layout.gridview_item,from,to);
        gridView2=view.findViewById(R.id.gridView2);
        gridView2.setAdapter(simpleAdapter);

        return view;
    }

    public void init(){
        int icno[]={R.mipmap.ym,R.mipmap.ym,R.mipmap.ym,R.mipmap.ym,R.mipmap.ym,R.mipmap.ym};
        String name[]={"AD钙","霸王牛筋","方便面","火腿肠","阿尔卑斯","巧克力"};
        dataList=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
    }

    public void inits(){
        int icno[]={R.mipmap.yd,R.mipmap.yd,R.mipmap.yd,R.mipmap.yd,R.mipmap.yd,R.mipmap.yd};
        String name[]={"核桃","葡萄干","松子","夏威夷果","杏仁","开心果"};
        dataLists=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            dataLists.add(map);
        }
    }

    public void init3(){
        int icno[]={R.mipmap.bg,R.mipmap.bg,R.mipmap.bg,R.mipmap.bg,R.mipmap.bg,R.mipmap.bg};
        String name[]={"人参","鹿茸","燕窝","银耳","红枣","鲍鱼"};
        getDataList=new ArrayList<Map<String, Object>>();
        for (int i=0;i<icno.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("img",icno[i]);
            map.put("text",name[i]);
            getDataList.add(map);
        }
    }
}
