package com.mfec.teamandroidshare.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mfec.teamandroidshare.R;
import com.mfec.teamandroidshare.fragment.FragmentRank;

public class RankActivity extends AppCompatActivity {
    Toolbar toolbar;
    private String TAG = "ff";
    CountDownTimer cdt;
    boolean time = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ///////////////////////////////////////////////
        cdt = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Tick
            }

            public void onFinish() {
                time = true;
            }
        }.start();
//////////////////////////////////////////////////////
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentRank, new FragmentRank())
                    .commit();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


    @Override
    public void onBackPressed() {
        if (time == true) {
            super.onBackPressed();
            finish();
        }
    }
}
