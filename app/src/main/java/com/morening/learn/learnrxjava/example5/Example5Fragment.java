package com.morening.learn.learnrxjava.example5;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morening.learn.learnrxjava.R;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example5Fragment extends Fragment {

    private static final String TAG = Example5Fragment.class.getSimpleName();

    private CompositeDisposable compositeDisposable = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example5, container, false);
        ButterKnife.bind(this, root);

        compositeDisposable = new CompositeDisposable();

        return root;
    }

    @OnClick(R.id.example5_tv_simple)
    void onClickSimpleBtn(){
        startSimplePolling();
    }

    private void startSimplePolling() {
        Log.d(TAG, "startSimplePolling");
        DisposableObserver simpleObserver = getDisposableObserver();
        Observable.intervalRange(0, 5, 0, 3000, TimeUnit.MILLISECONDS)
                .take(5)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        doWork();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(simpleObserver);

        compositeDisposable.add(simpleObserver);
    }

    @OnClick(R.id.example5_tv_advance)
    void onClickAdvanceBtn(){

        startAdvancePolling();
    }

    private void startAdvancePolling() {
        Log.d(TAG, "startAdvancePolling");
        DisposableObserver<Long> advanceObserver = getDisposableObserver();
        Observable.just(0L).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                doWork();
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<Long>>() {

            private long repeatCnt = 0;

            @Override
            public ObservableSource<Long> apply(Observable<Object> objectObservable) throws Exception {

                return objectObservable.flatMap(new Function<Object, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(Object o) throws Exception {
                        if (++repeatCnt > 4){
                            return Observable.empty();
                        }
                        Log.d(TAG, "startAdvancePolling apply");
                        return Observable.timer(3000+repeatCnt*1000, TimeUnit.MILLISECONDS);
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(advanceObserver);

        compositeDisposable.add(advanceObserver);
    }

    private DisposableObserver<Long> getDisposableObserver(){

        return new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "DisposableObserver onError, threadId="+Thread.currentThread().getId()+", reason="+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "DisposableObserver onComplete, threadId="+Thread.currentThread().getId());
            }
        };
    }

    private void doWork(){
        long workTime = (long)(Math.random()*500)+500;
        try {
            Log.d(TAG, "doWork start, threadId="+Thread.currentThread().getId());
            Thread.sleep(workTime);
            Log.d(TAG, "doWork finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}
