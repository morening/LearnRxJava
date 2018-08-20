package com.morening.learn.learnrxjava.example11;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example11Fragment extends Fragment {

    private static final String TAG = Example11Fragment.class.getSimpleName();

    @BindView(R.id.example11_tv_city)
    TextView example11_tv_city;

    @BindView(R.id.example11_tv_temperature)
    TextView example11_tv_temperature;

    private String[] cityNames = new String[]{"天津", "北京", "上海"};

    private CompositeDisposable locateDisposable = null;
    private CompositeDisposable weatherDisposable = null;
    private ConnectableObservable<String> locateObsevable = null;
    private Disposable disposable = null;
    private boolean isGoodNetwork = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example11, container, false);
        ButterKnife.bind(this, root);

        locateDisposable = new CompositeDisposable();
        weatherDisposable = new CompositeDisposable();
        requestLocate();

        return root;
    }

    private void requestWeather(String city) {
        example11_tv_temperature.setText("等待查询结果。。。");
        weatherDisposable.clear();
        isGoodNetwork = false;

        DisposableObserver<String> weatherObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                example11_tv_temperature.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                example11_tv_temperature.setText(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };

        Observable.just(city).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String city) throws Exception {

                return getWeatherObservable(city);
            }
        }).subscribeOn(Schedulers.io()).retryWhen(new Function<Observable<Throwable>, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(Throwable throwable) throws Exception {
                        Log.d(TAG, "getWeatherObservable retryWhen throwable="+throwable.getMessage());
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(weatherObserver);

        weatherDisposable.add(weatherObserver);
    }

    private Observable<String> getWeatherObservable(String city) {
        Log.d(TAG, "getWeatherObservable city="+city);
        return isGoodNetwork? Observable.just(String.valueOf(Math.random())): Observable.error(new Throwable("余额不足"));
    }

    private void requestLocate() {
        locateObsevable = Observable.intervalRange(0, cityNames.length, 5000, 5000, TimeUnit.MILLISECONDS).repeat()
                .flatMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Long index) throws Exception {

                        return Observable.just(cityNames[index.intValue()]);
                    }
                }).subscribeOn(Schedulers.io()).publish();
        disposable = locateObsevable.connect();

        DisposableObserver<String> locateObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                example11_tv_city.setText(s);

                requestWeather(s);
            }

            @Override
            public void onError(Throwable e) {
                example11_tv_city.setText(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        locateObsevable.observeOn(AndroidSchedulers.mainThread()).subscribe(locateObserver);

        locateDisposable.add(locateObserver);
        locateDisposable.add(disposable);
    }

    @OnClick(R.id.example11_btn_good_network)
    void onClickGoodNetworkBtn(){
        isGoodNetwork = true;
    }

    @OnClick(R.id.example11_btn_bad_network)
    void onClickBadNetworkBtn(){
        isGoodNetwork = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        locateDisposable.clear();
        weatherDisposable.clear();
    }
}
