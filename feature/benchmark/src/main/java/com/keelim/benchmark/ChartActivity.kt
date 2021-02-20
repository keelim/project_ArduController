package com.keelim.benchmark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.keelim.benchmark.databinding.ActivityChartBinding
import java.util.*

class ChartActivity : AppCompatActivity() {

    private val valsComp1 = (0..1000).map { Entry(it.toFloat(), random(1, 100).toFloat()) }
    private lateinit var binding: ActivityChartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Chart Benchmark"

        val chart = findViewById<LineChart>(R.id.chart)

        binding.btnRenderChart.setOnClickListener {

            val setComp1 = LineDataSet(valsComp1, "Data")
            setComp1.axisDependency = AxisDependency.LEFT

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(setComp1)

            val data = LineData(dataSets)
            chart.data = data
            chart.invalidate() // refresh
        }
    }

    private fun random(from: Int, to: Int) = (Math.random() * (to - from) + from).toInt()
}
