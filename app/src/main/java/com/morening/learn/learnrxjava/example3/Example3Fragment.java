package com.morening.learn.learnrxjava.example3;


import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example3Fragment extends Fragment {

    private static final String TAG = Example3Fragment.class.getSimpleName();

    @BindView(R.id.example3_tv)
    TextView example3_tv;

    private PublishSubject<String> publishSubject = null;
    private CompositeDisposable compositeDisposable = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example3, container, false);
        ButterKnife.bind(this, root);

        publishSubject = publishSubject.create();
        compositeDisposable = new CompositeDisposable();

        DisposableObserver<String> disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                example3_tv.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        publishSubject.debounce(200, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() > 0;
                    }
                })
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return getSearchObservable(s);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);

        return root;
    }

    private Observable<String> getSearchObservable(String s){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "开始请求，关键词为：" + s);
                try {
                    Thread.sleep(100 + (long)(Math.random() * 500));
                } catch (InterruptedException exception) {
                    if (!emitter.isDisposed()){
                        emitter.onError(exception);
                    }
                }
                Log.d(TAG, "结束请求，关键词为：" + s);
                emitter.onNext("完成请求，关键词为：" + s);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @OnTextChanged(R.id.example3_et)
    void afterTextChanged(Editable s){
        startSearch(s.toString());
    }

    void startSearch(String key){
        publishSubject.onNext(key);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}
