package com.morening.learn.learnrxjava.example13;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morening.learn.learnrxjava.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Example13Fragment extends Fragment {

    private Unbinder unbinder = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_example13, container, false);
        unbinder = ButterKnife.bind(this, root);

        addFragment(new Example13TopFragment(), R.id.example13_fragment_top);
        addFragment(new Example13BottomFragment(), R.id.example13_fragment_bottom);

        return root;
    }

    private <T extends Fragment>void addFragment(T fragment, int container_id){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(container_id, fragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
