package com.example.plb.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.plb.activity.AddShopPopupwindow;
import com.example.plb.R;
import com.example.plb.bean.ShopBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 陈 on 2019/1/2.
 */

public class DetailsShopFragment extends Fragment {
    private View view;
    private Button add_shop;//加入进货单
    private AddShopPopupwindow mPopwindow;
    public JSONObject object;
    private OkHttpClient client = new OkHttpClient();
    private ImageView imageView;//商品图片
    private CheckBox checkBox;//关注
    int num=1;
    public static boolean checkd;
    public static String info,unit,image,detailedurl;
    public static int minNum,stocks;
    public static double wholesalePrice;
    private TextView details_shop_name,tv_shop_money,shop_unit,shop_minNum,shop_retailPrice;//商品名称 商品价格 袋/件参数 起订数
    private TextView shop_stocks,imported,shelfLife;//可售量 是否进口 保质期
    ShopBean shopBean = new ShopBean ();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate ( R.layout.fragment_details_shop,null );
        initview();
        okhttpShopDate();
        add_shop.setOnClickListener ( new MyOnClickListener ());
        checkBox.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isChecked =true;
                    checkd = isChecked;
                    //Toast.makeText ( getContext (), ""+checkd, Toast.LENGTH_SHORT ).show ();
                }else{
                    isChecked =false;
                    checkd = isChecked;
                    //Toast.makeText ( getContext (), ""+checkd, Toast.LENGTH_SHORT ).show ();
                }
            }
        } );
        return view;
    }

    //初始化
    private void initview() {
        add_shop = view.findViewById (R.id.add_shop );
        details_shop_name =view.findViewById ( R.id.details_shop_name );
        tv_shop_money =view.findViewById ( R.id.tv_shop_money );
        shop_unit =view.findViewById ( R.id.shop_unit );
        shop_minNum =view.findViewById ( R.id.shop_minNum );
        shop_stocks =view.findViewById ( R.id.shop_stocks );
        imported =view.findViewById ( R.id.imported );
        shelfLife =view.findViewById ( R.id.ShelfLife );
        imageView =view.findViewById ( R.id.details_shop_iv );
        shop_retailPrice =view.findViewById ( R.id.retailPrice );
        checkBox = view.findViewById ( R.id.checkbox );
    }

    //点击
    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId ()){
                case R.id.add_shop:
                    mPopwindow = new AddShopPopupwindow(getActivity(), null);
                    mPopwindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    break;
            }
        }
    }

    //okhttp处理服务器数据
    private void okhttpShopDate() {
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

        if (date != null){
            try {
                JSONObject jsonObject  = new JSONObject ( date );
                JSONArray jsonArray = jsonObject.getJSONArray ( "CommodityInfo" );
                for(int i=0;i<jsonArray.length();i++){
                    object=jsonArray.getJSONObject(i);
                    try {
                        //获取到json数据中的activity数组里的内容imported
                        shopBean.setInfo ( object.getString("info") );
                        info = object.getString("info");
                        shopBean.setWholesalePrice ( object.getDouble ("wholesalePrice") );
                        wholesalePrice = object.getDouble("wholesalePrice");
                        shopBean.setUnit ( object.getString("unit") );
                        unit = object.getString("unit");
                        shopBean.setMinNum ( object.getInt("minNum") );
                        minNum = object.getInt("minNum");
                        shopBean.setStocks ( object.getInt("stocks") );
                        stocks = object.getInt("stocks");
                        shopBean.setImported ( object.getInt("imported"));
                        shopBean.setShelfLife (object.getString("ShelfLife"));
                        shopBean.setImage (object.getString("Image"));
                        image = object.getString("Image");
                        detailedurl = object.getString ( "Detailedurl" );
                        shopBean.setRetailPrice ( object.getDouble ( "retailPrice" ) );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                handler.sendEmptyMessage ( 1 );
            } catch (JSONException e) {
                e.printStackTrace ();
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
                    Glide.with (view.getContext ()).load ( shopBean.getImage () ).into ( imageView );
                    break;
            }
        }
    };
}
