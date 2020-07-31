package com.keelim.hard.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.hard.R
import com.keelim.hard.utils.BackPressCloseHandler
import kotlinx.android.synthetic.main.activity_json.*
import java.io.IOException
import java.nio.charset.Charset

class JsonActivity : AppCompatActivity() {
    private lateinit var onBackPressCloseHandler: BackPressCloseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json)

        onBackPressCloseHandler = BackPressCloseHandler(this)

        json_btn.setOnClickListener {
            json_tv.text = getJsonString()
        }
    }

    private fun getJsonString(): String {
        var json = ""

        try {
            val input = openFileInput(application.filesDir.absolutePath + getString(R.string.file))
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