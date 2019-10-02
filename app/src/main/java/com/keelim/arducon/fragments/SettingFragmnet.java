package com.keelim.arducon.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.keelim.arducon.R;

public class SettingFragmnet extends PreferenceFragmentCompat { //setting
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting_preference);
    }
}
