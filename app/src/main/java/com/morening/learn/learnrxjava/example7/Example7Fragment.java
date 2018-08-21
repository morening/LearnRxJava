package com.morening.learn.learnrxjava.example7;


import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.morening.learn.learnrxjava.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example7Fragment extends Fragment {

    private static final String TAG = Example7Fragment.class.getSimpleName();

    @BindView(R.id.example7_btn)
    Button example7_btn;

    private Unbinder unbinder = null;

    private CompositeDisposable compositeDisposable = null;
    private PublishSubject<String> userSubject = null;
    private PublishSubject<String> pwdSubject = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_example7, container, false);
        unbinder = ButterKnife.bind(this, root);

        compositeDisposable = new CompositeDisposable();
        userSubject = PublishSubject.create();
        pwdSubject = PublishSubject.create();

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
        Observable.combineLatest(userSubject, pwdSubject, new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String user, String pwd) throws Exception {
                return (user.length()>=3 && user.length()<=6) && (pwd.length()>=4 && pwd.length()<=8);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

        compositeDisposable.add(observer);

        return root;
    }

    @OnTextChanged(R.id.example7_et_user)
    void afterChangeUser(Editable s){
        userSubject.onNext(s.toString());
    }

    @OnTextChanged(R.id.example7_et_pwd)
    void afterChangePwd(Editable s){
        pwdSubject.onNext(s.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
        unbinder.unbind();
    }
}
