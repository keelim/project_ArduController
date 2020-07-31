package com.keelim.hard.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.keelim.hard.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return when (preference!!.key) {
            "github" -> {
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/keelim/TestingHardWare")).apply {
                    startActivity(this)
                }
                true
            }
            else -> false
        }
    }


}