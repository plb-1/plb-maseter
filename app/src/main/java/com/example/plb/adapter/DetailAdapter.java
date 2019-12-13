package com.example.plb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.plb.R;
import com.example.plb.activity.AddShopPopupwindow;
import com.example.plb.bean.ShopBean;

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
 * Created by cfw on 2019/12/12.
 */

public class DetailAdapter extends RecyclerView.Adapter{
    public static final int DetailsFragment = 1;
    public static final int ShopFragment = 0;

    private final Context mContext;
    //初始化布局
    private LayoutInflater inflater;

    public DetailAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType == ShopFragment){
            return new DetailsShopFragments(mContext,inflater.inflate(R.layout.fragment_details_shop,viewGroup,false));
        }else if(viewType==DetailsFragment){
            return new DetailsDetailsFragments(mContext,inflater.inflate(R.layout.fragment_details_details,viewGroup,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position) == ShopFragment){
            DetailsShopFragments detailsShopFragments = (DetailsShopFragments)viewHolder;
            detailsShopFragments.setData();
        }else if(getItemViewType(position) == DetailsFragment){
            DetailsDetailsFragments detailsDetailsFragments = (DetailsDetailsFragments)viewHolder;
            detailsDetailsFragments.setData();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position < 2 ? position : 2;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class DetailsShopFragments extends RecyclerView.ViewHolder {
        private final Context mContext;

        private AddShopPopupwindow mPopwindow;
        public JSONObject object;
        private OkHttpClient client = new OkHttpClient();
        private ImageView imageView;//商品图片
        int num=1;
        public static boolean checkd;
        public static String info,unit,image,detailedurl;
        public static int minNum,stocks;
        public static double wholesalePrice;
        private TextView details_shop_name,tv_shop_money,shop_unit,shop_minNum,shop_retailPrice;//商品名称 商品价格 袋/件参数 起订数
        private TextView shop_stocks,imported,shelfLife;//可售量 是否进口 保质期
        ShopBean shopBean = new ShopBean ();

        public DetailsShopFragments(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            initview();
        }

        private void initview() {
            details_shop_name =itemView.findViewById ( R.id.details_shop_name );
            tv_shop_money =itemView.findViewById ( R.id.tv_shop_money );
            shop_unit =itemView.findViewById ( R.id.shop_unit );
            shop_minNum =itemView.findViewById ( R.id.shop_minNum );
            shop_stocks =itemView.findViewById ( R.id.shop_stocks );
            imported =itemView.findViewById ( R.id.imported );
            shelfLife =itemView.findViewById ( R.id.ShelfLife );
            imageView =itemView.findViewById ( R.id.details_shop_iv );
            shop_retailPrice =itemView.findViewById ( R.id.retailPrice );
        }

        public void setData() {
            new Thread (){
                @Override
                public void run() {
                    super.run ();
                    Request request = new Request.Builder ().url ( "http://39.98.68.40:8080/RetailManager/getCommodityInfo?commodityId="+num ).build ();
                    try {
                        Response response = client.newCall ( request ).execute ();
                        String date = response.body ().string ();
                        jsonJX(date);
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }
                }
            }.start ();
        }

        private void jsonJX(String date) {
            if (date != null) {
                try {
                    JSONObject jsonObject = new JSONObject(date);
                    JSONArray jsonArray = jsonObject.getJSONArray("CommodityInfo");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        object = jsonArray.getJSONObject(i);
                        try {
                            //获取到json数据中的activity数组里的内容imported
                            shopBean.setInfo(object.getString("info"));
                            info = object.getString("info");
                            shopBean.setWholesalePrice(object.getDouble("wholesalePrice"));
                            wholesalePrice = object.getDouble("wholesalePrice");
                            shopBean.setUnit(object.getString("unit"));
                            unit = object.getString("unit");
                            shopBean.setMinNum(object.getInt("minNum"));
                            minNum = object.getInt("minNum");
                            shopBean.setStocks(object.getInt("stocks"));
                            stocks = object.getInt("stocks");
                            shopBean.setImported(object.getInt("imported"));
                            shopBean.setShelfLife(object.getString("ShelfLife"));
                            shopBean.setImage(object.getString("Image"));
                            image = object.getString("Image");
                            detailedurl = object.getString("Detailedurl");
                            shopBean.setRetailPrice(object.getDouble("retailPrice"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    handler.sendEmptyMessage(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @SuppressLint("HandlerLeak")
        public Handler handler = new Handler (  ){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        shopBean.setInfo ( info );
                        details_shop_name.setText ( ""+shopBean.getInfo ());
                        tv_shop_money.setText ( ""+shopBean.getWholesalePrice () );
                        shop_unit.setText ( "/"+shopBean.getUnit ());
                        shop_minNum.setText ( ""+shopBean.getMinNum ()+""+shopBean.getUnit () );
                        shop_stocks.setText (shopBean.getStocks ()+""+shopBean.getUnit ());
                        if (shopBean.getImported () == 0){
                            imported.setText ( "非进口" );
                        }else if (shopBean.getImported () == 1){
                            imported.setText ( "进口" );
                        }
                        shelfLife.setText ( ""+shopBean.getShelfLife () );
                        shop_retailPrice.setText ( "￥"+shopBean.getRetailPrice ()+"/"+ shopBean.getUnit ());
                        Glide.with (itemView.getContext ()).load ( shopBean.getImage () ).into ( imageView );
                        break;
                }
            }
        };
    }

    private class DetailsDetailsFragments extends RecyclerView.ViewHolder {
        private final Context mContext;
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

        public DetailsDetailsFragments(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            initView();
            setSimpleAdapter();
            okhttpShopDate();
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
            details_shopname = itemView.findViewById ( R.id.details_shopname );
            shop_details_iv = itemView.findViewById ( R.id.shop_details_iv );
            detailslist = itemView.findViewById(R.id.detailslist);
        }

        private void setSimpleAdapter(){
            simpleAdapter = new SimpleAdapter(itemView.getContext(),getDatalist(),
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

        public void setData() {
            details_shopname.setText ( ""+DetailAdapter.DetailsShopFragments.info );
            Glide.with ( itemView.getContext () ).load ( DetailAdapter.DetailsShopFragments.detailedurl ).into ( shop_details_iv );
        }
    }
}
