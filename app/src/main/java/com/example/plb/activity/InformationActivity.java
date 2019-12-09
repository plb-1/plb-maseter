package com.example.plb.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.plb.R;
import com.example.plb.fragment.MyAccountFragment;
import com.example.plb.fragment.MyShopFragment;


public class InformationActivity extends AppCompatActivity implements View.OnClickListener{

        private RelativeLayout myShop,myAccount;

        private TextView myShopText,myAccountText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_information);
            ActionBar();
            init();

            replaceFragment(new MyShopFragment());
        }

        private void init(){
            myShop = findViewById(R.id.my_shop);
            myAccount = findViewById(R.id.my_account);
            myShopText = findViewById(R.id.my_shop_text);
            myAccountText = findViewById(R.id.my_account_text);
            myShop.setOnClickListener(this);
            myAccount.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.my_shop:
                    myShopText.setTextColor(getResources().getColor(R.color.orange));
                    myAccountText.setTextColor (getResources().getColor(
                            R.color.deep_gray ));
                    replaceFragment(new MyShopFragment());
                    break;
                case R.id.my_account:
                    myAccountText.setTextColor (getResources().getColor
                            (R.color.orange));
                    myShopText.setTextColor(getResources().getColor
                            (R.color.deep_gray));
                    replaceFragment(new MyAccountFragment());
                    break;
            }
        }

        private void replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.f_layout,fragment);
            transaction.commit();
        }

        private void ActionBar(){
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null){
                actionBar.hide();
            }
        }
    }
