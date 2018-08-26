package com.morening.learn.learnrxjava.example15;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;
import com.morening.learn.learnrxjava.R2;
import com.morening.learn.learnrxjava.example15.db.DataBean;
import com.morening.learn.learnrxjava.example15.db.WeatherBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by morening on 2018/8/25.
 */
public class Example15RecyclerViewAdapter extends RecyclerView.Adapter<Example15RecyclerViewAdapter.VH> implements View.OnClickListener{

    private static final String TAG = Example15RecyclerViewAdapter.class.getSimpleName();

    private List<WeatherBean> weatherBeans = null;

    private OnItemClickListener onItemClickListener = null;

    public Example15RecyclerViewAdapter(List<WeatherBean> weatherBeans) {
        this.weatherBeans = weatherBeans;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example15_recyclerview_item, parent, false);
        view.setOnClickListener(this);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int index) {
        WeatherBean entity = weatherBeans.get(index);
        if (entity == null){
            return;
        }
        vh.example15_item_city.setText(entity.getCity());
        DataBean dataEntity = entity.getData();
        if (dataEntity == null){
            return;
        }
        vh.example15_item_temp.setText(dataEntity.getWendu()+"℃");

        vh.itemView.setTag(entity.getCity());
    }

    @Override
    public int getItemCount() {
        if (weatherBeans == null){
            return 0;
        }
        return weatherBeans.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(v);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    static class VH extends RecyclerView.ViewHolder{

        private Unbinder unbinder = null;

        @BindView(R2.id.example15_item_city)
        TextView example15_item_city;
        @BindView(R2.id.example15_item_temp)
        TextView example15_item_temp;

        public VH(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
