package com.keelim.aio.making;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.keelim.aio.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OpenRecyclerAdapter extends RecyclerView.Adapter<OpenRecyclerAdapter.ViewHolder> { //커스텀 adapter
    private final List<OpenItem> mData;
    private final Context mContext;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView1;

        ViewHolder(View view) {
            super(view);

            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
//                    mData.set(pos, "item clicked. pos=" + pos);
                    Context context = v.getContext();



                    notifyItemChanged(pos);
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = view.findViewById(R.id.open_text1);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public OpenRecyclerAdapter(Context context, List<OpenItem> list) {
        this.mData = list;
        this.mContext = context;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NotNull
    @Override
    public OpenRecyclerAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_open, parent, false);

        return new ViewHolder(view);
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NotNull OpenRecyclerAdapter.ViewHolder holder, int position) {
//        String text = mData.get(position);
//        holder.textView1.setText(text);

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}