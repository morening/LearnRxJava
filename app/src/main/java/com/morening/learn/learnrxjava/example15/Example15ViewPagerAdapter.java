package com.morening.learn.learnrxjava.example15;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;
import com.morening.learn.learnrxjava.R2;
import com.morening.learn.learnrxjava.example15.db.ForecastBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by morening on 2018/8/26.
 */
public class Example15ViewPagerAdapter extends PagerAdapter {

    Unbinder unbinder = null;

    @BindView(R2.id.example15_detail_vp_date)
    TextView example15_detail_vp_date;

    @BindView(R2.id.example15_detail_vp_type)
    TextView example15_detail_vp_type;

    @BindView(R2.id.example15_detail_vp_high)
    TextView example15_detail_vp_high;

    @BindView(R2.id.example15_detail_vp_low)
    TextView example15_detail_vp_low;

    private Context context = null;
    private List<ForecastBean> forecastBeans = null;

    public Example15ViewPagerAdapter(Context context, List<ForecastBean> forecastBeans) {
        this.context = context;
        this.forecastBeans = forecastBeans;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.example15_viewpager_item, null, false);
        unbinder = ButterKnife.bind(this, view);

        ForecastBean forecastBean = forecastBeans.get(position);
        example15_detail_vp_date.setText(forecastBean.getDate());
        example15_detail_vp_type.setText(forecastBean.getType());
        example15_detail_vp_high.setText(forecastBean.getHigh());
        example15_detail_vp_low.setText(forecastBean.getLow());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
//        unbinder.unbind();
    }

    @Override
    public int getCount() {
        if (forecastBeans == null){
            return 0;
        }
        return forecastBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
