package com.keelim.hardware.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.keelim.hardware.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {

    private final ArrayList<String> mData;
    private final Context mContext;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView1;

        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    mData.set(pos, "item clicked. pos=" + pos);
                    Context context = v.getContext();
                    Intent intent;
                    switch (pos) {

                        case 0:
                            //not working
                            intent = new Intent(context, VibrationActivity.class);
                            mContext.startActivity(intent);

                        case 1:

                            intent = new Intent(context, TeleActivity.class);
                            mContext.startActivity(intent);


                        case 2:
                            //not whole info

                            intent = new Intent(context, SystemInfoActivity.class);
                            mContext.startActivity(intent);


                        case 3:
                            intent = new Intent(context, ProximitySensorActivity.class);
                            mContext.startActivity(intent);


                        case 4:
                            //not working
//                    Intent intent = new Intent(context, FlashActivity::class.java)
//                            ; mcontext.startActivity(intent) }

                        case 5:
                            intent = new Intent(context, TouchSensorActivity.class);
                            mContext.startActivity(intent);


                        case 6:
                            //display multiple lighting
                            intent = new Intent(context, DisplayActivity.class);
                            mContext.startActivity(intent);


                        case 7:
                            //light sensor display of dac values
                            intent = new Intent(context, LightSensorActivity.class);
                            mContext.startActivity(intent);


                        case 8:
                            intent = new Intent(context, PressureActivity.class);
                            mContext.startActivity(intent);


                        case 9:
                            //wrong the volume buttons,home,menu etc has to be checked here
                            intent = new Intent(context, ButtonTestingActivity.class);
                            mContext.startActivity(intent);


                        case 10:
                            //wrong should have done that while taking calling position we here our voice
                            intent = new Intent(context, MicTestingActivity.class);
                            mContext.startActivity(intent);


                        case 11:
                            intent = new Intent(context, WifiAddressActivity.class);
                            mContext.startActivity(intent);


                        case 12:
                            intent = new Intent(context, BluetoothAddActivity.class);
                            mContext.startActivity(intent);


                        case 13:

                            intent = new Intent(context, GravitySensorActivity.class);
                            mContext.startActivity(intent);


                        case 14:


                            intent = new Intent(context, MagneticSensorActivity.class);
                            mContext.startActivity(intent);


                        case 15:
                            //not working
                            intent = new Intent(context, HeadphoneActivity.class);

                            mContext.startActivity(intent);


                        case 16:
                            intent = new Intent(context, GyroscopeActivity.class);
                            mContext.startActivity(intent);


                        case 17:
                            //test again
                            intent = new Intent(context, GpslocActivity.class);
                            mContext.startActivity(intent);


                        case 18:
                            intent = new Intent(context, BatteryindicatorActivity.class);
                            mContext.startActivity(intent);


                        case 19:
                            intent = new Intent(context, AccelarometerActivity.class);
                            mContext.startActivity(intent);

                    }
                    notifyItemChanged(pos);
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.text1);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public SimpleTextAdapter(Context context, ArrayList<String> list) {
        this.mData = list;
        this.mContext = context;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NotNull
    @Override
    public SimpleTextAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NotNull SimpleTextAdapter.ViewHolder holder, int position) {
        String text = mData.get(position);
        holder.textView1.setText(text);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}