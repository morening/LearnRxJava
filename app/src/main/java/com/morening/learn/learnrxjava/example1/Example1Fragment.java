package com.morening.learn.learnrxjava.example1;


import android.app.Fragment;
import android.os.Bundle;
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
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example1Fragment extends Fragment {

    private static final String TAG = Example1Fragment.class.getSimpleName();

    @BindView(R.id.example1_result_tv)
    TextView example1_result_tv;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public Example1Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_example1, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @OnClick(R.id.example1_start_btn)
    void onClickStartBtn(){
        startDownload();
    }

    private void startDownload() {
        // 原实现
        /*Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int k=0; k<100; k++){
                    if (k % 20 == 0){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException exception) {
                            if (!emitter.isDisposed()){
                                emitter.onError(exception);
                            }
                        }
                        emitter.onNext(k);
                    }
                }

                emitter.onComplete();
            }
        });*/

        // 使用intervalRange 代替for 循环，并通过flatmap 实现每5个单位显示下载结果
        Observable<Integer> observable =
                Observable.intervalRange(0, 100, 0, 100, TimeUnit.MILLISECONDS)
                .flatMap(new Function<Long, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Long aLong) throws Exception {
                        if (aLong % 5 == 0){
                            return Observable.just(aLong.intValue());
                        }
                        return Observable.empty();
                    }
                });

        DisposableObserver<Integer> disposableObserver = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext=" + integer);
                example1_result_tv.setText("Current Progress=" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError=" + e);
                example1_result_tv.setText("Download Error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
                example1_result_tv.setText("Download Complete");
            }
        };

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);

        compositeDisposable.add(disposableObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }
}







