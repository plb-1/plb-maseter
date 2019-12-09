package com.example.plb.bean;

import java.util.List;

/**
 * Created by zhc on 2019/1/11.
 */
public class ShopCartChild{

        private int img;

        private String info;

        private double money;

        private boolean isChecked=true;

        private int numberAddSub;

        @Override
        public String toString() {
            return "ShopCartChild{" +
                    "img=" + img +
                    ", info='" + info + '\'' +
                    ", money=" + money +
                    ", isChecked=" + isChecked +
                    '}';
        }

        public boolean getChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public ShopCartChild(int img, String info, double money,int numberAddSub) {
            this.img = img;
            this.info = info;
            this.money = money;
            this.numberAddSub = numberAddSub;

        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

    public void setNumberAddSub(int numberAddSub) {
        this.numberAddSub = numberAddSub;
    }

    public int getNumberAddSub() {
        return numberAddSub;
    }
}


