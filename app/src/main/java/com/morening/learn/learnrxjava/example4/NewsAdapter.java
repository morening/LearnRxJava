package com.morening.learn.learnrxjava.example4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;

import java.util.List;

/**
 * Created by morening on 2018/8/18.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.VH> {

    private List<NewsResultEntity> newsResultEntities = null;

    public NewsAdapter(List<NewsResultEntity> newsResultEntities){
        this.newsResultEntities = newsResultEntities;
    }

    @NonNull
    @Override
    public NewsAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.example4_item_layout, parent, false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.VH viewHolder, int i) {
        NewsResultEntity newsResultEntity = newsResultEntities.get(i);

        viewHolder.type.setText(newsResultEntity.getType());
        viewHolder.desc.setText(newsResultEntity.getDesc());
    }

    @Override
    public int getItemCount() {
        return newsResultEntities.size();
    }

    static class VH extends RecyclerView.ViewHolder{

        private TextView type;
        private TextView desc;

        public VH(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.example4_item_type);
            desc = itemView.findViewById(R.id.example4_item_desc);
        }
    }
}
