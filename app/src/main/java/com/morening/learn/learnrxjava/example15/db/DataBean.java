package com.morening.learn.learnrxjava.example15.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by morening on 2018/8/25.
 */
@Entity
public class DataBean {

    @Id(autoincrement = true)
    private Long id;

    /**
     * shidu : 55%
     * pm25 : 15.0
     * pm10 : 37.0
     * quality : 优
     * wendu : 25
     * ganmao : 各类人群可自由活动
     * yesterday : {"date":"24日星期五","sunrise":"05:31","high":"高温 32.0℃","low":"低温 23.0℃","sunset":"18:55","aqi":43,"fx":"北风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}
     * forecast : [{"date":"25日星期六","sunrise":"05:32","high":"高温 32.0℃","low":"低温 25.0℃","sunset":"18:54","aqi":52,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"26日星期日","sunrise":"05:33","high":"高温 31.0℃","low":"低温 24.0℃","sunset":"18:53","aqi":60,"fx":"南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"27日星期一","sunrise":"05:34","high":"高温 31.0℃","low":"低温 25.0℃","sunset":"18:51","aqi":69,"fx":"东风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"28日星期二","sunrise":"05:34","high":"高温 32.0℃","low":"低温 24.0℃","sunset":"18:50","aqi":77,"fx":"东南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"29日星期三","sunrise":"05:35","high":"高温 31.0℃","low":"低温 24.0℃","sunset":"18:48","aqi":81,"fx":"东北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]
     */

    private String shidu;
    private double pm25;
    private double pm10;
    private String quality;
    private String wendu;
    private String ganmao;

    private Long fk_yesterdayId;

    @ToOne(joinProperty = "fk_yesterdayId")
    private YesterdayBean yesterday;

    @ToMany(referencedJoinProperty = "ref_dataId")
    private List<ForecastBean> forecast;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 532982881)
    private transient DataBeanDao myDao;

    @Generated(hash = 2129597183)
    public DataBean(Long id, String shidu, double pm25, double pm10, String quality, String wendu, String ganmao, Long fk_yesterdayId) {
        this.id = id;
        this.shidu = shidu;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.quality = quality;
        this.wendu = wendu;
        this.ganmao = ganmao;
        this.fk_yesterdayId = fk_yesterdayId;
    }

    @Generated(hash = 908697775)
    public DataBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShidu() {
        return this.shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public double getPm25() {
        return this.pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return this.pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return this.wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return this.ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public Long getFk_yesterdayId() {
        return this.fk_yesterdayId;
    }

    public void setFk_yesterdayId(Long fk_yesterdayId) {
        this.fk_yesterdayId = fk_yesterdayId;
    }

    @Generated(hash = 1813175523)
    private transient Long yesterday__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1503849911)
    public YesterdayBean getYesterday() {
        Long __key = this.fk_yesterdayId;
        if (yesterday__resolvedKey == null || !yesterday__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            YesterdayBeanDao targetDao = daoSession.getYesterdayBeanDao();
            YesterdayBean yesterdayNew = targetDao.load(__key);
            synchronized (this) {
                yesterday = yesterdayNew;
                yesterday__resolvedKey = __key;
            }
        }
        return yesterday;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2102830174)
    public void setYesterday(YesterdayBean yesterday) {
        synchronized (this) {
            this.yesterday = yesterday;
            fk_yesterdayId = yesterday == null ? null : yesterday.getId();
            yesterday__resolvedKey = fk_yesterdayId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1137643910)
    public List<ForecastBean> getForecast() {
        if (forecast == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ForecastBeanDao targetDao = daoSession.getForecastBeanDao();
            List<ForecastBean> forecastNew = targetDao._queryDataBean_Forecast(id);
            synchronized (this) {
                if (forecast == null) {
                    forecast = forecastNew;
                }
            }
        }
        return forecast;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 74421150)
    public synchronized void resetForecast() {
        forecast = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 443544070)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDataBeanDao() : null;
    }

}
