package com.example.plb.bean;

/**
 * Created by zhc on 2018/12/25.
 */
public class Tab {

    private Class fragment;
    private int title;
    private int img;

    public Tab(Class fragment, int title, int img) {
        this.fragment = fragment;
        this.title = title;
        this.img = img;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
