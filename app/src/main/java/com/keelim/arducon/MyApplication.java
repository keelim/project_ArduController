package com.keelim.arducon;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.KakaoSDK;
import com.keelim.arducon.adapters.KakaoSDKAdapter;

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getGlobalApplicationContext() {
        if (instance == null) {
            throw new IllegalStateException("This Application does not inherit com.kakao.MyApplication");
        }
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
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