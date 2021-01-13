package com.keelim.arducon.ui.device

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keelim.arducon.R
import com.keelim.arducon.data.Data
import java.util.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // adapter에 들어갈 list 입니다.
    private val listData = ArrayList<Data>()

    class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.device_items, parent, false)) {
        fun onBind(data: Data) {
            val textView1: TextView = itemView.findViewById(R.id.item_tv1)
            val textView2: TextView = itemView.findViewById(R.id.item_tv2)
            textView1.text = data.title
            textView2.text = data.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder = ItemViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.onBind(listData[position])
    }

    override fun getItemCount(): Int = listData.size


    fun addItem(data: Data) { // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data)
    }
}

