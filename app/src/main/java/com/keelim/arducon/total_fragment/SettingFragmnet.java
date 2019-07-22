package com.keelim.arducon.total_fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.keelim.arducon.R;

public class SettingFragmnet extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting_preference, rootKey);
    }
}
