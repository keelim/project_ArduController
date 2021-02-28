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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.keelim.benchmark.databinding.ActivityChartBinding

class ChartActivity : AppCompatActivity() {
  private val valsComp1 = (0..1000).map { Entry(it.toFloat(), random(1, 100).toFloat()) }
  private lateinit var binding: ActivityChartBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityChartBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnRenderChart.setOnClickListener {
      val setComp1 = LineDataSet(valsComp1, "Data")
      setComp1.axisDependency = AxisDependency.LEFT

      val dataSets = ArrayList<ILineDataSet>()
      dataSets.add(setComp1)

      val data = LineData(dataSets)
      binding.chart.data = data
      binding.chart.invalidate() // refresh
    }
  }

  private fun random(from: Int, to: Int) = (Math.random() * (to - from) + from).toInt()
}
