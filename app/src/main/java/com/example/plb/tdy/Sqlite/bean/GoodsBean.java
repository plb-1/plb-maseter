package com.example.plb.tdy.Sqlite.bean;

/**
 * Created by louisgeek on 2016/4/27.
 */
public class GoodsBean {

    public static final  int STATUS_INVALID=0;
    public static final  int STATUS_VALID=1;
    //===============================================
    private int id;
    private String name;
    /** 商品宣传图片 */
    private int imageLogo;
    /** 商品规格 */
    private String desc;
    /** 原价，市场价 */
    private double price;
    /** 现价，折扣价 */
    private double discountPrice;
    private int count;
    /** 状态 */
    private int status;



    /** 是否被选中 */
    private boolean isChecked;
    /** 是否是编辑状态 */
    private boolean isEditing;



    public GoodsBean(int id, String name, int imageLogo, String desc, double price, double discountPrice, int count, int status, boolean isChecked, boolean isEditing) {
        this.id = id;
        this.name = name;
        this.imageLogo = imageLogo;
        this.desc = desc;
        this.price = price;
        this.discountPrice = discountPrice;
        this.count = count;
        this.status = status;
        this.isChecked = isChecked;
        this.isEditing = isEditing;
    }

    public GoodsBean(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(int imageLogo) {
        this.imageLogo = imageLogo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }
}
