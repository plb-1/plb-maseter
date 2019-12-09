package com.example.plb.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.bean.MoneyBean;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener{
    TextView money;
    Button btn;
    CheckBox zyb,wechat;
    ImageView back;
    int num=0;
    PopupWindow popupWindow;
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
        setContentView(R.layout.activity_payment);
        initView();

        setView();
    }
    private void initView(){
        money = findViewById(R.id.payment_money);
        back=findViewById(R.id.payment_back);
        btn = findViewById(R.id.payment_btn);
        zyb = findViewById(R.id.payment_zyb);
        wechat = findViewById(R.id.payment_wechat);
        back.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.payment_btn:
                if (zyb.isChecked()||wechat.isChecked()){
                    showPopupwindow();
                }
                break;
            case R.id.payment_back:
                finish();
                break;
        }
    }
    private void setView(){
        MoneyBean moneyBean = (MoneyBean) getIntent().getSerializableExtra("money");
        num = moneyBean.getMoney();
        money.setText("￥"+num);
        zyb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    wechat.setChecked(false);
                }
            }
        });
        wechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    zyb.setChecked(false);
                }
            }
        });
    }
    public void showPopupwindow(){
        View inflate = LayoutInflater.from(this).inflate(R.layout.popupwindow_item, null);
        popupWindow = new PopupWindow(inflate, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置位置
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        //设置PopupWindow的View点击事件
        setOnPopupViewClick(inflate);
    }

    private void setOnPopupViewClick(View v) {
        TextView pop_money = v.findViewById(R.id.popupwindow_money);
        TextView pop_back = v.findViewById(R.id.popupwindow_item_back);
        ImageView pop_wen = v.findViewById(R.id.popupwindow_wen);
        FrameLayout f2=v.findViewById(R.id.f2);
        Button pop_btn = v.findViewById(R.id.popupwindow_btn);
        LinearLayout ll = v.findViewById(R.id.popupwindow_ll);
        TextView pop_zhanghao = v.findViewById(R.id.popupwindow_zhuanghao);
        TextView pop_txt = v.findViewById(R.id.popupwindow_txt);
        pop_money.setText(num+"元");
        pop_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        if (wechat.isChecked()){
            f2.setVisibility(View.INVISIBLE);
        }else {
            f2.setVisibility(View.VISIBLE);
        }
        pop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PaymentActivity.this,OrderDetailsActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });
    }

}
