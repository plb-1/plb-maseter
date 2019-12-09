package com.example.plb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plb.R;

public class LoginPageActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private TextView registered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        initUI();
    }

    private void initUI() {
        registered = findViewById(R.id.registered);
        registered.setOnClickListener(this);
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
