package com.example.plb.fragment;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
>>>>>>> 12.18 Ls与仓库重新建立连接
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
<<<<<<< HEAD
=======
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
>>>>>>> 12.18 Ls与仓库重新建立连接
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
=======
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
>>>>>>> 12.18 Ls与仓库重新建立连接
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.plb.R;
import com.example.plb.activity.ProductInfoActivity;
import com.example.plb.adapter.MyAdapter;
<<<<<<< HEAD
import com.example.plb.adapter.ShopStoreAdapter_home;
import com.example.plb.bean.ShopStore_home;
import com.example.plb.bean.Shop_home;
=======
import com.example.plb.adapter.MyHomeAdapter;
import com.example.plb.adapter.ShopStoreAdapter_home;
import com.example.plb.bean.HomeBean;
import com.example.plb.bean.ShopStore_home;
import com.example.plb.bean.Shop_home;
import com.gyf.immersionbar.ImmersionBar;
>>>>>>> 12.18 Ls与仓库重新建立连接
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by zhc on 2018/12/27.
 * 首页
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    private View view;
    private ViewPager viewPager;
    private ViewPager viewPager_hot,viewPager_hot2;
    private MyAdapter adapter;
    private MyAdapter adapter_hot,adapter_hot2;
    private ShopStoreAdapter_home shopStoreAdapter_home;
    private List<Fragment> fragments;
    private List<Fragment> fragments_hot,fragments_hot2;
    private ImageView dot1,dot2,dot3,dot4;
    private FragmentManager manager;
<<<<<<< HEAD
    private ListView listView;


    private TextView action_season_home,tv_tejia_home,tv_hot_home; //季节活动,特价商品，热销商品*/
    private ImageView image_season_home; //季节活动图片
    private TextView season_ty1,season_ty2,season_ty3;
    private TextView season_ac1,season_ac2,season_ac3;
    private ImageView season_im1,season_im2,season_im3;
=======


    private TextView action_season_home,tv_tejia_home,tv_hot_home; //季节活动,特价商品，热销商品*/
>>>>>>> 12.18 Ls与仓库重新建立连接
    private TextView tj_ty1,tj_ty2,tj_ty3,tj_ty4,tj_ty5,tj_ty6;
    private TextView tj_ac1,tj_ac2,tj_ac3,tj_ac4,tj_ac5,tj_ac6;
    private ImageView tj_im1,tj_im2,tj_im3,tj_im4,tj_im5,tj_im6;
    private TextView mActionMore;   //特价商品更多
    private TextView mHotMore;      //热销商品更多
    private LinearLayout mShopLayout1;   //特价商品1
    private LinearLayout mShopLayout2;   //特价商品2
    private LinearLayout mShopLayout3;   //特价商品3
    private LinearLayout mShopLayout4;   //特价商品4
    private LinearLayout mShopLayout5;   //特价商品5
    private LinearLayout mShopLayout6;   //特价商品6
    private LinearLayout mShopLayout7;   //特价商品7
    private LinearLayout mShopLayout8;   //特价商品8
    private LinearLayout mShopLayout9;   //特价商品9
    private Intent intent;
    String season_Image;
    String season_ic1,season_ic2,season_ic3;
    String tejia_ic1,tejia_ic2,tejia_ic3,tejia_ic4,tejia_ic5,tejia_ic6;
<<<<<<< HEAD
=======
    private RelativeLayout title_home;
>>>>>>> 12.18 Ls与仓库重新建立连接
    int i=0,k=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< HEAD
        view = inflater.inflate(R.layout.fragment_home,null);
=======
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  //设置为全屏
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//状态栏字体颜色设置为黑色这个是Android 6.0才出现的属性   默认是白色
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//设置为透明色
            window.setNavigationBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        view = inflater.inflate(R.layout.fragment_hom,null);
>>>>>>> 12.18 Ls与仓库重新建立连接
        init(view);
        getDataFormNet();
        manager = getActivity().getSupportFragmentManager();
        handler.sendEmptyMessage(0);
        Log.e("------", "onCreateView:11 ");
<<<<<<< HEAD
        return view;
    }

=======
        //ImmersionBar.with(this).init();
        int statusBarHeight = getStatusBarHeight(getContext());
        title_home.setPadding(0, statusBarHeight, 0, 0);
        return view;
    }

    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

>>>>>>> 12.18 Ls与仓库重新建立连接
    //联网请求
    private void getDataFormNet() {
        //初始化Okhttputils
        initOkhttpclient();

        String url="http://39.98.68.40:8080/RetailManager/appIndex";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG:","首页请求数据失败=="+e.getMessage());
                    }

                    /**
                     * 联网成功时回调
                     * @param response 请求成功的数据
                     * @param id 区分http（100）和https（101）
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG:","首页请求数据成功=="+response);
                        //解析数据
<<<<<<< HEAD
                       processData(response);
=======
                        processData(response);
>>>>>>> 12.18 Ls与仓库重新建立连接
                    }
                });
    }

    private void processData(String json) {
        Shop_home shop_home=JSON.parseObject(json,Shop_home.class);
        ShopStore_home shopStore_home = JSON.parseObject(json,ShopStore_home.class);
        season_Image=shop_home.getActivity().getData3().get(0).getImgURL();
        season_ic1=shop_home.getActivity().getData3().get(1).getImgURL();
        season_ic2=shop_home.getActivity().getData3().get(2).getImgURL();
        season_ic3=shop_home.getActivity().getData3().get(3).getImgURL();
        tejia_ic1=shop_home.getActivity().getData1().get(0).getImgURL();
        tejia_ic2=shop_home.getActivity().getData1().get(1).getImgURL();
        tejia_ic3=shop_home.getActivity().getData1().get(2).getImgURL();
        tejia_ic4=shop_home.getActivity().getData1().get(3).getImgURL();
        tejia_ic5=shop_home.getActivity().getData1().get(4).getImgURL();
        tejia_ic6=shop_home.getActivity().getData1().get(5).getImgURL();

        change(shop_home);
<<<<<<< HEAD
     /* //设置ShopStore的适配器
        shopStoreAdapter_home=new ShopStoreAdapter_home(getContext(),shopStore_home);
        listView.setAdapter(shopStoreAdapter_home);*/
    }

    private void change(Shop_home shop_home) {
        action_season_home.setText(shop_home.getActivity().getType().get(2).getActivity_theme());
        tv_tejia_home.setText(shop_home.getActivity().getType().get(0).getActivity_theme());
        tv_hot_home.setText(shop_home.getActivity().getType().get(1).getActivity_theme());
        //季节活动
        season_ty1.setText(shop_home.getActivity().getData3().get(1).getActivity_item_Name());
        season_ac1.setText(shop_home.getActivity().getData3().get(1).getIntroduce());
        season_ty2.setText(shop_home.getActivity().getData3().get(2).getActivity_item_Name());
        season_ac2.setText(shop_home.getActivity().getData3().get(2).getIntroduce());
        season_ty3.setText(shop_home.getActivity().getData3().get(3).getActivity_item_Name());
        season_ac3.setText(shop_home.getActivity().getData3().get(3).getIntroduce());
//        Glide.with(this).load(season_Image).into(image_season_home);
        Glide.with(this).load(season_ic1).into(season_im1);
        Glide.with(this).load(season_ic2).into(season_im2);
        Glide.with(this).load(season_ic3).into(season_im3);
=======
    }

    private void change(Shop_home shop_home) {
>>>>>>> 12.18 Ls与仓库重新建立连接
        //特价活动
        tj_ty1.setText(shop_home.getActivity().getData1().get(0).getActivity_item_Name());
        tj_ac1.setText(shop_home.getActivity().getData1().get(0).getIntroduce());
        tj_ty2.setText(shop_home.getActivity().getData1().get(1).getActivity_item_Name());
        tj_ac2.setText(shop_home.getActivity().getData1().get(1).getIntroduce());
        tj_ty3.setText(shop_home.getActivity().getData1().get(2).getActivity_item_Name());
        tj_ac3.setText(shop_home.getActivity().getData1().get(2).getIntroduce());
        tj_ty4.setText(shop_home.getActivity().getData1().get(3).getActivity_item_Name());
        tj_ac4.setText(shop_home.getActivity().getData1().get(3).getIntroduce());
        tj_ty5.setText(shop_home.getActivity().getData1().get(4).getActivity_item_Name());
        tj_ac5.setText(shop_home.getActivity().getData1().get(4).getIntroduce());
        tj_ty6.setText(shop_home.getActivity().getData1().get(5).getActivity_item_Name());
        tj_ac6.setText(shop_home.getActivity().getData1().get(5).getIntroduce());
        Glide.with(this).load(tejia_ic1).into(tj_im1);
        Glide.with(this).load(tejia_ic2).into(tj_im2);
        Glide.with(this).load(tejia_ic3).into(tj_im3);
        Glide.with(this).load(tejia_ic4).into(tj_im4);
        Glide.with(this).load(tejia_ic5).into(tj_im5);
        Glide.with(this).load(tejia_ic6).into(tj_im6);

    }

    private void initOkhttpclient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L,TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    private void init(View view) {
<<<<<<< HEAD
        viewPager = view.findViewById(R.id.viewPager1);
        viewPager_hot=view.findViewById(R.id.viewPager2);
        viewPager_hot2 = view.findViewById(R.id.viewPager3);
        listView=view.findViewById(R.id.listview_home);
=======
        title_home=view.findViewById(R.id.title_home);
        viewPager = view.findViewById(R.id.viewPager1);
        viewPager_hot=view.findViewById(R.id.viewPager2);
>>>>>>> 12.18 Ls与仓库重新建立连接
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment_1());
        fragments.add(new HomeFragment_2());
        fragments.add(new HomeFragment_3());
        fragments.add(new HomeFragment_4());

        fragments_hot = new ArrayList<>();
        fragments_hot.add(new HomeFragment_hot_1());
        fragments_hot.add(new HomeFragment_hot_2());

        fragments_hot2 = new ArrayList<>();
        fragments_hot2.add(new HomeFragment_hot_3());
        fragments_hot2.add(new HomeFragment_hot_4());


<<<<<<< HEAD
        action_season_home=view.findViewById(R.id.action_season_home);
        tv_tejia_home=view.findViewById(R.id.tv_tejia_home);
        tv_hot_home=view.findViewById(R.id.tv_hot_home);
//        image_season_home=view.findViewById(R.id.image_season_home);
        season_ty1=view.findViewById(R.id.season_type1);
        season_ty2=view.findViewById(R.id.season_type2);
        season_ty3=view.findViewById(R.id.season_type3);
        season_ac1=view.findViewById(R.id.season_action1);
        season_ac2=view.findViewById(R.id.season_action2);
        season_ac3=view.findViewById(R.id.season_action3);
        season_im1=view.findViewById(R.id.season_image1);
        season_im2=view.findViewById(R.id.season_image2);
        season_im3=view.findViewById(R.id.season_image3);
=======
        tv_tejia_home=view.findViewById(R.id.tv_tejia_home);
        tv_hot_home=view.findViewById(R.id.tv_hot_home);
>>>>>>> 12.18 Ls与仓库重新建立连接
        tj_ty1=view.findViewById(R.id.tejia_type1);
        tj_ty2=view.findViewById(R.id.tejia_type2);
        tj_ty3=view.findViewById(R.id.tejia_type3);
        tj_ty4=view.findViewById(R.id.tejia_type4);
        tj_ty5=view.findViewById(R.id.tejia_type5);
        tj_ty6=view.findViewById(R.id.tejia_type6);
        tj_ac1=view.findViewById(R.id.tejia_action1);
        tj_ac2=view.findViewById(R.id.tejia_action2);
        tj_ac3=view.findViewById(R.id.tejia_action3);
        tj_ac4=view.findViewById(R.id.tejia_action4);
        tj_ac5=view.findViewById(R.id.tejia_action5);
        tj_ac6=view.findViewById(R.id.tejia_action6);
        tj_im1=view.findViewById(R.id.tejia_image1);
        tj_im2=view.findViewById(R.id.tejia_image2);
        tj_im3=view.findViewById(R.id.tejia_image3);
        tj_im4=view.findViewById(R.id.tejia_image4);
        tj_im5=view.findViewById(R.id.tejia_image5);
        tj_im6=view.findViewById(R.id.tejia_image6);



        // 点击进入商品详细介绍页面
<<<<<<< HEAD
        mShopLayout1 = view.findViewById(R.id.home_shop_layout1);
        mShopLayout2 = view.findViewById(R.id.home_shop_layout2);
        mShopLayout3 = view.findViewById(R.id.home_shop_layout3);
=======
>>>>>>> 12.18 Ls与仓库重新建立连接
        mShopLayout4 = view.findViewById(R.id.home_shop_layout4);
        mShopLayout5 = view.findViewById(R.id.home_shop_layout5);
        mShopLayout6 = view.findViewById(R.id.home_shop_layout6);
        mShopLayout7 = view.findViewById(R.id.home_shop_layout7);
        mShopLayout8 = view.findViewById(R.id.home_shop_layout8);
        mShopLayout9 = view.findViewById(R.id.home_shop_layout9);
        mActionMore = view.findViewById(R.id.action_more);
        mHotMore = view.findViewById(R.id.hot_more);

<<<<<<< HEAD
        mShopLayout1.setOnClickListener(this);
        mShopLayout2.setOnClickListener(this);
        mShopLayout3.setOnClickListener(this);
=======
>>>>>>> 12.18 Ls与仓库重新建立连接
        mShopLayout4.setOnClickListener(this);
        mShopLayout5.setOnClickListener(this);
        mShopLayout6.setOnClickListener(this);
        mShopLayout7.setOnClickListener(this);
        mShopLayout8.setOnClickListener(this);
        mShopLayout9.setOnClickListener(this);
        mActionMore.setOnClickListener(this);
        mHotMore.setOnClickListener(this);

        dot1 = view.findViewById(R.id.dot1);
        dot2 = view.findViewById(R.id.dot2);
        dot3 = view.findViewById(R.id.dot3);
        dot4 = view.findViewById(R.id.dot4);
        dot1.setOnClickListener(this);
        dot2.setOnClickListener(this);
        dot3.setOnClickListener(this);
        dot4.setOnClickListener(this);

        adapter = new MyAdapter(getChildFragmentManager(),fragments);
        adapter_hot=new MyAdapter(getChildFragmentManager(),fragments_hot);
        adapter_hot2 = new MyAdapter(getChildFragmentManager(),fragments_hot2);
        viewPager.setAdapter(adapter);
        viewPager_hot.setAdapter(adapter_hot);
<<<<<<< HEAD
        viewPager_hot2.setAdapter(adapter_hot2);
=======
>>>>>>> 12.18 Ls与仓库重新建立连接

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int j) {
                switchImage(j);
                i=j;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
<<<<<<< HEAD
=======
        //好物推荐
        List<HomeBean> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new HomeBean(R.mipmap.img_home,"11111111","￥55"));
        }
        MyHomeAdapter myHomeAdapter=new MyHomeAdapter(list);
        RecyclerView recyclerView=view.findViewById(R.id.recy_home);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myHomeAdapter);

>>>>>>> 12.18 Ls与仓库重新建立连接
    }
    private void initImg(int...index){
        dot1.setImageResource(index[0]);
        dot2.setImageResource(index[1]);
        dot3.setImageResource(index[2]);
        dot4.setImageResource(index[3]);
    }
    private void switchImage(int position) {
        switch (position) {
            case 0:
                initImg(R.mipmap.dot_1, R.mipmap.dot_2,
                        R.mipmap.dot_2, R.mipmap.dot_2);
                break;
            case 1:
                initImg(R.mipmap.dot_2, R.mipmap.dot_1,
                        R.mipmap.dot_2, R.mipmap.dot_2);
                break;
            case 2:
                initImg(R.mipmap.dot_2, R.mipmap.dot_2,
                        R.mipmap.dot_1, R.mipmap.dot_2);
                break;
            case 3:
                initImg(R.mipmap.dot_2, R.mipmap.dot_2,
                        R.mipmap.dot_2, R.mipmap.dot_1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = manager.beginTransaction();
        if(intent==null)
<<<<<<< HEAD
        intent = new Intent(getActivity(), ProductInfoActivity.class);
=======
            intent = new Intent(getActivity(), ProductInfoActivity.class);
>>>>>>> 12.18 Ls与仓库重新建立连接
        switch (v.getId()){
            case R.id.dot1:
                viewPager.setCurrentItem(0);
                i=0;
                break;
            case R.id.dot2:
                viewPager.setCurrentItem(1);
                i=1;
                break;
            case R.id.dot3:
                viewPager.setCurrentItem(2);
                i=2;
                break;
            case R.id.dot4:
                viewPager.setCurrentItem(3);
                i=3;
                break;

            case R.id.action_more:
                Toast.makeText(getContext(),"articleId",Toast.LENGTH_SHORT).show();
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
            case R.id.hot_more:
                intent.putExtra("articleId",2);
                startActivity(intent);
                break;
<<<<<<< HEAD
            case R.id.home_shop_layout1:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
            case R.id.home_shop_layout2:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
            case R.id.home_shop_layout3:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
=======
>>>>>>> 12.18 Ls与仓库重新建立连接
            case R.id.home_shop_layout4:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
            case R.id.home_shop_layout5:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
            case R.id.home_shop_layout6:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
            case R.id.home_shop_layout7:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
            case R.id.home_shop_layout8:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
            case R.id.home_shop_layout9:
                intent.putExtra("articleId",1);
                startActivity(intent);
                break;
        }
        transaction.commit();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                if (i>3){
                    i=0;
                }
                viewPager.setCurrentItem(i);
                i++;
                handler.sendEmptyMessageDelayed(0,2000);
            }
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        handler.removeMessages(0);
    }
<<<<<<< HEAD

 /*   @Override
    public void onStart() {
        super.onStart();
        handler.sendEmptyMessage(0);
    }*/


=======
>>>>>>> 12.18 Ls与仓库重新建立连接
}