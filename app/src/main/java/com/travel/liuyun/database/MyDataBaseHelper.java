package com.travel.liuyun.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by liuguizhou on 2017/4/7.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = MyDataBaseHelper.class.getSimpleName();

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table stu_table(_id integer primary key,name text varchar(20)," +
                "sex text varchar(10),telephone text,familyAddress text,age int)";
        db.execSQL(sql);
        Log.e(TAG, "Create database......");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "Upgrade database......");
    }
}
