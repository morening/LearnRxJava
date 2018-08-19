package com.morening.learn.learnrxjava.example2;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;


import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example2Fragment extends Fragment {

    private static final String TAG = Example2Fragment.class.getSimpleName();

    @BindView(R.id.example2_result_tv)
    TextView example2_result_tv;

    private PublishSubject<Double> publishSubject = null;
    private CompositeDisposable compositeDisposable = null;
    private SourceHandler sourceHandler = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example2, container, false);
        ButterKnife.bind(this, root);

        compositeDisposable = new CompositeDisposable();
        sourceHandler = new SourceHandler();
        sourceHandler.sendEmptyMessage(0);
        publishSubject = PublishSubject.create();
        startCalc();

        return root;
    }

    private void startCalc() {
        // 原实现
        /*DisposableObserver<List<Double>> disposableObserver = new DisposableObserver<List<Double>>() {
            @Override
            public void onNext(List<Double> doubles) {
                double result = 0;
                if (doubles.size() > 0){
                    for (Double d: doubles){
                        result += d;
                    }
                    result = result / doubles.size();
                }

                Log.d(TAG, "更新平均温度：" + result);
                example2_result_tv.setText(String.format("过去3秒收到了%d个数据，平均温度为：%f", doubles.size(), result));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };*/

        // 使用flatMap操作符转化buffer操作后的List为Data(包括数量和平均值)
        // 并且将转化工作放在计算线程中完成
        DisposableObserver<Data> disposableObserver = new DisposableObserver<Data>() {
            @Override
            public void onNext(Data data) {
                Log.d(TAG, "更新平均温度：" + data.average);
                example2_result_tv.setText(String.format("过去3秒收到了%d个数据，平均温度为：%f", data.size, data.average));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        publishSubject.buffer(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .flatMap(new Function<List<Double>, ObservableSource<Data>>() {
                    @Override
                    public ObservableSource<Data> apply(List<Double> doubles) throws Exception {
                        double result = 0;
                        if (doubles.size() > 0){
                            for (Double d: doubles){
                                result += d;
                            }
                            result = result / doubles.size();
                        }

                        return Observable.just(new Data(doubles.size(), result));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    private void updateTemperature(double temperature){
        Log.d(TAG, "温度测量结果：" + temperature);
        publishSubject.onNext(temperature);
    }

    private class SourceHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            double temperature = Math.random() * 25 + 5;
            updateTemperature(temperature);

            sendEmptyMessageDelayed(0, 250+(long)(250*Math.random()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sourceHandler.removeCallbacksAndMessages(null);
        compositeDisposable.clear();
    }

    private class Data{
        int size = 0;
        double average = 0;

        public Data(int size, double average){
            this.size = size;
            this.average = average;
        }
    }
}
