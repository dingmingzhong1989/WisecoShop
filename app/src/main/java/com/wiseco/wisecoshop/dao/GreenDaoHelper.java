package com.wiseco.wisecoshop.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wiseco.wisecoshop.greendao.DaoMaster;
import com.wiseco.wisecoshop.greendao.DaoSession;


/**
 * Created by apple on 2017/7/4.
 * 封装Greendao数据库
 */

public class GreenDaoHelper {

    Context context;
    DaoMaster.DevOpenHelper helper;
    SQLiteDatabase db;
    DaoMaster daoMaster;
    DaoSession daoSession;


    public GreenDaoHelper(Context context) {
        this.context = context;
    }

    public DaoSession initDao(){
        helper = new DaoMaster.DevOpenHelper(context, "recluse-db", null);
        db= helper.getWritableDatabase();
        daoMaster= new DaoMaster(db);
        daoSession= daoMaster.newSession();
        return daoSession;
    }
}
