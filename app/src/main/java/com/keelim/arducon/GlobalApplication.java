package com.keelim.arducon;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.kakao.auth.KakaoSDK;
import com.keelim.arducon.adapters.KakaoSDKAdapter;

public class GlobalApplication extends Application {
    private static GlobalApplication instance;

    public static GlobalApplication getGlobalApplicationContext() {
        if (instance == null) {
            throw new IllegalStateException("This Application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // Kakao Sdk 초기화
        KakaoSDK.init(new KakaoSDKAdapter());
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}