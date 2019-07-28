package com.keelim.arducon.utils;

import androidx.appcompat.app.AppCompatDelegate;

public abstract class AppCompatDelgate {
    public abstract void setLocalNightMode(@AppCompatDelegate.NightMode int mode);

    public static void setDefaultNightMode(@AppCompatDelegate.NightMode int mode) {

    }
}
