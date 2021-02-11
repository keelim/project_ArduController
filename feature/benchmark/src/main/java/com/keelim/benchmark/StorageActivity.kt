package com.keelim.benchmark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keelim.benchmark.databinding.ActivityStorageBinding
import io.paperdb.Paper
import timber.log.Timber


class StorageActivity : AppCompatActivity() {

    private data class Data(var id: Int, var body: String)
    private lateinit var binding: ActivityStorageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=  ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Storage Benchmark"

        Paper.init(applicationContext)


        binding.btnStoreAll.setOnClickListener {
            val items = ArrayList<Data>()

            val startTime = System.currentTimeMillis()

            for (i in 0..1000) {
                items.add(Data(i, "Lorem ipsum"))
            }

            Paper.book().write("items", items)

            val stopTime = System.currentTimeMillis()
            val elapsedTime = stopTime - startTime

            Timber.d( elapsedTime.toString())
        }

        binding.btnStoreSingle.setOnClickListener {

            val startTime = System.currentTimeMillis()

            for (i in 0..1000) {
                Paper.book().write("items_$i", Data(i, "Lorem ipsum"))
            }

            val stopTime = System.currentTimeMillis()
            val elapsedTime = stopTime - startTime

            Timber.d( elapsedTime.toString())
        }

        binding.btnReadAll.setOnClickListener {
            val startTime = System.currentTimeMillis()

            Paper.book().read<Data>("items")

            val stopTime = System.currentTimeMillis()
            val elapsedTime = stopTime - startTime

            Timber.d( elapsedTime.toString())
        }

        binding.btnReadSingle.setOnClickListener {
            val startTime = System.currentTimeMillis()

            for (i in 0..1000) {
                Paper.book().read<Data>("items_$i")
            }

            val stopTime = System.currentTimeMillis()
            val elapsedTime = stopTime - startTime

            Timber.d( elapsedTime.toString())
        }

    }
}
