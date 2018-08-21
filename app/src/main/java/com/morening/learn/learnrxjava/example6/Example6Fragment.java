package com.morening.learn.learnrxjava.example6;


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
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example6Fragment extends Fragment {

    private static final String TAG = Example6Fragment.class.getSimpleName();

    private Unbinder unbinder = null;

    private CompositeDisposable compositeDisposable = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_example6, container, false);
        unbinder = ButterKnife.bind(this, root);

        compositeDisposable = new CompositeDisposable();

        return root;
    }

    @OnClick(R.id.example6_tv)
    void onClickStartBtn(){
        startRetryRequest();
    }

    private void startRetryRequest() {
        Log.d(TAG, "startRetryRequest");
        DisposableObserver<String> disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext s="+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError e="+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
        getObservable("http://a.wrong.url").retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {

            private int retryCnt = 0;

            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {

                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if (retryCnt < 4){
                            Log.d(TAG, "retryWhen retryCnt="+retryCnt);
                            retryCnt++;
                            return Observable.timer(2000, TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(throwable);
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    private Observable<String> getObservable(String url){

        return Observable.error(new Throwable("\""+url+"\""+" not found"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
        unbinder.unbind();
    }
}
