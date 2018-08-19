package com.morening.learn.learnrxjava.example10;

import io.reactivex.observables.ConnectableObservable;

/**
 * Created by morening on 2018/8/19.
 */
public interface IHolder {
    void onWorkerPrepared(ConnectableObservable<Long> workerFlow);
}
