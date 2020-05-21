package com.keelim.testing.result

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.keelim.testing.test1.Test1Model

class ResultAdapter : BaseAdapter() {
    var resultCount = 0
    var model: Test1Model? = null
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