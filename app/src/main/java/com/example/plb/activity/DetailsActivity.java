package com.example.plb.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
//import com.example.plb.adapter.DetailAdapter;
import com.example.plb.adapter.DetailAdapter;
import com.example.plb.bean.ShopBean;
import com.example.plb.fragment.DetailsDetailsFragment;
import com.example.plb.fragment.DetailsShopFragment;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
//    private DetailsShopFragment fragmentShop;
//    private DetailsDetailsFragment fragmentDetails;
    //private TextView textView1,textView2;
    private ImageView imageView;
    private ImageButton imageButton;
    private RecyclerView rv_home;
    private AddShopPopupwindow mPopwindow;
    private TextView shop;
    private CheckBox checkBox;//关注
    public static boolean checkd;
    private Button add_shop;//加入进货单
    ShopBean shopBean = new ShopBean ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        //得到当前界面的装饰视图
        /*if(Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/
        /*getWindow().setStatusBarColor(getResources ().getColor ( R.color.orange ));*/
        setContentView ( R.layout.activity_details );
        /*textView1 = findViewById ( R.id.tvOne );
        textView2 = findViewById ( R.id.tvTwo );*/
        imageView = findViewById ( R.id.iv_shop );
        shop = findViewById(R.id.shop);
        imageButton = findViewById ( R.id.ib_back );
        add_shop = findViewById(R.id.add_shop);

        rv_home = findViewById(R.id.rv_home);
        checkBox = findViewById(R.id.checkbox);
        //initDate();

        DetailAdapter adapter = new DetailAdapter(DetailsActivity.this);
        GridLayoutManager manager = new GridLayoutManager(DetailsActivity.this,1);
        //设置画度大小的监听
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
               if(position == 0){
                    shop.setText("商品");
                }else if(position == 1){
                    shop.setText("详情");
                }
                return 1;
           }
        });
        rv_home.setAdapter(adapter);
        rv_home.setLayoutManager(manager);
       // rv_home.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        /*textView1.setOnClickListener ( this );
        textView2.setOnClickListener ( this );*/
        imageView.setOnClickListener ( this );
        imageButton.setOnClickListener ( this );
        add_shop.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isChecked = true;
                    checkd = isChecked;
                } else {
                    isChecked = false;
                    checkd = isChecked;
                }
            }
        });
       //FragmentManager fragmentManager = getSupportFragmentManager ();
        //FragmentTransaction transaction = fragmentManager.beginTransaction ();
        //transaction.replace ( R.id.rl_shop,fragmentShop );
        //transaction.commit ();
        /*viewPager.setAdapter(new DetailAdapter(getSupportFragmentManager()));
        tablayout.setVisibility(View.VISIBLE);
        tablayout.setupWithViewPager(viewPager);*/
    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        //FragmentManager fm = getSupportFragmentManager ();
        //FragmentTransaction transaction = fm.beginTransaction ();
        switch (v.getId ()) {
            /*case R.id.tvOne:
                transaction.replace ( R.id.rl_shop, fragmentShop );
                textView1.setTextColor ( getResources ().getColor ( R.color.orange ) );
                textView2.setTextColor ( getResources ().getColor ( R.color.black ) );
                break;

            case R.id.tvTwo:
                transaction.replace ( R.id.rl_shop, fragmentDetails );
                textView2.setTextColor ( getResources ().getColor ( R.color.orange ) );
                textView1.setTextColor ( getResources ().getColor ( R.color.black ) );
                break;*/
            case R.id.add_shop:
                mPopwindow = new AddShopPopupwindow(DetailsActivity.this, null);
                mPopwindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.iv_shop:
                showPopupMenu(imageView);
                break;
            case R.id.ib_back:
                finish ();
                break;
        }
       // transaction.commit ();
    }

    //状态栏菜单
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this, view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.shop_menu, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
            }
        });
        popupMenu.show();
    }
}
