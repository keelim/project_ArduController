package com.keelim.arducon;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class MainPreference extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference);
    }
}
