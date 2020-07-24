package com.keelim.hardware.model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> list;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
        list.add(new Fragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
