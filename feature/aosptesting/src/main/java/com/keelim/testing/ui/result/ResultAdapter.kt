package com.keelim.testing.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keelim.testing.R

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.MyViewHolder>() {
    private var list: List<Long> = listOf()

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false) as TextView
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = position.toString() + ": " + list[position].toString()
    }

    override fun getItemCount() = list.size

    fun setItem(items: List<Long>) {
        list = items
    }
}
