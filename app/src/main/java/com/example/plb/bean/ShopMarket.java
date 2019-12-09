package com.example.plb.bean;

import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by admin on 2019/1/11.
 */

public class ShopMarket {

    private String market;          //市场

    public TextView mMarketView;  //市场按钮

    public ShopMarket(String market) {
        this.market = market;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

}
