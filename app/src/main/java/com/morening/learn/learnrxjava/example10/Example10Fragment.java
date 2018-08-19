package com.morening.learn.learnrxjava.example10;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example10Fragment extends Fragment {

    private static final String TAG = Example10Fragment.class.getSimpleName();

    private IHolder holder = null;

    private Disposable disposable = null;
    private ConnectableObservable<Long> worker = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setRetainInstance(true);

        worker = Observable.intervalRange(0, 100, 0, 200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io()).publish();
        startUpdateProgress();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View root = inflater.inflate(R.layout.fragment_example10, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (holder != null){
            holder.onWorkerPrepared(worker);
        }
    }

    private void startUpdateProgress() {
        Log.d(TAG, "startUpdateProgress");

        disposable = worker.connect();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        if (context instanceof IHolder){
            holder = (IHolder)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
        holder = null;
    }
}
