package com.example.plb.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/1/9.
 */

public class FirmOrderItem {
    private Integer firm_imgs;
    private String frim_txt;
    private int firm_num;
    private List<String> sp;
    private int firm_money;
    public FirmOrderItem() {
    }

    public FirmOrderItem(Integer firm_imgs, String frim_txt, int firm_num, List<String> sp, int firm_money) {
        this.firm_imgs = firm_imgs;
        this.frim_txt = frim_txt;
        this.firm_num = firm_num;
        this.sp = sp;
        this.firm_money = firm_money;
    }

    public void setFirm_imgs(Integer firm_imgs) {
        this.firm_imgs = firm_imgs;
    }

    public void setFrim_txt(String frim_txt) {
        this.frim_txt = frim_txt;
    }

    public void setFirm_num(int firm_num) {
        this.firm_num = firm_num;
    }

    public void setSp(List<String> sp) {
        this.sp = sp;
    }

    public void setFirm_money(int firm_money) {
        this.firm_money = firm_money;
    }

    public Integer getFirm_imgs() {
        return firm_imgs;
    }

    public String getFrim_txt() {
        return frim_txt;
    }

    public int getFirm_num() {
        return firm_num;
    }

    public List<String> getSp() {
        return sp;
    }

    public int getFirm_money() {
        return firm_money;
    }
}
