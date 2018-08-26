package com.morening.learn.learnrxjava.example15;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.morening.learn.learnrxjava.LearnRxApplication;
import com.morening.learn.learnrxjava.R;
import com.morening.learn.learnrxjava.R2;
import com.morening.learn.learnrxjava.example15.db.DaoSession;
import com.morening.learn.learnrxjava.example15.db.DataBean;
import com.morening.learn.learnrxjava.example15.db.ForecastBean;
import com.morening.learn.learnrxjava.example15.db.WeatherBean;
import com.morening.learn.learnrxjava.example15.db.WeatherBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example15DetailFragment extends Fragment {

    private static final String TAG = Example15DetailFragment.class.getSimpleName();

    private Unbinder unbinder = null;

    @BindView(R2.id.example15_detail_city)
    TextView example15_detail_city;

    @BindView(R2.id.example15_detail_temp)
    TextView example15_detail_temp;

    @BindView(R2.id.example15_detail_quality)
    TextView example15_detail_quality;

    @BindView(R2.id.example15_detail_fx)
    TextView example15_detail_fx;

    @BindView(R2.id.example15_detail_fl)
    TextView example15_detail_fl;

    @BindView(R2.id.example15_detail_high)
    TextView example15_detail_high;

    @BindView(R2.id.example15_detail_low)
    TextView example15_detail_low;

    @BindView(R2.id.example15_detail_pm25)
    TextView example15_detail_pm25;

    @BindView(R2.id.example15_detail_pm10)
    TextView example15_detail_pm10;

    @BindView(R2.id.example15_detail_date)
    TextView example15_detail_date;

    @BindView(R2.id.example15_detail_notice)
    TextView example15_detail_notice;

    @BindView(R2.id.example15_detail_vp)
    ViewPager example15_detail_vp;

    @BindView(R2.id.example15_detail_pb)
    ProgressBar example15_detail_pb;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_example15_detail, container, false);
        unbinder = ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        if (bundle != null) {
            String city = bundle.getString(Constants.EXTRA_STRING_CITY);
            requestWeatherFromLocal(city);
        }
    }

    private void requestWeatherFromLocal(String city) {
        compositeDisposable.add(getWeatherObservableLocal(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getWeatherDisposableObserver()));
    }

    private DisposableObserver<WeatherBean> getWeatherDisposableObserver() {
        return new DisposableObserver<WeatherBean>() {
            @Override
            public void onNext(WeatherBean weatherBean) {
                updateWeatherInfo(weatherBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                example15_detail_pb.setVisibility(View.GONE);
            }
        };
    }

    private void updateWeatherInfo(WeatherBean weatherBean) {
        updateTodayWeather(weatherBean);
        updateForecastWeather(weatherBean);
    }

    private void updateTodayWeather(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }
        example15_detail_city.setText(weatherBean.getCity());

        DataBean dataBean = weatherBean.getData();
        if (dataBean == null) {
            return;
        }
        example15_detail_temp.setText(dataBean.getWendu() + "℃");
        example15_detail_quality.setText(dataBean.getQuality());
        example15_detail_pm25.setText("PM2.5 " + dataBean.getPm25());
        example15_detail_pm10.setText("PM10 " + dataBean.getPm10());

        List<ForecastBean> forecastBeans = dataBean.getForecast();
        if (forecastBeans == null || forecastBeans.size() <= 0) {
            return;
        }
        example15_detail_fx.setText(forecastBeans.get(0).getFx());
        example15_detail_fl.setText(forecastBeans.get(0).getFl());
        example15_detail_high.setText(forecastBeans.get(0).getHigh());
        example15_detail_low.setText(forecastBeans.get(0).getLow());
        example15_detail_date.setText(forecastBeans.get(0).getDate());
        example15_detail_notice.setText(forecastBeans.get(0).getNotice());
    }

    private void updateForecastWeather(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }
        DataBean dataBean = weatherBean.getData();
        if (dataBean == null) {
            return;
        }
        List<ForecastBean> forecastBeans = dataBean.getForecast();
        if (forecastBeans == null || forecastBeans.size() <= 1){
            return;
        }
        Example15ViewPagerAdapter adapter = new Example15ViewPagerAdapter(getActivity(), forecastBeans.subList(1, forecastBeans.size()));
        example15_detail_vp.setAdapter(adapter);
        example15_detail_vp.setPageTransformer(true, new ZoomOutSlideTransformer());
    }

    private Observable<WeatherBean> getWeatherObservableLocal(String city) {
        DaoSession daoSession = LearnRxApplication.getExample15DaoSession();

        WeatherBeanDao weatherBeanDao = daoSession.getWeatherBeanDao();
        List<WeatherBean> weatherBeans = weatherBeanDao.queryBuilder().where(WeatherBeanDao.Properties.City.eq(city)).list();

        return Observable.just(weatherBeans.get(0));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        compositeDisposable.clear();
    }
}
