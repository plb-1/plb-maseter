package com.example.plb.bean;

/**
 * Created by 123 on 2019/12/12.
 */

public class HomeBean {
    private int img_home;
    private String text_home;
    private String text_home2;

    public HomeBean(int img_home, String text_home, String text_home2) {
        this.img_home = img_home;
        this.text_home = text_home;
        this.text_home2 = text_home2;
    }

    public int getImg_home() {
        return img_home;
    }

    public void setImg_home(int img_home) {
        this.img_home = img_home;
    }

    public String getText_home() {
        return text_home;
    }

    public void setText_home(String text_home) {
        this.text_home = text_home;
    }

    public String getText_home2() {
        return text_home2;
    }

    public void setText_home2(String text_home2) {
        this.text_home2 = text_home2;
    }
}
