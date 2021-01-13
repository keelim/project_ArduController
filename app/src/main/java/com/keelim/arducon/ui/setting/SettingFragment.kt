package com.keelim.arducon.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.keelim.arducon.R
import com.keelim.arducon.ui.OpenSourceActivity

class SettingFragment : PreferenceFragmentCompat() {
    private lateinit var manager: ReviewManager
    private lateinit var reviewInfo: ReviewInfo

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        readyReview()
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "opensource" -> startActivity(Intent(context, OpenSourceActivity::class.java))

            "update" -> Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(getString(R.string.uriarducon))
                startActivity(this)
            }

            "dark" -> {
                Toast.makeText(requireActivity(), "업데이트 준비 중 입니다", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onPreferenceTreeClick(preference)
    }

    private fun readyReview() {
        manager = ReviewManagerFactory.create(requireActivity())
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                reviewInfo = request.result
            }
        }
        val flow = manager.launchReviewFlow(requireActivity(), reviewInfo)
        flow.addOnCompleteListener { Toast.makeText(requireActivity(), "Thank you!!", Toast.LENGTH_SHORT).show() }
    }
}
