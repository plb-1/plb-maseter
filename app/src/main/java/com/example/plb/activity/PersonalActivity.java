package com.example.plb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.plb.R;

/**
 * Created by asus on 2019/1/2.
 */

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personal);
        //初始化
        init();

    }

    /**
     * 初始化
     */
    private void init() {
//        ImageView iv_exit = findViewById(R.id.iv_exit);
//        ImageView iv_image = findViewById(R.id.iv_image);
//        ImageView iv_order_more = findViewById(R.id.iv_order_more);
//        ImageView iv_obligation = findViewById(R.id.iv_obligation);
//        ImageView iv_paid = findViewById(R.id.iv_paid);
//        ImageView iv_completed = findViewById(R.id.iv_completed);
//        ImageView iv_collection_more = findViewById(R.id.iv_collection_more);
//        ImageView iv_dailyListing_more = findViewById(R.id.iv_dailyListing_more);
//        ImageView iv_serviceCenter_more = findViewById(R.id.iv_serviceCenter_more);
//        ImageView iv_message_more = findViewById(R.id.iv_serviceCenter_more);
//        ImageView iv_dataManagement_more = findViewById(R.id.iv_dataManagement_more);
//        ImageView iv_setting_more = findViewById(R.id.iv_setting_more);
//        Button b_logout = findViewById(R.id.b_logout);
        ImageView rl_myOrder = findViewById(R.id.rl_myOrder);
        rl_myOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_myOrder:
                startActivity(new Intent(PersonalActivity.this, OrderActivity.class));
                break;
        }
    }
}
