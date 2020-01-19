package com.keelim.arducon.view.setting

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.keelim.arducon.R

class SettingFragment : PreferenceFragmentCompat() {
    //setting
    override fun onCreatePreferences(savedInstanceState: Bundle, rootKey: String) {
        addPreferencesFromResource(R.xml.setting_preference)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        val key = preference.key
        when(key){
            "developer" -> Toast.makeText(context, "개발 진행 중입니다.", Toast.LENGTH_SHORT).show()

        }
        return super.onPreferenceTreeClick(preference)
    }


}