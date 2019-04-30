package com.keelim.arducon;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


public class BugReport extends Fragment implements MainActivity.onKeyBackPressedListener {


    public BugReport() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bug_report, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //메인뷰 액티비티의 뒤로가기 callback 붙이기
        ((MainActivity) context).setOnKeyBackPressedListener(this);
    }

    @Override
    public void onBackKey() {
        MainActivity activity = (MainActivity) getActivity();
        //액티비티의 콜백을 직접호출
        Objects.requireNonNull(activity).onBackPressed();
        activity.setOnKeyBackPressedListener(null);
    }


}