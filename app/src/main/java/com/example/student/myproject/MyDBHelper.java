package com.example.student.myproject;

import android.content.Context;
import android.content.DialogInterface;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mio on 2018/1/14.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String database="mydata.db";//書上有要加private static final
    private static final int version=1;//書上有要加private static final

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    public MyDBHelper(Context context) {//這個建構式是幹嘛的?
        this(context, database, null, version);//必須+private static final不然database、version不能用
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//新增的資料庫(括號裡片是新增一個名稱為sqLiteDatabase的SQLiteDatabase物件
        sqLiteDatabase.execSQL("create table act_list(_id integer primary key autoincrement,"+
                "act_name text not null,"+
                "limted integer not null,"+
                "act_S_D text not null,"+
                "act_E_D text not null,"+
                "F_S_D text not null,"+
                "F_E_D text not null,"+
                "ratio integer not null,"+
                "memo text )");//execSQL是執行SQL語法
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//資料庫版本更新時作用
        sqLiteDatabase.execSQL("drop table if exists act_list");//假設act_list存在就刪掉它
        onCreate(sqLiteDatabase);//重新執行新增資料庫

    }
}
