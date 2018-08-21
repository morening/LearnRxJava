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

    @OnClick(R.id.example1_tv)
    void onClickExample1Tv(){
        String next = Example1Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example1Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example2_tv)
    void onClickExample2Tv(){
        String next = Example2Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example2Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example3_tv)
    void onClickExample3Tv(){
        String next = Example3Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example3Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example4_tv)
    void onClickExample4Tv(){
        String next = Example4Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example4Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example5_tv)
    void onClickExample5Tv(){
        String next = Example5Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example5Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example6_tv)
    void onClickExample6Tv(){
        String next = Example6Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example6Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example7_tv)
    void onClickExample7Tv(){
        String next = Example7Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example7Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example8_tv)
    void onClickExample8Tv(){
        String next = Example8Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example8Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example10_tv)
    void onClickExample10Tv(){
        Intent intent = new Intent(getActivity(), Example10Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.example11_tv)
    void onClickExample11Tv(){
        String next = Example11Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example11Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example12_tv_city)
    void onClickExample12Tv(){
        String next = Example12Fragment.class.getSimpleName();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = new Example12Fragment();
        }
        ft.replace(R.id.content_container, fragment, next);
        ft.addToBackStack(next);
        ft.commitAllowingStateLoss();
    }
}
