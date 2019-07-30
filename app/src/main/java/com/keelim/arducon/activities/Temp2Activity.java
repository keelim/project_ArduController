package com.keelim.arducon.activities;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.keelim.arducon.R;
import com.keelim.arducon.fragments.temp2.Temp2_1Fragment;
import com.keelim.arducon.fragments.temp2.Temp2_2Fragment;
import com.keelim.arducon.fragments.temp2.Temp2_3Fragment;

public class Temp2Activity extends AppCompatActivity implements Temp2_1Fragment.OnFragmentInteractionListener, Temp2_3Fragment.OnFragmentInteractionListener, Temp2_2Fragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp2);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }
}
