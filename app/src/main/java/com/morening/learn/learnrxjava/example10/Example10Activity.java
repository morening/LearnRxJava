package com.morening.learn.learnrxjava.example10;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.morening.learn.learnrxjava.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;

public class Example10Activity extends Activity implements IHolder {

    private static final String TAG = Example10Activity.class.getSimpleName();

    private CompositeDisposable compositeDisposable = null;

    @BindView(R.id.example10_tv)
    TextView example10_tv;

    private Unbinder unbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example10);
        unbinder = ButterKnife.bind(this);

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onResume() {
        super.onResume();

        addFragment();
    }

    private void addFragment() {
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentByTag(Example10Fragment.class.getSimpleName());
        if (fragment == null) {
            fragment = new Example10Fragment();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_container, fragment, Example10Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onWorkerPrepared(ConnectableObservable<Long> worker) {
        Log.d(TAG, "onWorkerPrepared worker="+worker);
        DisposableObserver<Long> disposableObserver = new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                example10_tv.setText("onNext aLong="+aLong);
            }

            @Override
            public void onError(Throwable e) {
                example10_tv.setText("onError e="+e.getMessage());
            }

            @Override
            public void onComplete() {
                example10_tv.setText("onComplete");
            }
        };
        worker.observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        compositeDisposable.clear();
    }
}
