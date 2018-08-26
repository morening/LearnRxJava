package com.morening.learn.learnrxjava.example15.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by morening on 2018/8/25.
 */
@Entity
public class ForecastBean {

    @Id(autoincrement = true)
    private Long id;

    /**
     * date : 25日星期六
     * sunrise : 05:32
     * high : 高温 32.0℃
     * low : 低温 25.0℃
     * sunset : 18:54
     * aqi : 52.0
     * fx : 南风
     * fl : <3级
     * type : 晴
     * notice : 愿你拥有比阳光明媚的心情
     */

    private String date;
    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private double aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;

    private Long ref_dataId;

    @Generated(hash = 165207018)
    public ForecastBean(Long id, String date, String sunrise, String high,
            String low, String sunset, double aqi, String fx, String fl,
            String type, String notice, Long ref_dataId) {
        this.id = id;
        this.date = date;
        this.sunrise = sunrise;
        this.high = high;
        this.low = low;
        this.sunset = sunset;
        this.aqi = aqi;
        this.fx = fx;
        this.fl = fl;
        this.type = type;
        this.notice = notice;
        this.ref_dataId = ref_dataId;
    }

    @Generated(hash = 1841848901)
    public ForecastBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getHigh() {
        return this.high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return this.low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getSunset() {
        return this.sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public double getAqi() {
        return this.aqi;
    }

    public void setAqi(double aqi) {
        this.aqi = aqi;
    }

    public String getFx() {
        return this.fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return this.fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotice() {
        return this.notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Long getRef_dataId() {
        return this.ref_dataId;
    }

    public void setRef_dataId(Long ref_dataId) {
        this.ref_dataId = ref_dataId;
    }

}
