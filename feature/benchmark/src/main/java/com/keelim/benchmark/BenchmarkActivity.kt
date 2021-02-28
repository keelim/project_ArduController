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
package com.keelim.benchmark

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.benchmark.databinding.ActivityBenchmarkBinding

class BenchmarkActivity : AppCompatActivity() {
  private lateinit var binding: ActivityBenchmarkBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityBenchmarkBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnList.setOnClickListener {
      startActivity(Intent(this, ListViewActivity::class.java))
    }

    binding.btnHttp.setOnClickListener {
      startActivity(Intent(this, HttpActivity::class.java))
    }

    binding.btnStorage.setOnClickListener {
      startActivity(Intent(this, StorageActivity::class.java))
    }

    binding.btnChart.setOnClickListener {
      startActivity(Intent(this, ChartActivity::class.java))
    }
  }
}
