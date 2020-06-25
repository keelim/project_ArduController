package com.keelim.hardware.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.keelim.hardware.R;
import com.keelim.hardware.activity.AccelarometerActivity;
import com.keelim.hardware.activity.BatteryindicatorActivity;
import com.keelim.hardware.activity.BluetoothAddActivity;
import com.keelim.hardware.activity.ButtonTestingActivity;
import com.keelim.hardware.activity.DisplayActivity;
import com.keelim.hardware.activity.GpslocActivity;
import com.keelim.hardware.activity.GravitySensorActivity;
import com.keelim.hardware.activity.GyroscopeActivity;
import com.keelim.hardware.activity.HeadphoneActivity;
import com.keelim.hardware.activity.LightSensorActivity;
import com.keelim.hardware.activity.MagneticSensorActivity;
import com.keelim.hardware.activity.MicTestingActivity;
import com.keelim.hardware.activity.PressureActivity;
import com.keelim.hardware.activity.ProximitySensorActivity;
import com.keelim.hardware.activity.SystemInfoActivity;
import com.keelim.hardware.activity.TeleActivity;
import com.keelim.hardware.activity.TouchSensorActivity;
import com.keelim.hardware.activity.VibrationActivity;
import com.keelim.hardware.activity.WifiAddressActivity;

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
//                    Intent  new Intent(context, FlashActivity::class.java)
//                            ; mcontext.startActivity(intent) }
                            Toast.makeText(context, "testing ...", Toast.LENGTH_SHORT).show();

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
                            mContext.startActivity(new Intent(context, ButtonTestingActivity.class));
                            break;


                        case 10:
                            //wrong should have done that while taking calling position we here our voice
                            mContext.startActivity(new Intent(context, MicTestingActivity.class));
                            break;


                        case 11:
                            mContext.startActivity(new Intent(context, WifiAddressActivity.class));
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