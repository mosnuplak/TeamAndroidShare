package com.mfec.teamandroidshare;

import android.app.Application;

import com.mfec.teamandroidshare.manager.Contextor;


/**
 * Created by E5-473G on 8/3/2017.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
