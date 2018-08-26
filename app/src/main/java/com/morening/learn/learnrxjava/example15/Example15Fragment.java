package com.morening.learn.learnrxjava.example15;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.morening.learn.learnrxjava.LearnRxApplication;
import com.morening.learn.learnrxjava.R;
import com.morening.learn.learnrxjava.R2;
import com.morening.learn.learnrxjava.example15.db.DaoSession;
import com.morening.learn.learnrxjava.example15.db.DataBean;
import com.morening.learn.learnrxjava.example15.db.DataBeanDao;
import com.morening.learn.learnrxjava.example15.db.ForecastBean;
import com.morening.learn.learnrxjava.example15.db.WeatherBean;
import com.morening.learn.learnrxjava.example15.db.WeatherBeanDao;
import com.morening.learn.learnrxjava.example15.db.YesterdayBean;
import com.morening.learn.learnrxjava.example15.remote.WeatherApi;
import com.morening.learn.learnrxjava.example15.remote.WeatherEntity;
import com.morening.learn.learnrxjava.example15.util.DBUtils;

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
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example15Fragment extends Fragment implements OnItemClickListener{

    private static final String TAG = Example15Fragment.class.getSimpleName();

    private Unbinder unbinder = null;

    @BindView(R2.id.example15_rv)
    RecyclerView example15_rv;

    @BindView(R2.id.example15_et)
    EditText example15_et;

    @BindView(R2.id.example15_pb)
    ProgressBar example15_pb;

    private List<WeatherBean> weatherBeans = new ArrayList<>();
    private Example15RecyclerViewAdapter adapter = null;
    private WeatherApi weatherApi = null;

    private CompositeDisposable disposable = new CompositeDisposable();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_example15, container, false);
        unbinder = ButterKnife.bind(this, root);

        adapter = new Example15RecyclerViewAdapter(weatherBeans);
        adapter.setOnItemClickListener(this);
        example15_rv.setAdapter(adapter);
        example15_rv.setLayoutManager(new LinearLayoutManager(getContext()));

        weatherApi = new Retrofit.Builder().baseUrl("https://www.sojson.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WeatherApi.class);

        return root;
    }

    @OnClick(R.id.example15_btn)
    void onClick(){
        example15_pb.setVisibility(View.VISIBLE);

        String city = example15_et.getText().toString().replaceAll(" ", "");
        requestWeatherFromRemote(city);
    }

    private void requestWeatherFromRemote(String city) {
        disposable.add(
                getWeatherObservableRemote(city).flatMap(new Function<WeatherEntity, ObservableSource<WeatherEntity>>() {
                    @Override
                    public ObservableSource<WeatherEntity> apply(WeatherEntity weatherEntity) throws Exception {
                        if (weatherEntity.getStatus() != 200){
                            return Observable.error(new Throwable(weatherEntity.getMessage()));
                        }
                        return Observable.just(weatherEntity);
                    }
                }).doOnNext(new Consumer<WeatherEntity>() {
                    @Override
                    public void accept(WeatherEntity weatherEntity) throws Exception {
                        deleteWeatherInfoFromDb(weatherEntity);
                        saveWeatherInfoToDb(weatherEntity);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe());
    }

    private void deleteWeatherInfoFromDb(WeatherEntity weatherEntity){
        DaoSession daoSession = LearnRxApplication.getExample15DaoSession();

        List<WeatherBean> weatherBeans = daoSession.getWeatherBeanDao().queryBuilder().where(WeatherBeanDao.Properties.City.eq(weatherEntity.getCity())).list();
        if (weatherBeans != null && weatherBeans.size() > 0){
            WeatherBean weatherBean = weatherBeans.get(0);
            daoSession.getForecastBeanDao().deleteInTx(weatherBean.getData().getForecast());
            daoSession.getYesterdayBeanDao().delete(weatherBean.getData().getYesterday());
            daoSession.getDataBeanDao().delete(weatherBean.getData());
            daoSession.getWeatherBeanDao().delete(weatherBean);
        }
    }

    private void saveWeatherInfoToDb(WeatherEntity weatherEntity) {
        disposable.add(Observable.just(weatherEntity).flatMap(new Function<WeatherEntity, ObservableSource<WeatherBean>>() {
            @Override
            public ObservableSource<WeatherBean> apply(WeatherEntity entity) throws Exception {

                return getWeatherBeanObservable(entity);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(getDisposableObserver()));
    }

    private Observable<WeatherBean> getWeatherBeanObservable(WeatherEntity weatherEntity){
        DaoSession daoSession = LearnRxApplication.getExample15DaoSession();

        YesterdayBean yesterdayBean = DBUtils.yesterdayEntity2Bean(weatherEntity.getData().getYesterday());
        long yesterdayId = daoSession.getYesterdayBeanDao().insert(yesterdayBean);

        DataBean dataBean = DBUtils.dataEntity2Bean(weatherEntity.getData());
        dataBean.setFk_yesterdayId(yesterdayId);
        long dataId = daoSession.getDataBeanDao().insert(dataBean);

        List<WeatherEntity.DataEntity.ForecastEntity> forecastEntities = weatherEntity.getData().getForecast();
        for (WeatherEntity.DataEntity.ForecastEntity forecastEntity: forecastEntities){
            ForecastBean forecastBean = DBUtils.forecastEntity2Bean(forecastEntity);
            forecastBean.setRef_dataId(dataId);
            daoSession.getForecastBeanDao().insert(forecastBean);
        }

        WeatherBean weatherBean = DBUtils.weatherEntity2Bean(weatherEntity);
        weatherBean.setFk_dataId(dataId);
        daoSession.getWeatherBeanDao().insert(weatherBean);

        return Observable.just(weatherBean);
    }

    private DisposableObserver<WeatherBean> getDisposableObserver(){
        return new DisposableObserver<WeatherBean>() {
            @Override
            public void onNext(WeatherBean bean) {
                example15_et.setText("");
                if (!weatherBeans.contains(bean)){
                    weatherBeans.add(bean);
                    adapter.notifyDataSetChanged();
                    example15_rv.scrollToPosition(adapter.getItemCount()-1);
                    example15_pb.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private Observable<WeatherEntity> getWeatherObservableRemote(String city){
        return weatherApi.getWeather(city);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        disposable.clear();
    }

    @Override
    public void onItemClick(View view) {
        String city = (String) view.getTag();
        Intent intent = new Intent(getActivity(), Example15DetailActivity.class);
        intent.putExtra(Constants.EXTRA_STRING_CITY, city);
        startActivity(intent);
    }

}
