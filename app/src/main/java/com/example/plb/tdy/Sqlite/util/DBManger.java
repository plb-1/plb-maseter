package com.example.plb.tdy.Sqlite.util;

import android.app.Person;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.plb.tdy.Sqlite.Constant;
import com.example.plb.tdy.Sqlite.MysqliteHelper;
import com.example.plb.tdy.Sqlite.bean.shopinfos;

import java.util.ArrayList;
import java.util.List;

public class DBManger {
    public static MysqliteHelper helper;

    /**
     * 将 MysqliteHelper 类构建为一个单例的形式 只允许拥有一个实例
     * @param context 上下文
     * @return 数据库对象
     */
    public static MysqliteHelper getMmysqliteHelper(Context context){
        if(helper==null){
            helper = new MysqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句在数据库中执行不同的操作
     * @param db 数据库对象
     * @param sql sql语句
     */
    public static void ExecSQL(SQLiteDatabase db, String sql){
        if(helper!=null){
            if(sql!=null && !"".equals(sql)){
                db.execSQL(sql);
            }
        }
    }


    /**
     * 根据sql语句查询 cursor对象
     * @param db 数据库对象
     * @param sql sql语句
     * @param selectionArgs 查询条件
     * @return 查询结构
     */
    public static Cursor SqlDBselect(SQLiteDatabase db, String sql, String[] selectionArgs){
        //cursor就是根据sql语句返回符合条件的虚拟结果集
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }



    public static List<shopinfos> CursorTolsit(Cursor cursor){
        List<shopinfos> list = new ArrayList<>();

        /**
         * moveToNext 若返回true则表示还有下一条数据 若返回false则代表以到最后一条，读取完毕
         */
        while (cursor.moveToNext()){

            //通过字段名称获取该字段的下标
            int columnindex = cursor.getColumnIndex(Constant.SHOPBUYNUM);
            //通过下标获取该字段的值
            int SHOPBUYNUM = cursor.getInt(columnindex);

            String name = cursor.getString(cursor.getColumnIndex(Constant.NAME));

            String shopimg = cursor.getString(cursor.getColumnIndex(Constant.SHOPIMG));

            int shopprice = cursor.getInt(cursor.getColumnIndex(Constant.SHOPPRICE));

            int ispayment = cursor.getInt(cursor.getColumnIndex(Constant.ISPAYMENT));

            int Deliverystatus = cursor.getInt(cursor.getColumnIndex(Constant.DELIVERYSTATUS));

            int shopid = cursor.getInt(cursor.getColumnIndex("id"));
            shopinfos shopinfos = new shopinfos(name,SHOPBUYNUM,shopprice,shopimg,ispayment,Deliverystatus,shopid);
            list.add(shopinfos);
        }
        return list;
    }



}
