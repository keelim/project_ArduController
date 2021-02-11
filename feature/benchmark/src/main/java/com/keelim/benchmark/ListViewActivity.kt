package com.keelim.benchmark

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.keelim.benchmark.databinding.ActivityListViewBinding

import java.util.*

class ListViewActivity : AppCompatActivity() {

    private class Data(var id: Int, var label: String)

    private var listItems = ArrayList<Data>()
    private var id: Int = 1
    private lateinit var binding: ActivityListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "List View Benchmark"
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)

        binding.lv.adapter = arrayAdapter

        binding.btnCreate.setOnClickListener {
            listItems.clear()
            listItems.addAll(buildData())
            arrayAdapter.notifyDataSetChanged()
        }

        binding.btnCreateLots.setOnClickListener {
            listItems.clear()
            listItems.addAll(buildData(10000))
            arrayAdapter.notifyDataSetChanged()
        }

        binding.btnAppend.setOnClickListener {
            listItems.addAll(buildData(10000))
            arrayAdapter.notifyDataSetChanged()
        }

        binding.btnUpdate.setOnClickListener {
            for (i in 0..listItems.size step 10) {
                listItems[i].label += " !!!"
            }
            arrayAdapter.notifyDataSetChanged()
        }

        binding.btnClear.setOnClickListener {
            listItems.clear()
            arrayAdapter.notifyDataSetChanged()
        }
    }

    private fun buildData(count: Int = 1000): ArrayList<Data> {
        val adjectives: Array<String> = arrayOf("pretty", "large", "big", "small", "tall", "short", "long", "handsome", "plain", "quaint", "clean", "elegant", "easy", "angry", "crazy", "helpful", "mushy", "odd", "unsightly", "adorable", "important", "inexpensive", "cheap", "expensive", "fancy")
        val colours: Array<String> = arrayOf("red", "yellow", "blue", "green", "pink", "brown", "purple", "brown", "white", "black", "orange")
        val nouns: Array<String> = arrayOf("table", "chair", "house", "bbq", "desk", "car", "pony", "cookie", "sandwich", "burger", "pizza", "mouse", "keyboard")

        val data = ArrayList<Data>()

        for (i in 0..count) {
            data.add(Data(id, adjectives[random(0, adjectives.size)] + " " + colours[random(0, colours.size)] + " " + nouns[random(0, nouns.size)]))
            id++
        }

        return data

    }

    private fun random(from: Int, to: Int) = (Math.random() * (to - from) + from).toInt()
}
