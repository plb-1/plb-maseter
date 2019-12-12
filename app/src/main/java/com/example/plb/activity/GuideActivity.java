package com.example.plb.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.plb.activity.MainActivity;
import com.example.plb.R;

public class GuideActivity extends Activity implements View.OnClickListener {
    private Button jump;
    private int j = 5, i = 0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        jump = findViewById(R.id.guide_btn);

        handler.sendEmptyMessage(0);
        handler.sendEmptyMessage(1);

        jump.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                jump.setText(j + " 跳转");
                j -= 1;
                if (j >= 0) {
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            } else if (msg.what == 1) {
                i += 1;
                if (i == 6) {
                    if (sharedPreferences.getBoolean("AUTO_LOGIN",false)) {
                        startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(GuideActivity.this, LoginPageActivity.class));
                    }
                    handler.removeMessages(1);
                    finish();
                }
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guide_btn:
                if (sharedPreferences.getBoolean("AUTO_LOGIN",false)) {
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                }
                handler.removeMessages(1);
                finish();
                break;
        }
    }
}
