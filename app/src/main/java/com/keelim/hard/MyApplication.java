package com.keelim.hard;

import android.app.Application;

import androidx.appcompat.app.AppCompatDialog;	

public class MyApplication extends Application {	
    private static MyApplication myApplication;	
    AppCompatDialog progressDialog;	

    public static MyApplication getInstance() {	
        return myApplication;	
    }	

    @Override	
    public void onCreate() {	
        super.onCreate();	
        myApplication = this;	
    }	


}