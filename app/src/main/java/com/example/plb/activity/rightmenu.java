package com.example.plb.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.plb.R;

public class rightmenu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_productinfo_rightmenu);



        Button reset = findViewById(R.id.Reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rightmenu.this,"123",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
