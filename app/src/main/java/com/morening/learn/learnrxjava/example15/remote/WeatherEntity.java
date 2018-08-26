package com.morening.learn.learnrxjava.example15.remote;

import java.util.List;

/**
 * Created by morening on 2018/8/25.
 */

public class WeatherEntity {

    /**
     * date : 20180825
     * message : Success !
     * status : 200
     * city : 天津
     * count : 9
     * data : {"shidu":"55%","pm25":15,"pm10":37,"quality":"优","wendu":"25","ganmao":"各类人群可自由活动","yesterday":{"date":"24日星期五","sunrise":"05:31","high":"高温 32.0℃","low":"低温 23.0℃","sunset":"18:55","aqi":43,"fx":"北风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},"forecast":[{"date":"25日星期六","sunrise":"05:32","high":"高温 32.0℃","low":"低温 25.0℃","sunset":"18:54","aqi":52,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"26日星期日","sunrise":"05:33","high":"高温 31.0℃","low":"低温 24.0℃","sunset":"18:53","aqi":60,"fx":"南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"27日星期一","sunrise":"05:34","high":"高温 31.0℃","low":"低温 25.0℃","sunset":"18:51","aqi":69,"fx":"东风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"28日星期二","sunrise":"05:34","high":"高温 32.0℃","low":"低温 24.0℃","sunset":"18:50","aqi":77,"fx":"东南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"29日星期三","sunrise":"05:35","high":"高温 31.0℃","low":"低温 24.0℃","sunset":"18:48","aqi":81,"fx":"东北风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]}
     */

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private DataEntity data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
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
        private YesterdayEntity yesterday;
        private List<ForecastEntity> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public double getPm25() {
            return pm25;
        }

        public void setPm25(double pm25) {
            this.pm25 = pm25;
        }

        public double getPm10() {
            return pm10;
        }

        public void setPm10(double pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayEntity getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayEntity yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastEntity> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastEntity> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayEntity {
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

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public double getAqi() {
                return aqi;
            }

            public void setAqi(double aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastEntity {
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

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public double getAqi() {
                return aqi;
            }

            public void setAqi(double aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
