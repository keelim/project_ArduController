package com.keelim.hard.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keelim.aio.Open.OpenItem
import com.keelim.hard.R

class OpRecyclerAdapter // 생성자에서 데이터 리스트 객체를 전달받음.
(//리사이클러 뷰를 사용을 하기위 커스터 어댑터
        private val mData: MutableList<OpenItem>) : RecyclerView.Adapter<OpRecyclerAdapter.ViewHolder>() {

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_openview, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder() - position 에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text1 = mData[position].sector
        val text2 = mData[position].desc

        holder.openText1.text = text1
        holder.openText2.text = text2
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    override fun getItemCount(): Int {
        return mData.size
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val openText1: TextView = view.findViewById(R.id.open_tv1)
        val openText2: TextView = view.findViewById(R.id.open_tv2)

        init {

            /*view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    mData.set(pos, "item clicked. pos=" + pos);
                    Context context = v.getContext();
                    notifyItemChanged(pos);
                }
            });*/

            // 뷰 객체에 대한 참조. (hold strong reference)
        }
    }

}