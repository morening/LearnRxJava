package com.morening.learn.learnrxjava.example15.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by morening on 2018/8/25.
 */
public interface WeatherApi {

    @GET("open/api/weather/json.shtml")
    Observable<WeatherEntity> getWeather(@Query("city") String city);
}
