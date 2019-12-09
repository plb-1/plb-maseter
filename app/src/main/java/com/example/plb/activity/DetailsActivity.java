package com.example.plb.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
import com.example.plb.fragment.DetailsDetailsFragment;
import com.example.plb.fragment.DetailsShopFragment;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private DetailsShopFragment fragmentShop;
    private DetailsDetailsFragment fragmentDetails;
    private TextView textView1,textView2;
    private ImageView imageView;
    private ImageButton imageButton;

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
        textView1 = findViewById ( R.id.tvOne );
        textView2 = findViewById ( R.id.tvTwo );
        imageView = findViewById ( R.id.iv_shop );
        imageButton = findViewById ( R.id.ib_back );
        textView1.setOnClickListener ( this );
        textView2.setOnClickListener ( this );
        imageView.setOnClickListener ( this );
        imageButton.setOnClickListener ( this );
        fragmentShop  = new DetailsShopFragment ();
        fragmentDetails = new DetailsDetailsFragment ();
        FragmentManager fragmentManager = getSupportFragmentManager ();
        FragmentTransaction transaction = fragmentManager.beginTransaction ();
        transaction.replace ( R.id.rl_shop,fragmentShop );
        transaction.commit ();
    }



    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager ();
        FragmentTransaction transaction = fm.beginTransaction ();
        switch (v.getId ()) {
            case R.id.tvOne:
                transaction.replace ( R.id.rl_shop, fragmentShop );
                textView1.setTextColor ( getResources ().getColor ( R.color.orange ) );
                textView2.setTextColor ( getResources ().getColor ( R.color.black ) );
                break;

            case R.id.tvTwo:
                transaction.replace ( R.id.rl_shop, fragmentDetails );
                textView2.setTextColor ( getResources ().getColor ( R.color.orange ) );
                textView1.setTextColor ( getResources ().getColor ( R.color.black ) );
                break;
            case R.id.iv_shop:
                showPopupMenu(imageView);
                break;
            case R.id.ib_back:
                finish ();
                break;
        }
        transaction.commit ();
    }


    //状态栏菜单
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
