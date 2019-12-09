package com.example.plb.bean;

/**
 * Created by zhc on 2019/1/4.
 */
public class ShopCartGroup {

    private String address;
    private boolean isChecked;
    public ShopCartGroup(String address,boolean isChecked) {
        this.address = address;
        this.isChecked=isChecked;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
