package com.mfec.teamandroidshare.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.dao.TitleDao;
import com.mfec.teamandroidshare.fragment.FragmentWebTitle;
import com.mfec.teamandroidshare.manager.http.HttpManagerNice;

import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MSI on 16/8/2560.
 */

public class WebviewActivity extends AppCompatActivity {
    public String link;
    public String topicId;
    public String topicName;
    public String category;
    TextView tvToolbar;
    Toolbar toolbar;
    Boolean back = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        init();
        timemer();

        if (savedInstanceState == null) {
            link = getIntent().getStringExtra("linkUrl");
            topicId = getIntent().getStringExtra("topicId");
            topicName = getIntent().getStringExtra("topicName");
            category = getIntent().getStringExtra("category");

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentWeb , FragmentWebTitle.newInstance(link,category),"getFragmentWebTitle")
                    .commit();
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(category);
        tvToolbar.setText(topicName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void timemer() {
        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("MildMos","sec :: "+millisUntilFinished/1000);
                    if(back == true){
                        cancel();
                }
            }

            @Override
            public void onFinish() {
                Log.d("MildMos","done!");
                TitleDao titleDao = new TitleDao();
                titleDao.setTopicId(topicId);
                Call<TitleDao> call = HttpManagerNice.getInstance().getService().AddViewTopic(titleDao);
                call.enqueue(new Callback<TitleDao>() {
                    @Override
                    public void onResponse(Call<TitleDao> call, Response<TitleDao> response) {
                        if (response.isSuccessful()) {

                        }
                    }

                    @Override
                    public void onFailure(Call<TitleDao> call, Throwable t) {

                    }
                });

            }
        }.start();

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbar = (TextView) findViewById(R.id.tvToolbar);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                finish();
            default :
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
            back = true;
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
