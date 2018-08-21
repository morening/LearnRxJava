package com.morening.learn.learnrxjava.example8;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morening.learn.learnrxjava.R;
import com.morening.learn.learnrxjava.example4.NewsAdapter;
import com.morening.learn.learnrxjava.example4.NewsResultEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example8Fragment extends Fragment {

    private static final String TAG = Example8Fragment.class.getSimpleName();

    CompositeDisposable compositeDisposable = null;

    private NewsAdapter newsAdapter;
    private List<NewsResultEntity> newsResultEntities = new ArrayList<>();

    @BindView(R.id.example8_rv)
    RecyclerView example8_rv;

    private Unbinder unbinder = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example8, container, false);
        unbinder = ButterKnife.bind(this, root);

        compositeDisposable = new CompositeDisposable();

        newsAdapter = new NewsAdapter(newsResultEntities);
        example8_rv.setAdapter(newsAdapter);
        example8_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    @OnClick(R.id.example8_btn_concat)
    void onClickConcat(){
        Observable<List<NewsResultEntity>> observable
                = Observable.concat(getCacheNews(500).subscribeOn(Schedulers.io()),
                getNetworkNews(2000).subscribeOn(Schedulers.io()));
        DisposableObserver disposableObserver = getNewsObserver();
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    @OnClick(R.id.example8_btn_concateager)
    void onClickConcatEager(){
        List<Observable<List<NewsResultEntity>>> observables = new ArrayList<>();
        observables.add(getCacheNews(500).subscribeOn(Schedulers.io()));
        observables.add(getNetworkNews(2000).subscribeOn(Schedulers.io()));
        Observable<List<NewsResultEntity>> observable
                = Observable.concatEager(observables);
        DisposableObserver disposableObserver = getNewsObserver();
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    @OnClick(R.id.example8_btn_merge)
    void onClickConcatMerge(){
        DisposableObserver disposableObserver = getNewsObserver();
        Observable.merge(
                getCacheNews(500).subscribeOn(Schedulers.io()),
                getNetworkNews(2000).subscribeOn(Schedulers.io())
        ).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    @OnClick(R.id.example8_btn_publish)
    void onClickConcatPublish(){
        DisposableObserver disposableObserver = getNewsObserver();
        getNetworkNews(2000).subscribeOn(Schedulers.io())
                .publish(new Function<Observable<List<NewsResultEntity>>, ObservableSource<List<NewsResultEntity>>>() {
                    @Override
                    public ObservableSource<List<NewsResultEntity>> apply(Observable<List<NewsResultEntity>> network) throws Exception {
                        return Observable.merge(network, getCacheNews(500).subscribeOn(Schedulers.io()).takeUntil(network));
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    private Observable<List<NewsResultEntity>> getCacheNews(long simulateTime){
        return Observable.create(new ObservableOnSubscribe<List<NewsResultEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsResultEntity>> emitter) throws Exception {
                try {
                    Log.d(TAG, "开始加载缓存数据");
                    Thread.sleep(simulateTime);
                    List<NewsResultEntity> results = new ArrayList<>();
                    for (int k=0; k<10; k++){
                        NewsResultEntity entity = new NewsResultEntity();
                        entity.setType("缓存");
                        entity.setDesc("序号="+k);
                        results.add(entity);
                    }
                    emitter.onNext(results);
                    emitter.onComplete();
                    Log.d(TAG, "结束加载缓存数据");
                } catch (InterruptedException e) {
                    if (!emitter.isDisposed()){
                        emitter.onError(e);
                    }
                }

            }
        });
    }

    private Observable<List<NewsResultEntity>> getNetworkNews(long simulateTime){
        return Observable.create(new ObservableOnSubscribe<List<NewsResultEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsResultEntity>> emitter) throws Exception {
                try {
                    Log.d(TAG, "开始加载网络数据");
                    Thread.sleep(simulateTime);
                    List<NewsResultEntity> results = new ArrayList<>();
                    for (int k=0; k<10; k++){
                        NewsResultEntity entity = new NewsResultEntity();
                        entity.setType("网络");
                        entity.setDesc("序号="+k);
                        results.add(entity);
                    }
                    emitter.onNext(results);
                    emitter.onComplete();
                    Log.d(TAG, "结束加载网络数据");
                } catch (InterruptedException e){
                    if (!emitter.isDisposed()){
                        emitter.onError(e);
                    }
                }
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<NewsResultEntity>>>() {
            @Override
            public ObservableSource<? extends List<NewsResultEntity>> apply(Throwable throwable) throws Exception {
                Log.d(TAG, "网络请求发生错误 throwable="+throwable);
                return Observable.never();
            }
        });
    }

    private DisposableObserver<List<NewsResultEntity>> getNewsObserver(){
        return new DisposableObserver<List<NewsResultEntity>>() {
            @Override
            public void onNext(List<NewsResultEntity> entities) {
                newsResultEntities.clear();
                newsResultEntities.addAll(entities);
                newsAdapter.notifyDataSetChanged();
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
        unbinder.unbind();
    }
}
