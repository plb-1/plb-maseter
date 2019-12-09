package com.example.plb.bean;

import java.util.List;

/**
 * Created by 陈 on 2019/1/7.
 */

public class ShopBean {

        private String unit;
        private int imported;
        private String shelfLife;
        private String setailedurl;
        private String image;
        private double wholesalePrice;
        private double retailPrice;
        private String brand;
        private int stocks;
        private int minNum;
        private String info;

    public ShopBean(String unit, int imported, String shelfLife, String setailedurl, String image, double wholesalePrice, double retailPrice, String brand, int stocks, int minNum, String info) {
        this.unit = unit;
        this.imported = imported;
        this.shelfLife = shelfLife;
        this.setailedurl = setailedurl;
        this.image = image;
        this.wholesalePrice = wholesalePrice;
        this.retailPrice = retailPrice;
        this.brand = brand;
        this.stocks = stocks;
        this.minNum = minNum;
        this.info = info;
    }

    public ShopBean() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getImported() {
        return imported;
    }

    public void setImported(int imported) {
        this.imported = imported;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getSetailedurl() {
        return setailedurl;
    }

    public void setSetailedurl(String setailedurl) {
        this.setailedurl = setailedurl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
