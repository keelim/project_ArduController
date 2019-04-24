package com.keelim.arducon;

import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingFragment extends PreferenceFragmentCompat {

    private ListPreference mListPreference;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Indicate here the XML resource you created above that holds the preferences
        setPreferencesFromResource(R.xml.settings_preference, rootKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mListPreference = (ListPreference) getPreferenceManager().findPreference("preference_key");


        return inflater.inflate(R.layout.main_fragment, container, false);
    }
}