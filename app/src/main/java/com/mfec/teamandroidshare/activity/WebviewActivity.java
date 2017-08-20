package com.mfec.teamandroidshare.activity;


import android.content.Intent;
import android.os.Bundle;
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
    TextView tvToolbar;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        init();

        if (savedInstanceState == null) {
            link = getIntent().getStringExtra("linkUrl");
            topicId = getIntent().getStringExtra("topicId");
            topicName = getIntent().getStringExtra("topicName");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentWeb , FragmentWebTitle.newInstance(link),"getFragmentWebTitle")
                    .commit();
        }
        setSupportActionBar(toolbar);
        tvToolbar.setText(topicName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    protected void onDestroy() {
        super.onDestroy();
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
}
