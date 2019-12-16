package com.example.plb.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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

import java.io.IOException;
import java.util.HashMap;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {


    private EditText regAccount;
    private EditText regPhone;
    private RelativeLayout regBtn;
    private Button regYzcodeBtn;
    private EditText regYzcode;
    private EditText regPwd;
    private CheckBox regCheckbox;
    private ImageView exit;

    private String yzCodeURL = "http://49.233.148.75/RetailManager/sms";
    private String regURL = "http://49.233.148.75/RetailManager/registerUser";
    private String TAG = "RegisteredActivity";

    private TimeCount time;
    private EditText regOkPwd;
    private String requestContent = "请求数据";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(RegisteredActivity.this,"手机号码不正确",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
        initEven();
    }

    private void initEven() {
        //根据输入框状态 改变按钮颜色
        regAccount.addTextChangedListener(this);
        regPhone.addTextChangedListener(this);
        regYzcode.addTextChangedListener(this);
        regPwd.addTextChangedListener(this);
        regOkPwd.addTextChangedListener(this);
    }

    private void initView() {

        regAccount = findViewById(R.id.reg_account);//店铺老板姓名输入框
        regPhone = findViewById(R.id.reg_phone);//店铺老板电话号码输入框
        regYzcode = findViewById(R.id.reg_yzcode);//验证码输入框
        regPwd = findViewById(R.id.reg_pwd);//密码输入框
        regOkPwd = findViewById(R.id.reg_ok_pwd);//确认密码

        regYzcodeBtn = findViewById(R.id.reg_yzcode_btn);//手机验证码按钮
        regYzcodeBtn.setOnClickListener(this);
        time = new TimeCount(30000, 1000, regYzcodeBtn);

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
                            regPwd.getText().length() > 0 && regOkPwd.getText().length() > 0) {
                        if (requestContent.equals(regYzcode.getText().toString())) {
                            if (regAccount.getText().length()>3&&regAccount.getText().length()<12) {
                                if (isMobileNumber(regPhone.getText().toString())) {
                                    if (regPwd.getText().length()>5&&regPwd.getText().length()<12) {
                                        if (regPwd.getText().toString().equals(regOkPwd.getText().toString())) {
                                            HashMap regMap = new HashMap();
                                            regMap.put("userName",regAccount.getText().toString());
                                            regMap.put("phone",regPhone.getText().toString());
                                            regMap.put("authCode",regYzcode.getText().toString());
                                            regMap.put("password",regPwd.getText().toString());
                                            regMap.put("okPassword",regOkPwd.getText().toString());

                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    String retrunMsg = "";
                                                    try {
                                                        retrunMsg = NetWorkUtils.postFrom(regURL,regMap);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    Log.e(TAG, "onClick: retrunMsg ---> "+retrunMsg);
                                                }
                                            }).start();
                                        }else {
                                            Toast.makeText(RegisteredActivity.this,"两次输入密码不匹配",Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(RegisteredActivity.this,"密码必须是6-12个字符",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(RegisteredActivity.this,"手机号码格式不正确",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(RegisteredActivity.this,"店主名字格式不正确（必须是4-12个字符）",Toast.LENGTH_SHORT).show();
                            }
                        }
                        requestContent = "";
                    } else {
                        Toast.makeText(RegisteredActivity.this, "请先输入注册信息", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisteredActivity.this, "请先同意用户许可", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reg_yzcode_btn:
                if (regPhone.getText().length() != 0 && !"".equals(regPhone.getText().toString())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (isMobileNumber(regPhone.getText().toString())) {
                                time.start();
                                try {
                                    requestContent = NetWorkUtils.get(yzCodeURL, regPhone.getText().toString());
                                    if (requestContent.equals("注册成功")) {
                                        Toast.makeText(RegisteredActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.e(TAG, requestContent);
                            }else {
                                handler.sendEmptyMessage(1);
                            }
                        }
                    }).start();
                } else {
                    Toast.makeText(RegisteredActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //验证手机号
    public static boolean isMobileNumber(String mobiles) {
        String telRegex = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
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
            regBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            regBtn.setBackgroundColor(getResources().getColor(R.color.login_btn_gray));
        }
    }
}
