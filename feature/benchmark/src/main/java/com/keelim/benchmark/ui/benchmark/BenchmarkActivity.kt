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
package com.keelim.benchmark.ui.benchmark

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.keelim.benchmark.R
import com.keelim.benchmark.databinding.ActivityBenchmarkBinding
import com.keelim.benchmark.ui.ChartActivity
import com.keelim.benchmark.ui.HttpActivity
import com.keelim.benchmark.ui.ListViewActivity
import com.keelim.benchmark.ui.StorageActivity

class BenchmarkActivity : AppCompatActivity() {

  private lateinit var binding: ActivityBenchmarkBinding
  private val viewModel by viewModels<BenchmarkViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_benchmark)
    binding.vm = viewModel
    binding.lifecycleOwner = this

    viewModel.viewEvent.observe(
      this,
      {
        it.getContentIfNotHandled()?.let { event ->
          when (event) {
            BenchmarkViewModel.VIEW_1 -> startActivity(Intent(this, ListViewActivity::class.java))

            BenchmarkViewModel.VIEW_2 -> startActivity(Intent(this, HttpActivity::class.java))

            BenchmarkViewModel.VIEW_3 -> startActivity(Intent(this, StorageActivity::class.java))

            BenchmarkViewModel.VIEW_4 -> startActivity(Intent(this, ChartActivity::class.java))
          }
        }
      }
    )

//        binding.btnHttp.setOnClickListener {
//            startActivity(Intent(this, HttpActivity::class.java))
//        }
//
//        binding.btnStorage.setOnClickListener {
//            startActivity(Intent(this, StorageActivity::class.java))
//        }
//
//        binding.btnChart.setOnClickListener {
//            startActivity(Intent(this, ChartActivity::class.java))
//        }
  }
}
