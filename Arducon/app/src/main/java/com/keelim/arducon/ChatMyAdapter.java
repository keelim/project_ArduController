package com.keelim.arducon;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

class ChatMyAdapter extends ArrayAdapter<ChatMessage> {
    ArrayList<ChatMessage> list;
    int resId;
    Context context;

    public ChatMyAdapter(Context context, int resId, ArrayList<ChatMessage> list) {
        super(context, resId, list);
        this.context = context;
        this.resId = resId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resId, null);


        TextView msgView = (TextView) convertView.findViewById(R.id.mission2_item_msg);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) msgView
                .getLayoutParams();

        ChatMessage msg = list.get(position);
        if (msg.who.equals("me")) {
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
                    RelativeLayout.TRUE);
            msgView.setTextColor(Color.WHITE);
            msgView.setBackgroundResource(R.drawable.bg_right);
        } else if (msg.who.equals("you")) {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
                    RelativeLayout.TRUE);
            msgView.setBackgroundResource(R.drawable.bg_left);
        }
        msgView.setText(msg.msg);

        return convertView;

    }
}
