package com.mfec.teamandroidshare.manager.http;

import android.content.Context;

import com.mfec.teamandroidshare.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HttpManagerNice {

    private static HttpManagerNice instance;

    public static HttpManagerNice getInstance() {
        if (instance == null)
            instance = new HttpManagerNice();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private HttpManagerNice() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.221.197.204:8080/serviceMiniProject/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() { return service; }

}
