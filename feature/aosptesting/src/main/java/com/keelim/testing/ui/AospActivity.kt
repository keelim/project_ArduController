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
package com.keelim.testing.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.databinding.ActivityAospBinding
import com.keelim.testing.ui.handlertest.HandlerTestActivity
import com.keelim.testing.ui.windowtest.AddWindowTestActivity

class AospActivity : AppCompatActivity() {
  private lateinit var binding: ActivityAospBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAospBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnTest1.setOnClickListener {
      Intent(this, AddWindowTestActivity::class.java).apply {
        startActivity(this)
        finish()
      }
    }

    binding.btnTest2.setOnClickListener {
      Intent(this, HandlerTestActivity::class.java).apply {
        startActivity(this)
        finish()
      }
    }
  }
}
