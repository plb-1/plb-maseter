package com.example.plb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
import com.example.plb.Utils.NetWorkUtils;
import com.example.plb.Utils.TimeCount;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText regAccount;
    private EditText regPhone;
    private RelativeLayout regBtn;
    private Button regYzcodeBtn;
    private EditText regYzcode;
    private EditText regPwd;
    private CheckBox regCheckbox;
    private ImageView exit;

    private String yzCodeURL = "http://49.233.148.75/RetailManager/sms";
    private String TAG = "RegisteredActivity";

    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
        initEven();
    }

    private void initEven() {
        regAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() != 0 && !"".equals(s)) {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
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
                if (s.toString().length() != 0 && !"".equals(s)) {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    regBtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
                }
            }
        });
    }

    private void initView() {

        regAccount = findViewById(R.id.reg_account);//店铺老板姓名输入框
        regPhone = findViewById(R.id.reg_phone);//店铺老板电话号码输入框
        regYzcode = findViewById(R.id.reg_yzcode);//验证码输入框
        regPwd = findViewById(R.id.reg_pwd);//密码输入框

        regYzcodeBtn = findViewById(R.id.reg_yzcode_btn);//手机验证码按钮
        regYzcodeBtn.setOnClickListener(this);
        time = new TimeCount(15000,1000,regYzcodeBtn);

        regCheckbox = findViewById(R.id.reg_checkbox);//是否同意用户许可

        exit = findViewById(R.id.exit);//返回按钮
        exit.setOnClickListener(this);

        regBtn = findViewById(R.id.reg_btn);//登录按钮
        regBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.reg_btn:
                if (regCheckbox.isChecked()) {
                    if (regAccount.getText().length() > 0 && regPhone.getText().length() > 0 && regYzcode.getText().length() > 0 &&
                            regPwd.getText().length() > 0) {

                    } else {
                        Toast.makeText(RegisteredActivity.this, "请先输入注册信息", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisteredActivity.this, "请先同意用户许可", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reg_yzcode_btn:
                if (regPhone.getText().length()!=0&&!"".equals(regPhone.getText().toString())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            time.start();
                            String requestContent = "请求数据";
                            requestContent = NetWorkUtils.get(yzCodeURL,regPhone.getText().toString());
                            Log.e(TAG,requestContent);
                        }
                    }).start();
                }else {
                    Toast.makeText(RegisteredActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
