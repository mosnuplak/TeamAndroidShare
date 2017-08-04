package com.mfec.teamandroidshare.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentRank;

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flRank, new FragmentRank())
                    .commit();
        }
    }
}
