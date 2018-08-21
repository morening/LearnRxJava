package com.morening.learn.learnrxjava.example4;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.morening.learn.learnrxjava.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example4Fragment extends Fragment {

    private static final String TAG = Example4Fragment.class.getSimpleName();

    @BindView(R.id.example4_rv)
    RecyclerView example4_rv;

    private Unbinder unbinder = null;

    private int currentPage = 0;
    private NewsAdapter newsAdapter;
    private List<NewsResultEntity> newsResultEntities = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example4, container, false);
        unbinder = ButterKnife.bind(this, root);

        initViews();
        
        return root;
    }

    private void initViews() {
        example4_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsAdapter = new NewsAdapter(newsResultEntities);
        example4_rv.setAdapter(newsAdapter);
        startRefresh(++currentPage);
    }

    @OnClick(R.id.example4_tv)
    void onClickRefreshBtn(){
        startRefresh(++currentPage);
    }

    private void startRefresh(int currentPage) {
        DisposableObserver<List<NewsResultEntity>> disposableObserver = new DisposableObserver<List<NewsResultEntity>>() {
            @Override
            public void onNext(List<NewsResultEntity> results) {
                newsResultEntities.clear();
                newsResultEntities.addAll(results);
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable.just(currentPage)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<List<NewsResultEntity>>>() {
                    @Override
                    public ObservableSource<List<NewsResultEntity>> apply(Integer page) throws Exception {

                        Observable<NewsEntity> androidNew = getObservable("Android", page);
                        Observable<NewsEntity> iosNew = getObservable("iOS", page);

                        return Observable.zip(androidNew, iosNew, new BiFunction<NewsEntity, NewsEntity, List<NewsResultEntity>>() {
                            @Override
                            public List<NewsResultEntity> apply(NewsEntity androidNew, NewsEntity iosNew) throws Exception {
                                List<NewsResultEntity> results = new ArrayList<>();
                                results.addAll(androidNew.getResults());
                                results.addAll(iosNew.getResults());
                                return results;
                            }
                        });
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);

        compositeDisposable.add(disposableObserver);
    }

    private Observable<NewsEntity> getObservable(String category, int page) {
        NewsApi api = new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(NewsApi.class);
        return api.getNews(category, 10, page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
        unbinder.unbind();
    }
}
