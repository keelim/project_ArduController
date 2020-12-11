package com.keelim.arducon.ui.setting

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.play.core.review.ReviewManagerFactory
import com.keelim.arducon.R

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "setting" -> {
                val manager = ReviewManagerFactory.create(requireContext())

                manager.requestReviewFlow().apply {
                    addOnCompleteListener {
                        if (this.isSuccessful) {
                            manager.launchReviewFlow(requireActivity(), this.result)
                        }
                    }
                }
            }
        }
        return super.onPreferenceTreeClick(preference)
    }
}
