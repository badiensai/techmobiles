package com.example.ensai.techmobiles;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by ensai on 06/04/18.
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
