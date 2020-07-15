package com.keelim.hardware.view.fragments

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.keelim.hardware.R
import kotlinx.android.synthetic.main.activity_hello.*
import kotlinx.android.synthetic.main.app_bar_main.*

class FragmentMainActivity2 : AppCompatActivity(R.layout.activity_hello) {
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            Snackbar.make(view!!, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_1,
            R.id.nav_2,
            R.id.nav_3,
            R.id.nav_4,
            R.id.nav_5,
            R.id.nav_6,
            R.id.nav_7,
            R.id.nav_8,
            R.id.nav_9,
            R.id.nav_10,
            R.id.nav_11,
            R.id.nav_12,
            R.id.nav_13,
            R.id.nav_14,
            R.id.nav_15,
            R.id.nav_16,
            R.id.nav_17,
            R.id.nav_18,
            R.id.nav_19,
            R.id.nav_20,
            R.id.nav_21
        )
            .setDrawerLayout(drawer_layout)
            .build()
        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(nav_view, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.hello, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }
}