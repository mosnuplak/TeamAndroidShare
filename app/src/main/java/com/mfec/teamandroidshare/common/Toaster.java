package com.mfec.teamandroidshare.common;

//import android.os.CountDownTimer;
//import android.view.Gravity;
//import android.widget.Toast;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;

public class Toaster {

    public static void  ggToast(Context context, int string, int showTime){
        final Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, showTime);
    }













}
