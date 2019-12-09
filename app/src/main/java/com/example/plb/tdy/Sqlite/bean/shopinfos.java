package com.example.plb.tdy.Sqlite.bean;

/**
 * bean 中的类都是数据库表的实体
 */
public class shopinfos {
    private String name;
    private int shopbynum;
    private int shopprice;
    private String shopimg;
    private int ispayment;
    private int Deliverystatus;
    private int shopid;

    public shopinfos(String name, int shopbynum,int shopprice,String shopimg,int ispayment,int Deliverystatus,int shopid) {
        this.name = name;
        this.shopbynum = shopbynum;
        this.shopprice = shopprice;
        this.shopimg = shopimg;
        this.ispayment = ispayment;
        this.Deliverystatus = Deliverystatus;
        this.shopid = shopid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getShopimg() {
        return shopimg;
    }
    public void setShopimg(String shopimg) {
        this.shopimg = shopimg;
    }

    public int getshopprice() {
        return shopprice;
    }
    public void setshopprice(int shopprice) {
        this.shopprice = shopprice;
    }

    public int getShopbynum() {
        return shopbynum;
    }
    public void setShopbynum(int shopbynum) {
        this.shopbynum = shopbynum;
    }

    public int getIspayment() {
        return ispayment;
    }
    public void setIspayment(int ispayment) {
        this.shopprice = ispayment;
    }

    public int getDeliverystatus() {
        return Deliverystatus;
    }
    public void setDeliverystatus(int Deliverystatus) {
        this.shopbynum = Deliverystatus;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    @Override
    public String toString() {
        return this.name+"-"+this.shopimg+"-"+this.shopbynum+"-"+this.shopprice;
    }
}
