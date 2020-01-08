package com.keelim.arducon.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.keelim.arducon.R;

public class SettingFragment extends PreferenceFragmentCompat { //setting
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting_preference);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();
//        switch (key){
//            case default:
//                break;
//        }
        return false;
    }
}
