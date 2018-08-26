package com.morening.learn.learnrxjava;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morening.learn.learnrxjava.example1.Example1Fragment;
import com.morening.learn.learnrxjava.example10.Example10Activity;
import com.morening.learn.learnrxjava.example11.Example11Fragment;
import com.morening.learn.learnrxjava.example12.Example12Fragment;
import com.morening.learn.learnrxjava.example13.Example13Fragment;
import com.morening.learn.learnrxjava.example14.Example14Fragment;
import com.morening.learn.learnrxjava.example15.Example15Fragment;
import com.morening.learn.learnrxjava.example2.Example2Fragment;
import com.morening.learn.learnrxjava.example3.Example3Fragment;
import com.morening.learn.learnrxjava.example4.Example4Fragment;
import com.morening.learn.learnrxjava.example5.Example5Fragment;
import com.morening.learn.learnrxjava.example6.Example6Fragment;
import com.morening.learn.learnrxjava.example7.Example7Fragment;
import com.morening.learn.learnrxjava.example8.Example8Fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    private Unbinder unbinder = null;

    private FragmentManager fm = null;
    private FragmentTransaction ft = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, root);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private <T extends Fragment> void onClick(T t){
        String next = t.getClass().getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = t;
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(MainFragment.class.getSimpleName());
        ft.commit();
    }

    @OnClick(R.id.example1_tv)
    void onClickExample1Tv(){
        onClick(new Example1Fragment());
    }

    @OnClick(R.id.example2_tv)
    void onClickExample2Tv(){
        onClick(new Example2Fragment());
    }

    @OnClick(R.id.example3_tv)
    void onClickExample3Tv(){
        onClick(new Example3Fragment());
    }

    @OnClick(R.id.example4_tv)
    void onClickExample4Tv(){
        onClick(new Example4Fragment());
    }

    @OnClick(R.id.example5_tv)
    void onClickExample5Tv(){
        onClick(new Example5Fragment());
    }

    @OnClick(R.id.example6_tv)
    void onClickExample6Tv(){
        onClick(new Example6Fragment());
    }

    @OnClick(R.id.example7_tv)
    void onClickExample7Tv(){
        onClick(new Example7Fragment());
    }

    @OnClick(R.id.example8_tv)
    void onClickExample8Tv(){
        onClick(new Example8Fragment());
    }

    @OnClick(R.id.example10_tv)
    void onClickExample10Tv(){
        Intent intent = new Intent(getActivity(), Example10Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.example11_tv)
    void onClickExample11Tv(){
        onClick(new Example11Fragment());
    }

    @OnClick(R.id.example12_tv)
    void onClickExample12Tv(){
        onClick(new Example12Fragment());
    }

    @OnClick(R.id.example13_tv)
    void onClickExample13Tv(){
        onClick(new Example13Fragment());
    }

    @OnClick(R.id.example14_tv)
    void onClickExample14Tv(){
        onClick(new Example14Fragment());
    }

    @OnClick(R.id.example15_tv)
    void onClickExample15Tv(){
        onClick(new Example15Fragment());
    }
}
