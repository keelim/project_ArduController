package com.keelim.hardware.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.keelim.hardware.R;
import com.keelim.hardware.view.AccelarometerActivity;
import com.keelim.hardware.view.BatteryindicatorActivity;
import com.keelim.hardware.view.BluetoothAddActivity;
import com.keelim.hardware.view.SoundButtonActivity;
import com.keelim.hardware.view.DisplayActivity;
import com.keelim.hardware.view.FlashActivity;
import com.keelim.hardware.view.GpslocActivity;
import com.keelim.hardware.view.GravitySensorActivity;
import com.keelim.hardware.view.GyroscopeActivity;
import com.keelim.hardware.view.HeadphoneActivity;
import com.keelim.hardware.view.LightSensorActivity;
import com.keelim.hardware.view.MagneticSensorActivity;
import com.keelim.hardware.view.MicActivity;
import com.keelim.hardware.view.PressureActivity;
import com.keelim.hardware.view.ProximitySensorActivity;
import com.keelim.hardware.view.SystemInfoActivity;
import com.keelim.hardware.view.TeleActivity;
import com.keelim.hardware.view.TouchSensorActivity;
import com.keelim.hardware.view.VibrationActivity;
import com.keelim.hardware.view.WifiActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {

    private final ArrayList<String> mData;
    private final Context mContext;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView1;

        ViewHolder(View view) {
            super(view);


            view.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    mData.set(pos, "item clicked. pos=" + pos);
                    Context context = v.getContext();

                    switch (pos) {
                        case 0:
                            //not working
                            mContext.startActivity(new Intent(context, VibrationActivity.class));
                            break;
                        case 1:
                            mContext.startActivity(new Intent(context, TeleActivity.class));
                            break;

                        case 2:
                            //not whole info
                            mContext.startActivity(new Intent(context, SystemInfoActivity.class));
                            break;

                        case 3:
                            mContext.startActivity(new Intent(context, ProximitySensorActivity.class));
                            break;

                        case 4:
                            //not working
                            mContext.startActivity(new Intent(context, FlashActivity.class));
                            break;

                        case 5:
                            mContext.startActivity(new Intent(context, TouchSensorActivity.class));
                            break;

                        case 6:
                            //display multiple lightin
                            mContext.startActivity(new Intent(context, DisplayActivity.class));
                            break;


                        case 7:
                            //light sensor display of dac values
                            mContext.startActivity(new Intent(context, LightSensorActivity.class));
                            break;


                        case 8:
                            mContext.startActivity(new Intent(context, PressureActivity.class));
                            break;


                        case 9:
                            mContext.startActivity(new Intent(context, SoundButtonActivity.class));
                            break;


                        case 10:
                            //wrong should have done that while taking calling position we here our voice
                            mContext.startActivity(new Intent(context, MicActivity.class));
                            break;


                        case 11:
                            mContext.startActivity(new Intent(context, WifiActivity.class));
                            break;


                        case 12:
                            mContext.startActivity(new Intent(context, BluetoothAddActivity.class));
                            break;

                        case 13:

                            mContext.startActivity(new Intent(context, GravitySensorActivity.class));
                            break;

                        case 14:

                            mContext.startActivity(new Intent(context, MagneticSensorActivity.class));
                            break;


                        case 15:
                            //not working
                            mContext.startActivity(new Intent(context, HeadphoneActivity.class));
                            break;


                        case 16:
                            mContext.startActivity(new Intent(context, GyroscopeActivity.class));
                            break;


                        case 17:
                            //test again

                            mContext.startActivity(new Intent(context, GpslocActivity.class));
                            break;


                        case 18:

                            mContext.startActivity(new Intent(context, BatteryindicatorActivity.class));
                            break;


                        case 19:
                            mContext.startActivity(new Intent(context, AccelarometerActivity.class));
                            break;
                    }
                    notifyItemChanged(pos);
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = view.findViewById(R.id.recycler_text1);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public TextAdapter(Context context, ArrayList<String> list) {
        this.mData = list;
        this.mContext = context;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @NotNull
    @Override
    public TextAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NotNull TextAdapter.ViewHolder holder, int position) {
        String text = mData.get(position);
        holder.textView1.setText(text);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}