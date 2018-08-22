package com.morening.learn.learnrxjava.example13.rx;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by morening on 2018/8/22.
 */
public final class RxConnector {

    private static final RxConnector INSTANCE = new RxConnector();

    private RxConnector(){

    }

    public static RxConnector getInstance(){
        return INSTANCE;
    }

    private Map<String, Mapper> mappers = new HashMap<>();

    public <T extends Object> RxConnector add(String key, Observable<T> observable){
        if (TextUtils.isEmpty(key) || observable == null){
            return this;
        }
        Mapper mapper = mappers.get(key);
        if (mapper == null){
            mapper = new Mapper(key);
            mappers.put(key, mapper);
        }
        mapper.observable = observable;

        return this;
    }

    public <T extends Object> RxConnector add(String key, DisposableObserver<T> observer){
        if (TextUtils.isEmpty(key) || observer == null){
            return this;
        }
        Mapper mapper = mappers.get(key);
        if (mapper == null){
            mapper = new Mapper(key);
            mappers.put(key, mapper);
        }
        mapper.observer = observer;

        return this;
    }

    public void engage(String key){
        if (TextUtils.isEmpty(key)){
            return;
        }
        Mapper mapper = mappers.get(key);
        if (mapper != null && mapper.observable != null && mapper.observer != null && !mapper.engaged){
            mapper.observable.subscribe(mapper.observer);
        }
    }

    public void engageAll(){
        for (String key: mappers.keySet()){
            engage(key);
        }
    }

    public void dispose(String key){
        if (TextUtils.isEmpty(key)){
            return;
        }
        Mapper mapper = mappers.get(key);
        if (mapper == null){
            return;
        }
        mappers.remove(key);
        mapper.observable = null;
        mapper.observer.dispose();
        mapper.observer = null;
        mapper = null;
    }

    private class Mapper<T extends Object>{
        boolean engaged = false;
        String key = null;
        Observable<T> observable = null;
        DisposableObserver<T> observer = null;

        public Mapper(String key){
            this.key = key;
        }
    }
}
