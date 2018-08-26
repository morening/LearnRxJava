package com.morening.learn.learnrxjava.example15;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.morening.learn.learnrxjava.MainFragment;
import com.morening.learn.learnrxjava.R;

public class Example15DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example15_detail);

        Intent intent = getIntent();
        if (intent != null){
            String city = intent.getStringExtra(Constants.EXTRA_STRING_CITY);
            Fragment fragment = new Example15DetailFragment();
            Bundle args = new Bundle();
            args.putString(Constants.EXTRA_STRING_CITY, city);
            fragment.setArguments(args);
            addFragment(fragment);
        }
    }

    private <T extends Fragment> void addFragment(T t){
        String next = t.getClass().getSimpleName();
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentByTag(next);
        if (fragment == null){
            fragment = t;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_container, fragment, next);
        ft.commit();
    }
}
