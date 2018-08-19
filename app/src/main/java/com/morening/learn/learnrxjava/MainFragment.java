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
import com.morening.learn.learnrxjava.example2.Example2Fragment;
import com.morening.learn.learnrxjava.example3.Example3Fragment;
import com.morening.learn.learnrxjava.example4.Example4Fragment;
import com.morening.learn.learnrxjava.example5.Example5Fragment;
import com.morening.learn.learnrxjava.example6.Example6Fragment;
import com.morening.learn.learnrxjava.example7.Example7Fragment;
import com.morening.learn.learnrxjava.example8.Example8Fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    private FragmentManager fm = null;
    private FragmentTransaction ft = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        return root;
    }

    @OnClick(R.id.example1_tv)
    void onClickExample1Tv(){
        Fragment fragment = fm.findFragmentByTag(Example1Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example1Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example1Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example2_tv)
    void onClickExample2Tv(){
        Fragment fragment = fm.findFragmentByTag(Example2Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example2Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example2Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example3_tv)
    void onClickExample3Tv(){
        Fragment fragment = fm.findFragmentByTag(Example3Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example3Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example3Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example4_tv)
    void onClickExample4Tv(){
        Fragment fragment = fm.findFragmentByTag(Example4Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example4Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example4Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example5_tv)
    void onClickExample5Tv(){
        Fragment fragment = fm.findFragmentByTag(Example5Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example5Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example5Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example6_tv)
    void onClickExample6Tv(){
        Fragment fragment = fm.findFragmentByTag(Example6Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example6Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example6Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example7_tv)
    void onClickExample7Tv(){
        Fragment fragment = fm.findFragmentByTag(Example7Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example7Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example7Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example8_tv)
    void onClickExample8Tv(){
        Fragment fragment = fm.findFragmentByTag(Example8Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example8Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example8Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    @OnClick(R.id.example10_tv)
    void onClickExample10Tv(){
        Intent intent = new Intent(getActivity(), Example10Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.example11_tv_city)
    void onClickExample11Tv(){
        Fragment fragment = fm.findFragmentByTag(Example11Fragment.class.getSimpleName());
        if (fragment == null){
            fragment = new Example11Fragment();
        }
        ft.replace(R.id.content_container, fragment, Example11Fragment.class.getSimpleName());
        ft.addToBackStack(this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }
}
