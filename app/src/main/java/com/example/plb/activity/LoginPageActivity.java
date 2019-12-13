package com.example.plb.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
import com.example.plb.Utils.NetWorkUtils;

import java.io.IOException;


public class LoginPageActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private TextView registered;
    private EditText loginUser;
    private EditText loginPassword;
    private RelativeLayout loginLoginbtn;

    private String loginURL = "http://49.233.148.75/RetailManager/Login";
    private String TAG = "LoginPageActivity";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEdittor;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(LoginPageActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    loginUser.setText("");
                    loginPassword.setText("");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);

        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        spEdittor = sharedPreferences.edit();

        initUI();
        initEven();
    }

    private void initUI() {
        loginUser = findViewById(R.id.login_user);//用户名输入框
        loginPassword = findViewById(R.id.login_password);//密码输入框
        registered = findViewById(R.id.registered);//注册按钮
        loginLoginbtn = findViewById(R.id.login_loginbtn);//登录按钮

        registered.setOnClickListener(this);
        loginLoginbtn.setOnClickListener(this);
    }

    public void initEven() {
        //根据输入框状态改变按钮颜色
        loginUser.addTextChangedListener(this);
        loginPassword.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registered:
                startActivity(new Intent(LoginPageActivity.this, RegisteredActivity.class));
                break;
            case R.id.login_loginbtn:
                if (loginUser.getText().length() > 0 && loginPassword.getText().length() > 0
                        && !"".equals(loginUser.getText().toString()) && !"".equals(loginPassword.getText().toString())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String loginRrequset = null;
                                loginRrequset = NetWorkUtils.get(loginURL,loginUser.getText().toString()+"/"+loginPassword.getText().toString());

                                Log.e(TAG, "loginBtnClick: loginRrequset ---> "+loginRrequset );

                                if (!loginRrequset.equals("")) {
                                    //登录成功
                                    handler.sendEmptyMessage(0);

                                    spEdittor.putBoolean("AUTO_LOGIN",true);
                                    spEdittor.putString("USER_NAME",loginUser.getText().toString());
                                    spEdittor.putString("PASS_WORD",loginPassword.getText().toString());

                                    startActivity(new Intent(LoginPageActivity.this, MainActivity.class));
                                }else {
                                    Toast.makeText(LoginPageActivity.this,"登录失败，请仔细检查用户名密码是否输入正确",Toast.LENGTH_SHORT).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    Toast.makeText(LoginPageActivity.this, "请输入用户名，密码", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() != 0 && !"".equals(s)) {
            loginLoginbtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            loginLoginbtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
        }
    }
}
