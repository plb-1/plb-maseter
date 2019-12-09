package com.example.plb.tdy;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.plb.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class message_Fragment1 extends Fragment {

    private ListView message1list;
    SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
    private int listicon[] = {R.mipmap.messageicon1};
    private String listtxt1[] = {"送你一张10元美食红包券","送你一张11元美食红包券","送你一张12元美食红包券","送你一张13元美食红包券"};
    private String listtxt2[] = {"听说红包与低价美食更配哦","听说红包与低价美食更配哦","听说红包与低价美食更配哦","听说红包与低价美食更配哦"};
    View view;
    Context context;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_messages_fragment1, null);
        context = view.getContext();
        initView();
        setSimpleAdapter();
        return view;
    }

    private void initView(){
        message1list = view.findViewById(R.id.message1list);
    }

    private void setSimpleAdapter(){
        simpleAdapter = new SimpleAdapter(context,getDatalist(),
                R.layout.message_fragment_item,
                new String[]{"img1","txtv1","txtv2"},
                new int[]{R.id.messageicon,R.id.text1,R.id.text2});
        message1list.setAdapter(simpleAdapter);
    }

    private List<Map<String,Object>> getDatalist(){
        for(int i = 0;i<listtxt1.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img1",listicon[0]);
            map.put("txtv1",listtxt1[i]);
            map.put("txtv2",listtxt2[i]);
            datalist.add(map);
        }
        return datalist;
    }

}
