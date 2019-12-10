package com.example.plb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.plb.R;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText regStore;
    private EditText regAccount;
    private EditText regPhone;
    private EditText regPosition;
    private RelativeLayout regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
        initEven();
    }

    private void initEven() {
        regStore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()!=0&& !"".equals(s)) {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
                }
            }
        });
        regAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()!=0&& !"".equals(s)) {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
                }
            }
        });
        regPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()!=0&& !"".equals(s)) {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
                }
            }
        });
        regPosition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()!=0&& !"".equals(s)) {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
                }
            }
        });
    }

    private void initView() {
        regStore = findViewById(R.id.reg_store);//店铺输入框
        regAccount = findViewById(R.id.reg_account);//店铺老板姓名输入框
        regPhone = findViewById(R.id.reg_phone);//店铺老板电话号码输入框
        regPosition = findViewById(R.id.reg_position);//店铺位置

        regBtn = findViewById(R.id.reg_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.tv_registered:
//                startActivity(new Intent(RegisteredActivity.this, StoreCertificationActivity.class));
//                break;
//            case R.id.forget_password:
//                finish();
//                break;
        }
    }

}
