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
import com.keelim.benchmark.databinding.ActivityStorageBinding
import com.keelim.model.Data
import io.paperdb.Paper
import timber.log.Timber

class StorageActivity : AppCompatActivity() {

  private lateinit var binding: ActivityStorageBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityStorageBinding.inflate(layoutInflater)
    setContentView(binding.root)

    Paper.init(applicationContext)

    binding.btnStoreAll.setOnClickListener {
      val items = ArrayList<Data>()

      val startTime = System.currentTimeMillis()
      (0..1000).forEach { i ->
        items.add(Data(i, "Lorem ipsum"))
      }

      Paper.book().write("items", items)

      val stopTime = System.currentTimeMillis()
      val elapsedTime = stopTime - startTime

      Timber.d(elapsedTime.toString())
    }

    binding.btnStoreSingle.setOnClickListener {

      val startTime = System.currentTimeMillis()

      (0..1000).forEach { i ->
        Paper.book().write("items_$i", Data(i, "Lorem ipsum"))
      }

      val stopTime = System.currentTimeMillis()
      val elapsedTime = stopTime - startTime

      Timber.d(elapsedTime.toString())
    }

    binding.btnReadAll.setOnClickListener {
      val startTime = System.currentTimeMillis()

      Paper.book().read<Data>("items")

      val stopTime = System.currentTimeMillis()
      val elapsedTime = stopTime - startTime

      Timber.d(elapsedTime.toString())
    }

    binding.btnReadSingle.setOnClickListener {
      val startTime = System.currentTimeMillis()

      (0..1000).forEach { i ->
        Paper.book().read<Data>("items_$i")
      }

      val stopTime = System.currentTimeMillis()
      val elapsedTime = stopTime - startTime

      Timber.d(elapsedTime.toString())
    }
  }
}
