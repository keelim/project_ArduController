package com.keelim.hard.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keelim.hard.R
import kotlinx.android.synthetic.main.recycler_content.view.*

class RecyclerAdapter(makeList: MutableList<String>) : RecyclerView.Adapter<RecyclerAdapter.MainViewHolder>() {
    private var items: MutableList<String> = makeList

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MainViewHolder(parent)


    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: RecyclerAdapter.MainViewHolder, position: Int) {
        items[position].let { item ->
            with(holder) {
                tvTitle.text = item[position].toString()
            }
        }

    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_content, parent, false)) {
        val tvTitle: TextView = itemView.recycler_content_text
    }
}