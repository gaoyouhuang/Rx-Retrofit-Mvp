package com.example.scs.rxretrofitmvp;

import android.app.Application;

import com.example.scs.rxretrofitmvp.util.SharedPreferencesManager;

/**
 * Created by scs on 18-3-12.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.init(this);
    }
}
