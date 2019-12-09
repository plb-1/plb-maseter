package com.example.plb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.plb.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 陈 on 2019/1/2.
 */

public class DetailsDetailsFragment extends Fragment {
    private View view;
    private TextView details_shopname;
    private ImageView shop_details_iv;
    private OkHttpClient client = new OkHttpClient();
    public JSONObject object;
    String date;
    int num=1;

    private ListView detailslist;
    SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();
    private String listtxt1[] = {
            "生产日期",
            "许可证编号",
            "产品标准号",
            "厂名",
            "厂址",
            "联系方式",
            "配料表",
            "储藏方法",
            "保质期"
    };
    private String listtxt2[] = {
            "2019年03月01日 至 2019年05月12日",
            "SC12444020500182",
            "GB",
            "广东隆盛食品有限公司",
            "韶关市曲江区白土经济开发区B3区地块",
            "00800-021216",
            "无",
            "常温，天热冷藏",
            "180天"
    };

    DetailsShopFragment ds = new DetailsShopFragment (  );
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate ( R.layout.fragment_details_details,null );
        initView();
        initDate ();
        setSimpleAdapter();
        okhttpShopDate();
        return view;
    }

    private void okhttpShopDate() {
        new Thread (){
            @Override
            public void run() {
                super.run ();
                Request request = new Request.Builder ().url ( "http://39.98.68.40:8080/RetailManager/getCommodityInfo?commodityId="+num ).build ();
                try {
                    Response response = client.newCall ( request ).execute ();
                    String date = response.body ().string();
                    jsonJX(date);
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }.start ();
    }


    private void jsonJX(String date) {
        if (date != null){
            try {
                JSONObject jsonObject  = new JSONObject ( date );
                JSONArray jsonArray = jsonObject.getJSONArray ( "CommodityInfo" );
                for(int i=0;i<jsonArray.length();i++){
                    object=jsonArray.getJSONObject(i);
                    try {
                        //获取到json数据中的activity数组里的内容imported
                        Log.e("okhttpShopDate", "jsonJX: "+object.getString("info"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace ();
            }
        }
    }


    private void initView() {
        details_shopname = view.findViewById ( R.id.details_shopname );
        shop_details_iv = view.findViewById ( R.id.shop_details_iv );
        detailslist = view.findViewById(R.id.detailslist);
    }

    public void initDate() {
       details_shopname.setText ( ""+ds.info );
       Glide.with ( view.getContext () ).load ( ds.detailedurl ).into ( shop_details_iv );
    }

    private void setSimpleAdapter(){
        simpleAdapter = new SimpleAdapter(view.getContext(),getDatalist(),
                R.layout.tdy_detailslist_item,
                new String[]{"text1","text2"},
                new int[]{R.id.text1,R.id.text2});
        detailslist.setAdapter(simpleAdapter);
    }

    private List<Map<String,Object>> getDatalist(){
        for(int i = 0;i<listtxt1.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("text1",listtxt1[i]);
            map.put("text2",listtxt2[i]);
            datalist.add(map);
        }
        return datalist;
    }
}
