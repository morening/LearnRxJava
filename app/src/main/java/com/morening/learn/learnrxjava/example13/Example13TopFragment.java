package com.morening.learn.learnrxjava.example13;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morening.learn.learnrxjava.R;
import com.morening.learn.learnrxjava.example13.rx.RxConnector;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example13TopFragment extends Fragment {

    private Unbinder unbinder = null;

    private int tapCnt = 0;
    private PublishSubject<Integer> tapSubject = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_example13_top, container, false);
        unbinder = ButterKnife.bind(this, root);
        tapSubject = PublishSubject.create();

        RxConnector.getInstance().add(ConnectKey.KEY_INTEGER_TAP, tapSubject).engage(ConnectKey.KEY_INTEGER_TAP);

        return root;
    }

    @OnClick(R.id.example13_btn)
    void onClickTapBtn(){
        tapSubject.onNext(tapCnt++);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxConnector.getInstance().dispose(ConnectKey.KEY_INTEGER_TAP);
    }
}
