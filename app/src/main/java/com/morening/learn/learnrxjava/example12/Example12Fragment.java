package com.morening.learn.learnrxjava.example12;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.morening.learn.learnrxjava.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example12Fragment extends Fragment {

    private static final String TAG = Example12Fragment.class.getSimpleName();

    private Unbinder unbinder = null;

    @BindView(R.id.example12_btn)
    Button example12_btn;

    @BindView(R.id.example12_tv)
    TextView example12_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example12, container, false);
        unbinder = ButterKnife.bind(this, root);
        RxView.clicks(example12_btn)
                .buffer(2, TimeUnit.SECONDS)
                .flatMap(new Function<List<?>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<Integer> apply(List<?> objects) throws Exception {
                        return Observable.just(objects.size());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (example12_tv != null){
                            example12_tv.setText(o.toString());
                        }
                    }
                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
