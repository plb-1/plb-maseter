package com.example.plb.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/1/11.
 */

public class MoneyBean implements Serializable {
    private int money;

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
