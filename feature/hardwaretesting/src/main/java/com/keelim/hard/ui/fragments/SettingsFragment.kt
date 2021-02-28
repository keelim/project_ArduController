/*
 * Designed and developed by 2020 keelim (Jaehyun Kim)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keelim.hard.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.keelim.hard.R

class SettingsFragment : PreferenceFragmentCompat() {

  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    (requireActivity() as AppCompatActivity).supportActionBar!!.hide()
    setPreferencesFromResource(R.xml.root_preferences, rootKey)
  }

  override fun onPreferenceTreeClick(preference: Preference?): Boolean {
    return when (preference!!.key) {
      "github" -> {
        Intent(
          Intent.ACTION_VIEW,
          Uri.parse("https://github.com/keelim/TestingHardWare")
        ).apply {
          startActivity(this)
        }
        true
      }

      else -> false
    }
  }
}
