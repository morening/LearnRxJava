package com.morening.learn.learnrxjava.example15.db;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by morening on 2018/8/25.
 */
@Entity
public class WeatherBean {

    @Id(autoincrement = true)
    private Long id;

    /**
     * date : 20180825
     * message : Success !
     * status : 200
     * city : 天津
     * count : 9
     * data : {"shidu":"55%","pm25":15,"pm10":37,"quality":"优","wendu":"25","ganmao":"各类人群可自由活动","yesterday":{"date":"24日星期五","sunrise":"05:31","high":"高温 32.0℃","low":"低温 23.0℃","sunset":"18:55","aqi":43,"fx":"北风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},"forecast":[{"date":"25日星期六","sunrise":"05:32","high":"高温 32.0℃","low":"低温 25.0℃","sunset":"18:54","aqi":52,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"26日星期日","sunrise":"05:33","high":"高温 31.0℃","low":"低温 24.0℃","sunset":"18:53","aqi":60,"fx":"南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"27日星期一","sunrise":"05:34","high":"高温 31.0℃","low":"低温 25.0℃","sunset":"18:51","aqi":69,"fx":"东风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"28日星期二","sunrise":"05:34","high":"高温 32.0℃","low":"低温 24.0℃","sunset":"18:50","aqi":77,"fx":"东南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"29日星期三","sunrise":"05:35","high":"高温 31.0℃","low":"低温 24.0℃","sunset":"18:48","aqi":81,"fx":"东北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]}
     */

    private String date;
    @Unique
    private String city;

    private Long fk_dataId;

    @ToOne(joinProperty = "fk_dataId")
    private DataBean data;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2021789550)
    private transient WeatherBeanDao myDao;

    @Generated(hash = 564458940)
    public WeatherBean(Long id, String date, String city, Long fk_dataId) {
        this.id = id;
        this.date = date;
        this.city = city;
        this.fk_dataId = fk_dataId;
    }

    @Generated(hash = 2015408157)
    public WeatherBean() {
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

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getFk_dataId() {
        return this.fk_dataId;
    }

    public void setFk_dataId(Long fk_dataId) {
        this.fk_dataId = fk_dataId;
    }

    @Generated(hash = 998359)
    private transient Long data__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 958132096)
    public DataBean getData() {
        Long __key = this.fk_dataId;
        if (data__resolvedKey == null || !data__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DataBeanDao targetDao = daoSession.getDataBeanDao();
            DataBean dataNew = targetDao.load(__key);
            synchronized (this) {
                data = dataNew;
                data__resolvedKey = __key;
            }
        }
        return data;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 913253551)
    public void setData(DataBean data) {
        synchronized (this) {
            this.data = data;
            fk_dataId = data == null ? null : data.getId();
            data__resolvedKey = fk_dataId;
        }
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
    @Generated(hash = 1819107742)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWeatherBeanDao() : null;
    }

    @Override
    public boolean equals(Object obj) {
        WeatherBean other = (WeatherBean)obj;
        return this.city.equals(other.city);
    }

}
