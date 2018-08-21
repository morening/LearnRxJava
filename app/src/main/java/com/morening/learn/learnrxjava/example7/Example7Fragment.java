package com.morening.learn.learnrxjava.example7;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.morening.learn.learnrxjava.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example7Fragment extends Fragment {

    private static final String TAG = Example7Fragment.class.getSimpleName();

    @BindView(R.id.example7_btn)
    Button example7_btn;

    @BindView(R.id.example7_et_user)
    EditText example7_et_user;

    @BindView(R.id.example7_et_pwd)
    EditText example7_et_pwd;

    private Unbinder unbinder = null;

    private CompositeDisposable compositeDisposable = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example7, container, false);
        unbinder = ButterKnife.bind(this, root);

        compositeDisposable = new CompositeDisposable();

        DisposableObserver<Boolean> observer = new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean){
                    example7_btn.setEnabled(true);
                    example7_btn.setAlpha(1.0f);
                } else {
                    example7_btn.setEnabled(false);
                    example7_btn.setAlpha(0.5f);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        Observable.combineLatest(RxTextView.textChanges(example7_et_user)
                , RxTextView.textChanges(example7_et_pwd), new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence user, CharSequence pwd) throws Exception {
                return (user.length()>=3 && user.length()<=6) && (pwd.length()>=4 && pwd.length()<=8);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

        compositeDisposable.add(observer);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
        unbinder.unbind();
    }
}
