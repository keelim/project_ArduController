package com.keelim.arducon.utils;

import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

public abstract class AppCompatDelgate {
    public abstract void setLocalNightMode(@AppCompatDelegate.NightMode int mode);

    public static void setDefaultNightMode(@AppCompatDelegate.NightMode int mode) {

    }

    boolean isDarkTheme(Configuration config){
        return config.uiMode == Configuration.UI_MODE_NIGHT_MASK;
    }
}
