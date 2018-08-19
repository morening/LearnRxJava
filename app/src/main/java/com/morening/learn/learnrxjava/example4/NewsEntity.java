package com.morening.learn.learnrxjava.example4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morening on 2018/8/18.
 */
public class NewsEntity {

    private boolean error;
    private List<NewsResultEntity> results = new ArrayList<>();

    public boolean isError(){
        return error;
    }

    public void setError(boolean error){
        this.error = error;
    }

    public List<NewsResultEntity> getResults(){
        return results;
    }

    public void setResults(List<NewsResultEntity> results){
        this.results = results;
    }
}
