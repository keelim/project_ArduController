package com.keelim.arducon.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.keelim.arducon.R
import com.keelim.arducon.ui.OpenSourceActivity
import com.keelim.arducon.utils.ThemeHelper

class SettingFragment : PreferenceFragmentCompat() {
    private lateinit var manager: ReviewManager
    private lateinit var reviewInfo: ReviewInfo

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        readyReview()

        val themePreference: ListPreference = findPreference("themePref")!!
        themePreference.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            val themeOption = newValue as String
            ThemeHelper.applyTheme(themeOption)
            true
        }
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "opensource" -> startActivity(Intent(context, OpenSourceActivity::class.java))

            "update" -> Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(getString(R.string.uriarducon))
                startActivity(this)
            }

        }
        return super.onPreferenceTreeClick(preference)
    }

    private fun readyReview() {
        val manager = ReviewManagerFactory.create(requireActivity())

        manager.requestReviewFlow().apply {
            addOnCompleteListener {
                if (this.isSuccessful) {
                    manager.launchReviewFlow(requireActivity(), this.result)
                }
            }
        }
    }
}
