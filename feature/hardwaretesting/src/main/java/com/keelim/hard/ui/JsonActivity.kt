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
package com.keelim.hard.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.common.BackPressCloseHandler
import com.keelim.hard.R
import kotlinx.android.synthetic.main.activity_json.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.charset.Charset

class JsonActivity : AppCompatActivity() {
  private lateinit var onBackPressCloseHandler: BackPressCloseHandler
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_json)

    onBackPressCloseHandler = BackPressCloseHandler(this)

    json_tv.movementMethod = ScrollingMovementMethod()

    json_btn.setOnClickListener {
      json_tv.text = getJsonString()
      json_btn.visibility = View.INVISIBLE
    }
  }

  private fun getJsonString(): String {
    var json = ""

    try {
      val input = FileInputStream(File(application.filesDir, getString(R.string.file)))
      val fileSize: Int = input.available()
      val buffer = ByteArray(fileSize)
      input.read(buffer)
      input.close()

      json = String(buffer, Charset.defaultCharset())
    } catch (e: IOException) {
      Toast.makeText(this, "파일 만드는데 문제가 생겼습니다. ", Toast.LENGTH_SHORT).show()
    }
    return json
  }

  override fun onBackPressed() {
    onBackPressCloseHandler.onBackPressed()
  }
}
