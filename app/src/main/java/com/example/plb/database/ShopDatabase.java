package com.example.plb.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by 陈 on 2019/1/15.
 */

public class ShopDatabase extends SQLiteOpenHelper{
    private Context context;
    public ShopDatabase(Context context,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, null,null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        String sql = "create table shop_details(_id integer primary key autoincrement,shop_img varchar,shop_name varchar,shop_summoney float,shop_wholesalePrice float,shop_buyNum int,shop_follow boolean)";
        db.execSQL ( sql );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
