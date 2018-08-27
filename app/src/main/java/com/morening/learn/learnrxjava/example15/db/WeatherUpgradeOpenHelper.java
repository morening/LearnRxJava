package com.morening.learn.learnrxjava.example15.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * Created by morening on 2018/8/27.
 */
public class WeatherUpgradeOpenHelper extends DaoMaster.OpenHelper {
    public WeatherUpgradeOpenHelper(Context context, String name) {
        super(context, name);
    }

    public WeatherUpgradeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        switch (oldVersion){
            case 1:
            case 2:
            case 3:
                // db.execSQL("ALTER TABLE xx ADD COLUMN xx TEXT");
        }
    }
}
