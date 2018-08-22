package com.morening.learn.learnrxjava.example13;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;
import com.morening.learn.learnrxjava.example13.rx.RxConnector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example13BottomFragment extends Fragment {

    private Unbinder unbinder = null;

    @BindView(R.id.example13_tv)
    TextView example13_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_example13_bottom, container, false);
        unbinder = ButterKnife.bind(this, root);

        DisposableObserver<Integer> observer = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer value) {
                example13_tv.setText(String.valueOf(value));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        RxConnector.getInstance().add(ConnectKey.KEY_INTEGER_TAP, observer).engage(ConnectKey.KEY_INTEGER_TAP);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxConnector.getInstance().dispose(ConnectKey.KEY_INTEGER_TAP);
    }
}
