package com.keelim.testing.test2

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class Test2Adapter : BaseAdapter() {
    var test2count = 0
    override fun getCount(): Int {
        return 0
    }

    override fun getItem(position: Int): Any {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View,
        parent: ViewGroup
    ): View {
        return null
    }
}