package com.morening.learn.learnrxjava.example15.util;

import com.morening.learn.learnrxjava.example15.db.DataBean;
import com.morening.learn.learnrxjava.example15.db.ForecastBean;
import com.morening.learn.learnrxjava.example15.db.WeatherBean;
import com.morening.learn.learnrxjava.example15.db.YesterdayBean;
import com.morening.learn.learnrxjava.example15.remote.WeatherEntity;

import java.util.List;

/**
 * Created by morening on 2018/8/26.
 */
public class DBUtils {

    public static WeatherBean weatherEntity2Bean(WeatherEntity entity){
        WeatherBean bean = new WeatherBean();
        bean.setId(null);
        bean.setCity(entity.getCity());
        bean.setDate(entity.getDate());

        return bean;
    }

    public static DataBean dataEntity2Bean(WeatherEntity.DataEntity entity){
        DataBean bean = new DataBean();
        bean.setId(null);
        bean.setGanmao(entity.getGanmao());
        bean.setPm10(entity.getPm10());
        bean.setPm25(entity.getPm25());
        bean.setQuality(entity.getQuality());
        bean.setShidu(entity.getShidu());
        bean.setWendu(entity.getWendu());

        return bean;
    }

    public static YesterdayBean yesterdayEntity2Bean(WeatherEntity.DataEntity.YesterdayEntity entity){
        YesterdayBean bean = new YesterdayBean();
        bean.setId(null);
        bean.setAqi(entity.getAqi());
        bean.setDate(entity.getDate());
        bean.setFl(entity.getFl());
        bean.setFx(entity.getFx());
        bean.setHigh(entity.getHigh());
        bean.setLow(entity.getLow());
        bean.setNotice(entity.getNotice());
        bean.setSunrise(entity.getSunrise());
        bean.setSunset(entity.getSunset());
        bean.setType(entity.getType());

        return bean;
    }

    public static ForecastBean forecastEntity2Bean(WeatherEntity.DataEntity.ForecastEntity entity){
        ForecastBean bean = new ForecastBean();
        bean.setId(null);
        bean.setAqi(entity.getAqi());
        bean.setDate(entity.getDate());
        bean.setFl(entity.getFl());
        bean.setFx(entity.getFx());
        bean.setHigh(entity.getHigh());
        bean.setLow(entity.getLow());
        bean.setNotice(entity.getNotice());
        bean.setSunrise(entity.getSunrise());
        bean.setSunset(entity.getSunset());
        bean.setType(entity.getType());

        return bean;
    }
}
















