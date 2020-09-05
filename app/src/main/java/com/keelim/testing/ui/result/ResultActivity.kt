package com.keelim.testing.ui.result

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.keelim.testing.R
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*


class ResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var resultArray: ArrayList<Long>
    private lateinit var fab_open: Animation
    private lateinit var fab_close: Animation
    private var isFabOpen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close);

        resultArray = intent.getSerializableExtra("result") as ArrayList<Long>
        // array List 를 받는다.

        result_recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ResultActivity)
            adapter = ResultAdapter(resultArray)
        }

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);


    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.fab1 -> {
                anim()
                Toast.makeText(this, "Floating Action Button", Toast.LENGTH_SHORT).show()
            }
            R.id.fab2 -> {
                anim()
                Toast.makeText(this, "Button1", Toast.LENGTH_SHORT).show()
            }
            R.id.fab3 -> {
                anim()
                Toast.makeText(this, "Button2", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun anim() {
        if (isFabOpen) {
            fab2.startAnimation(fab_close)
            fab3.startAnimation(fab_close)
            fab2.isClickable = false
            fab3.isClickable = false
            isFabOpen = false
        } else {
            fab2.startAnimation(fab_open)
            fab3.startAnimation(fab_open)
            fab2.isClickable = true
            fab3.isClickable = true
            isFabOpen = true
        }
    }
}

