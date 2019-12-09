package com.example.plb.bean;

import java.util.List;

/**
 * 未付款
 * Created by asus on 2019/1/12.
 */

public class PaymentInformation {

    private List<String> imageUrl;  //商品图片
    private List<String> price;     //商品价格
    private Boolean isCheck;        //选中状态
    private String market;          //市场

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public PaymentInformation(List<String> imageUrl, List<String> price, Boolean isCheck, String market) {
        this.imageUrl = imageUrl;
        this.price = price;
        this.isCheck = isCheck;
        this.market = market;
    }
}
