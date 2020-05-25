package com.keelim.testing.result

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.keelim.testing.R
import com.keelim.testing.utils.BackPressCloseHandler

class ResultActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //
        AlertDialog.Builder(this)
            .setMessage("결과를 전송")
            .setNegativeButton("아니오") { _, _ ->

            }
            .setPositiveButton("예") { _, _ ->

            }
            .create()
            .show()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        BackPressCloseHandler(this).onBackPressed()
    }

    override fun onClick(v: View) {

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}