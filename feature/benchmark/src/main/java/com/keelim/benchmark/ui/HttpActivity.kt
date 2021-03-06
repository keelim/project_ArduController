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
package com.keelim.benchmark.ui

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
            Timber.d(elapsedTime.toString())
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
            Timber.d(elapsedTime.toString())
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
            Timber.d(elapsedTime.toString())
          }
        }
      }
    }
  }
}
