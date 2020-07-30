package com.keelim.hard.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.keelim.hard.R
import com.keelim.hard.view.OpenSourceActivity

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        when (preference!!.key) {
            "github" -> {
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/keelim/TestingHardWare")).apply {
                    startActivity(this);
                }
                return true
            }

            "opensource" -> {
                Intent(requireActivity(), OpenSourceActivity::class.java).apply {
                    startActivity(this)
                }
                return true
            }
            else -> return false
        }
    }


}