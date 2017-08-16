package com.mfec.teamandroidshare.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        tvToolbar.setText(topicName);
    }

    private void init() {
        tvToolbar = (TextView) findViewById(R.id.tvToolbar);

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
                    Log.d("hi","eiei");
                }
            }

            @Override
            public void onFailure(Call<TitleDao> call, Throwable t) {

            }
        });

    }
}
