package com.keelim.benchmark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.keelim.benchmark.databinding.ActivityHttpBinding
import timber.log.Timber


class HttpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHttpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "HTTP Benchmark"

        FuelManager.instance.basePath = "https://jsonplaceholder.typicode.com"

        binding.btnGet.setOnClickListener {

            val startTime = System.currentTimeMillis()
            var counter = 0

            for (i in 1..100) {

                "/comments".httpGet().responseString { request, response, result ->
                    counter++
                    if (counter == 100) {
                        val stopTime = System.currentTimeMillis()
                        val elapsedTime = stopTime - startTime
                        Timber.d( elapsedTime.toString())
                    }
                }
            }

        }

        binding.btnPost.setOnClickListener {
            val startTime = System.currentTimeMillis()
            var counter = 0

            for (i in 1..100) {

                ("/comments/").httpPost().body("{ \"id\" : \"1\", \"name\" : \"Lorem Ipsum\", \"email\" : \"test@mail.com\", \"body\" : \"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\" }").response { request, response, result ->
                    counter++
                    if (counter == 100) {
                        val stopTime = System.currentTimeMillis()
                        val elapsedTime = stopTime - startTime
                        Timber.d( elapsedTime.toString())
                    }
                }
            }
        }

        binding.btnPut.setOnClickListener {
            val startTime = System.currentTimeMillis()
            var counter = 0

            for (i in 1..100) {

                ("/comments/$i").httpPut().body("{ \"id\" : \"1\", \"name\" : \"Lorem Ipsum\", \"email\" : \"test@mail.com\", \"body\" : \"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\" }").response { request, response, result ->
                    counter++
                    if (counter == 100) {
                        val stopTime = System.currentTimeMillis()
                        val elapsedTime = stopTime - startTime
                        Timber.d(elapsedTime.toString())
                    }
                }
            }
        }

        binding.btnDelete.setOnClickListener {
            val startTime = System.currentTimeMillis()
            var counter = 0

            for (i in 1..100) {

                ("/comments/$i").httpDelete().response { request, response, result ->
                    counter++
                    if (counter == 100) {
                        val stopTime = System.currentTimeMillis()
                        val elapsedTime = stopTime - startTime
                        Timber.d( elapsedTime.toString())
                    }
                }
            }
        }

    }
}
