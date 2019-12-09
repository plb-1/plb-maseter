package com.example.plb.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plb.R;

/**
 * Created by Administrator on 2019/1/10 0010.
 */

public class BasicInfoFragment extends Fragment{
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.fragment_basic_info,container,false);
     return view;
    }
}
