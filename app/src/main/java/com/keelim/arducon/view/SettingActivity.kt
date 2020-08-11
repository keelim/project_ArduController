package com.keelim.arducon.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.keelim.arducon.R

class SettingActivity : AppCompatActivity(R.layout.activity_setting) {

    override fun onCreate(savedInstanceState: Bundle?) { //fragment 설정을 위한 activity
        super.onCreate(savedInstanceState)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SettingFragment())
                .commit()
    }


    class SettingFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle, rootKey: String) {
            addPreferencesFromResource(R.xml.setting_preference)
        }

        override fun onPreferenceTreeClick(preference: Preference): Boolean {
            when (preference.key) {
                "developer" -> Toast.makeText(context, "개발 중입니다.", Toast.LENGTH_SHORT).show()
            }

            return super.onPreferenceTreeClick(preference)
        }
    }
}