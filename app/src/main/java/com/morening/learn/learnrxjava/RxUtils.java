package com.morening.learn.learnrxjava;

import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by morening on 2018/8/20.
 */
public class RxUtils {

    public static Observable<?> onClick(View view){
        return Observable.create(new ClickObservableOnSubscribe(view));
    }

    private static class ClickObservableOnSubscribe implements ObservableOnSubscribe {

        private View view = null;

        public ClickObservableOnSubscribe(View view){
            this.view = view;
        }

        @Override
        public void subscribe(ObservableEmitter emitter) throws Exception {
            view.setOnClickListener(v -> {
                emitter.onNext(0);
            });
        }
    }
}
