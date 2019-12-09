package com.example.plb.tdy.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MysqliteHelper extends SQLiteOpenHelper {
    public static final String TAG=MysqliteHelper.class.getName();

    /**
     * 构造函数
     * @param context 上下文
     * @param name 数据库名
     * @param factory 游标工厂
     * @param version 数据库版本 必须大于或等于1
     */
    public MysqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 构造函数
     * @param context
     */
    public MysqliteHelper(Context context){
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }



    /**
     * 创建数据库
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.e(TAG,"-------->onCreate");
        String createtb = "create table "/*AUTOINCREMENT 自增*/
                +Constant.TABLE_NAME+"(" +
                "id"+" Integer primary key AUTOINCREMENT,"+
                Constant.NAME+" varchar(10),"+
                Constant.SHOPIMG+" varchar(255),"+
                Constant.SHOPPRICE+" Integer,"+
                Constant.ISPAYMENT+" Integer,"+
                Constant.DELIVERYSTATUS+" Integer,"+
                Constant.SHOPBUYNUM+" Integer)";
        db.execSQL(createtb);
    }

    /**
     * 版本升级
     * @param db 数据库对象
     * @param oldVersion 旧版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.e(TAG,"-------->onUpgrade");
    }

    /**
     * 打开数据库
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
//        Log.e(TAG,"-------->onOpen");
    }
}
