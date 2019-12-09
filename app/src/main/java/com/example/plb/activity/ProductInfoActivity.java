package com.example.plb.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.plb.R;
import com.example.plb.bean.ProductInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.LogRecord;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.net.Proxy.Type.HTTP;

public class ProductInfoActivity extends Activity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    private ListView mProListView;  //商品的Listview
    private ArrayList<ProductInfo> productInfoList = new ArrayList<>();  //商品数据
    private MyProductInfoAdapter myProductInfoAdapter;              //商品信息适配器
    private Spinner mSpinner;       //综合下拉
    private Context mContext;       //当前上下文
    private EditText mEditSearch;   //搜索框
    private ImageButton mBackBtn;   //返回
    private ImageButton mShoppingCar;         //购物车
    private LinearLayout mShaiXuanLayout;     //筛选



    public JSONObject object;
    private OkHttpClient client = new OkHttpClient();
    private ArrayList<String> httpshopName = new ArrayList<>();
    private ArrayList<String> httpshopImgUrl = new ArrayList<>();
    int num=1;
    private int articleId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_product_info);
        mContext = this;
        Bundle bundle = this.getIntent().getExtras();
        articleId = bundle.getInt("articleId");

        TextView title = findViewById(R.id.title);

        if(articleId==1){
            title.setText("特价商品");
        }else{
            title.setText("热销商品");
        }

        init();
        okhttpShopDate();

    }

    private Handler mHandler = new Handler()
    {

        public void handleMessage(Message msg)
        {
            //更新UI
            Toast.makeText(ProductInfoActivity.this,""+httpshopImgUrl.size(),Toast.LENGTH_SHORT).show();
            initData();
            myProductInfoAdapter.notifyDataSetChanged();
        };
    };


    /*http*/
    private void okhttpShopDate() {
        new Thread (){
            @Override
            public void run() {
                super.run ();
                Request request = new Request.Builder ().url ( "http://39.98.68.40:8080/RetailManager/moreActivity?marketName=五一市场&activityId="+articleId).build ();
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
//        Log.e("jsonJX","jsonJX");
        if (date != null){
            try {
                JSONObject jsonObject  = new JSONObject ( date );
                JSONArray jsonArray = jsonObject.getJSONArray ( "activity" );
                productInfoList  = new ArrayList<>();
                for(int i=0;i<jsonArray.length();i++){
                    object=jsonArray.getJSONObject(i);
                    try {
                        //获取到json数据中的activity数组里的内容imported
                        httpshopName.add(object.getString("activity_item_Name"));
                        httpshopImgUrl.add(object.getString("ImgURL"));
//                        productInfoList.add(new ProductInfo(object.getString("ImgURL") , object.getString("activity_item_Name"),2,false,2,"五一市场"));
//                        productInfoList.get(i).setShopName(object.getString("activity_item_Name"));
//                        productInfoList.get(i).setProImageButtonPath(object.getString("ImgURL"));
//                        Log.e("img",""+object.getString("ImgURL"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
//                ;
            } catch (JSONException e) {
                e.printStackTrace ();
            }
        }
//        myProductInfoAdapter.notifyDataSetChanged();
    }
    /**/



    @SuppressLint("ResourceAsColor")
    private void init(){
        //商品信息
        mProListView = findViewById(R.id.list_view_product_info);
        myProductInfoAdapter = new MyProductInfoAdapter();
//        Log.d("8888", "123");
        mProListView.setAdapter(myProductInfoAdapter);

        productInfoArrayList = productInfoList;
    }


    ArrayList<ProductInfo> screenArrayList = new ArrayList<>();//筛选后的商品
    ArrayList<ProductInfo> productInfoArrayList = new ArrayList<>();//未筛选时的初始商品
    private void initData(){
        //添加商品数据
        int size = httpshopImgUrl.size();
        Log.e("size",""+size);
        for (int i=0;i<7;i++){
            Log.e("name",""+httpshopName.get(i));
            productInfoList.add(new ProductInfo(httpshopImgUrl.get(i) , httpshopName.get(i),2,false,2,"五一市场"));
        }
//        okhttpShopDate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                finish();
                break;
//            case R.id.shaixuan:
//                openShaiXuan();
//                break;
            case R.id.shopping_car_btn:
                break;
        }
    }

    int mins = 0;
    int maxs = 0;

    //综合的子项选择
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(ProductInfoActivity.this,position+""+id,Toast.LENGTH_SHORT).show();
        switch (position){
            case 0:
                break;
            case 1:
                //Toast.makeText(this,"销量",Toast.LENGTH_SHORT).show();
                break;
                //升序降序排列 如果筛选中的集合有内容的话 我们就按筛选的内容来排
            case 2://升序

                break;
            case 3://倒序

                break;
        }
//        Toast.makeText(ProductInfoActivity.this,position+"----"+id,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





    public class MyProductInfoAdapter extends BaseAdapter{

        public int getCount() {
            return productInfoList.size();
        }

        public Object getItem(int position) {
            return productInfoList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final ProductInfo holder;
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_productinfo,null);
            holder = productInfoList.get(position);

            if (convertView!=null){
                convertView.getTag();
            }

            //初始化图片按钮、信息介绍layout
            holder.mProImageButton = convertView.findViewById(R.id.productinfo_img);
            holder.mProDataLayout = convertView.findViewById(R.id.productinfo_data);
            holder.mJinKouImage = convertView.findViewById(R.id.productinfo_shop_jinkou);
            holder.mShopName = convertView.findViewById(R.id.productinfo_shop_name);
            holder.mMinNum = convertView.findViewById(R.id.productinfo_min_shop_number);
            holder.mDanjia = convertView.findViewById(R.id.productinfo_shop_price);
            holder.mMarket = convertView.findViewById(R.id.productinfo_market);

            //处理图片按钮功能
            holder.mProImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //点击显示商品详情/传值
                    Intent intent = new Intent(ProductInfoActivity.this
                            ,DetailsActivity.class);
                    intent.putExtra("shopName",holder.getShopName());
                    intent.putExtra("market",holder.getMarket());
                    startActivityForResult(intent,88888);
                }
            });

            holder.mProDataLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击显示商品详情/传值
                    Intent intent = new Intent(ProductInfoActivity.this
                            ,DetailsActivity.class);
                    intent.putExtra("shopName",holder.getShopName());
                    intent.putExtra("market",holder.getMarket());
                    startActivityForResult(intent,88888);
                }
            });

            //给显示商品模块的各项控件设值
            holder.mProImageButton.setImageURL(holder.getProImageButtonPath());
            holder.mShopName.setText(holder.getShopName()+"");
            holder.mMinNum.setText(holder.getMinNum()+"");
            if(!holder.isShowJK()){
                holder.mJinKouImage.setVisibility(View.INVISIBLE);
            }else {
                holder.mJinKouImage.setVisibility(View.VISIBLE);
            }
            holder.mDanjia.setText(holder.getDanjia()+"");
            holder.mMarket.setText(holder.getMarket()+"");

            convertView.setTag(holder);

            return convertView;
        }
    }


}
