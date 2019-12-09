package com.example.plb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.plb.R;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_registered, forget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        init();
    }

    private void init() {
        tv_registered = findViewById(R.id.tv_registered);
        forget_password = findViewById(R.id.forget_password);
        tv_registered.setOnClickListener(this);
        forget_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_registered:
                startActivity(new Intent(RegisteredActivity.this, StoreCertificationActivity.class));
                break;
            case R.id.forget_password:
                finish();
                break;
        }
    }

}
