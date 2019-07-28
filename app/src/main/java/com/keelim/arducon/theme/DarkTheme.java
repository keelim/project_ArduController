package com.keelim.arducon.theme;

import com.keelim.arducon.utils.AppCompatDelgate;

import static com.keelim.arducon.utils.AppCompatDelgate.MODE_NIGHT_NO;
import static com.keelim.arducon.utils.AppCompatDelgate.MODE_NIGHT_YES;

public class DarkTheme {
    public void apply(boolean enabled) {
        int nightMode;
        if (enabled)
            nightMode = MODE_NIGHT_YES;
        else
            nightMode = MODE_NIGHT_NO;

        AppCompatDelgate.setDefaultNightMode(nightMode);
    }
}
