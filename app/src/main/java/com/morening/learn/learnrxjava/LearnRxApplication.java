package com.morening.learn.learnrxjava;

import android.app.Application;

import com.morening.learn.learnrxjava.example15.db.DaoMaster;
import com.morening.learn.learnrxjava.example15.db.DaoSession;
import com.morening.learn.learnrxjava.example15.db.WeatherUpgradeOpenHelper;

import org.greenrobot.greendao.database.Database;

/**
 * Created by morening on 2018/8/26.
 */
public class LearnRxApplication extends Application {

    private static DaoSession example15DaoSession = null;

    @Override
    public void onCreate() {
        super.onCreate();

        setupExample15DataBase();
    }

    private void setupExample15DataBase() {

        WeatherUpgradeOpenHelper openHelper = new WeatherUpgradeOpenHelper(this, "example15-db");
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        example15DaoSession = daoMaster.newSession();
    }

    public static DaoSession getExample15DaoSession(){
        return example15DaoSession;
    }
}
