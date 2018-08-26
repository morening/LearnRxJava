package com.morening.learn.learnrxjava.example15.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by morening on 2018/8/25.
 */
@Entity
public class YesterdayBean {

    @Id(autoincrement = true)
    private Long id;

    /**
     * date : 24日星期五
     * sunrise : 05:31
     * high : 高温 32.0℃
     * low : 低温 23.0℃
     * sunset : 18:55
     * aqi : 43.0
     * fx : 北风
     * fl : 3-4级
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
    @Generated(hash = 173801037)
    public YesterdayBean(Long id, String date, String sunrise, String high,
            String low, String sunset, double aqi, String fx, String fl,
            String type, String notice) {
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
    }
    @Generated(hash = 1719795218)
    public YesterdayBean() {
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

}
