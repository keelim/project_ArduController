package com.keelim.arducon.ui.setting

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.keelim.arducon.R

class SettingFragment : PreferenceFragmentCompat() {
    private lateinit var  manager: ReviewManager
    private lateinit var reviewInfo:ReviewInfo
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        manager = ReviewManagerFactory.create(requireActivity())
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                reviewInfo = request.result
            }
        }
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "review" -> {
                val flow = manager.launchReviewFlow(requireActivity(), reviewInfo)
                flow.addOnCompleteListener { Toast.makeText(requireActivity(), "Thank you!!", Toast.LENGTH_SHORT).show() }

            }
        }
        return super.onPreferenceTreeClick(preference)
    }
}
