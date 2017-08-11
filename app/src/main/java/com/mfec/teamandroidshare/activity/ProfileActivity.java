package com.mfec.teamandroidshare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mfec.teamandroidshare.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    FancyButton btn_rank2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn_rank2 = (FancyButton) findViewById(R.id.btn_rank2);
        btn_rank2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplication(), RankActivity.class);
        startActivity(i);
    }
}
