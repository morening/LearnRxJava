package com.morening.learn.learnrxjava.example4;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by morening on 2018/8/18.
 */
public interface NewsApi {

    @GET("api/data/{category}/{count}/{page}")
    Observable<NewsEntity> getNews(@Path("category") String category,
                                   @Path("count") int count,
                                   @Path("page") int page);
}
