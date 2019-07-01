package com.bawei.dome002;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.dome002.gen.DaoMaster;
import com.bawei.dome002.gen.DaoSession;

/**
 * Author:程金柱
 * Date:2019/6/29 9:38
 * Description：
 */

public class App extends Application {

    private static SharedPreferences my;
    private static DaoSession daoSession;

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static SharedPreferences getMy() {
        return my;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        my = getSharedPreferences("my", MODE_PRIVATE);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "my.db");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();
    }
}
