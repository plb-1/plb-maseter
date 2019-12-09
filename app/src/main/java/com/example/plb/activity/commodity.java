package com.example.plb.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plb.R;
//商品详情

public class commodity extends AppCompatActivity implements View.OnClickListener {
    private String[] groups = {"门店1", "门店2","门店3"};
    private String[][] childs={{"A1","A2","A3","A4"},{"A1","A2","A3", "B4"},{"A1","A2","A3","C4"}};
    private ExpandableListView expandableListView;
    Button button,back;

    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);
        expandableListView = findViewById(R.id.expandableListView);

        expandableListView.setAdapter(new MyExpandableListView());
        initView();
    }
    private void initView(){
        button = findViewById(R.id.settlement);
        button.setOnClickListener(this);
        back = findViewById(R.id.commodity_back);
        back.setOnClickListener(this);
        checkBox = findViewById(R.id.all);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.settlement:
                Intent intent = new Intent(this,FirmOrder.class);
                startActivity(intent);

                break;
            case R.id.commodity_back:
                finish();
                break;
        }
    }

    class MyExpandableListView  extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childs[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childs[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_group, null);
            }
            TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group);
            tv_group.setText(groups[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_child, null);
            }

            ImageView iv_child = (ImageView) convertView.findViewById(R.id.iv_child);
            TextView tv_child = (TextView) convertView.findViewById(R.id.tv_child);

            //iv_child.setImageResource(resId);
            tv_child.setText(childs[groupPosition][childPosition]);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
