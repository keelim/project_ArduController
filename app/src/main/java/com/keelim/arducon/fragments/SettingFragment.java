package com.keelim.arducon.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.keelim.arducon.R;
import com.keelim.arducon.activities.DeveloperActivity;

public class SettingFragment extends PreferenceFragmentCompat { //setting
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting_preference);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();
        if (key.equals("developer")) {
            Intent intent_developer = new Intent(getContext(), DeveloperActivity.class);
            startActivity(intent_developer);
            return true;
        }
        return false;
    }
}
