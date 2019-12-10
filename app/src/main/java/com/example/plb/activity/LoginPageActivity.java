package com.example.plb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.plb.R;

public class LoginPageActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView registered;
    private EditText loginUser;
    private EditText loginPassword;
    private RelativeLayout loginLoginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        initUI();
        initEven();
    }

    private void initUI() {
        loginUser = findViewById(R.id.login_user);//用户名输入框
        loginPassword = findViewById(R.id.login_password);//密码输入框
        registered = findViewById(R.id.registered);//注册按钮
        loginLoginbtn = findViewById(R.id.login_loginbtn);//登录按钮

        registered.setOnClickListener(this);
    }

    public void initEven(){
        loginUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()!=0&& !"".equals(s)) {
                    loginLoginbtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    loginLoginbtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
                }
            }
        });

        loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()!=0&& !"".equals(s)) {
                    loginLoginbtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    loginLoginbtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registered:
                startActivity(new Intent(LoginPageActivity.this, RegisteredActivity.class));
                break;

        }
    }
}
